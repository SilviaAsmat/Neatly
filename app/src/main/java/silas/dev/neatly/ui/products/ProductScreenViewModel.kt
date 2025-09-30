package silas.dev.neatly.ui.products

import androidx.compose.foundation.text.input.TextFieldState
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import silas.dev.neatly.domain.PhotoInfo
import silas.dev.neatly.domain.ProductInfo
import silas.dev.neatly.domain.Repository
import javax.inject.Inject

@HiltViewModel
class ProductScreenViewModel @Inject constructor(
    private val repo: Repository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _productInfoViewState =
        MutableStateFlow<ProductInfoViewState>(ProductInfoViewState.NONE)
    val productInfoViewState: StateFlow<ProductInfoViewState> = _productInfoViewState

    private val _photoInfoViewState =
        MutableStateFlow<PhotoInfoViewState>(PhotoInfoViewState.Loading)
    val photoInfoViewState: StateFlow<PhotoInfoViewState> = _photoInfoViewState

    val nameLabel: TextFieldState = TextFieldState()
    val descriptionLabel: TextFieldState = TextFieldState()

    private var id: Int = 0

    fun initWithId(productId: Int) {
        this.id = productId
        viewModelScope.launch(Dispatchers.IO) {
            val newState: ProductInfoViewState
            if (productId > 0) {
                initPhotoInfoViewState(productId)
                val product = repo.getProduct(productId)
                newState = ProductInfoViewState(
                    product.id,
                    name = product.name,
                    description = product.description,
                )
            } else {
                newState = ProductInfoViewState.NONE
            }
            _productInfoViewState.emit(newState)
        }
    }

    fun saveProduct() {
        viewModelScope.launch(Dispatchers.IO) {
            val data = ProductInfo(
                id = id,
                name = nameLabel.text.toString(),
                description = descriptionLabel.text.toString(),
                upcCode = "",
                photoInfo = PhotoInfo(0, "", id) // TODO SAA remove?
            )
            // Save product to db
            val productId = repo.addProduct(data).toInt()
            val collectionId = savedStateHandle.get<Int>("collectionId")
            // Add product to collection
            repo.addProductCollectionCrossRef(productId, collectionId = collectionId!!)
            // Associate photo with product
            (_photoInfoViewState.value as? PhotoInfoViewState.Data)?.let { photoViewState ->
                repo.setPhoto(
                    PhotoInfo(
                        photoId = 0,
                        uri = photoViewState.uri,
                        productId = productId
                    )
                )
            }
            // Cache product Id
            this@ProductScreenViewModel.id = productId
        }
    }

    fun setPhoto(uri: String){
        val newPhotoViewState = PhotoInfoViewState.Data(
            photoId = 0,// For now we only support one photo per product
            uri = uri,
            productId = this.id,
        )
        _photoInfoViewState.update { newPhotoViewState }
    }

    private suspend fun initPhotoInfoViewState(productId: Int) {
        val photos = repo.getPhotosByProductId(productId)
        val viewState = if (photos.isEmpty()) {
            PhotoInfoViewState.Empty
        } else {
            PhotoInfoViewState.Data(
                photoId = photos[0].photoId,// For now we only support one photo per product
                uri = photos[0].uri,
                productId = photos[0].productId,
            )
        }
        _photoInfoViewState.update{viewState}
    }

}