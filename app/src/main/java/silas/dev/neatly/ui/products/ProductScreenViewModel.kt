package silas.dev.neatly.ui.products

import androidx.compose.foundation.text.input.TextFieldState
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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


    val nameLabel: TextFieldState = TextFieldState()
    val descriptionLabel: TextFieldState = TextFieldState()

    private var id: Int = 0

    fun initWithId(id: Int) {
        this.id = id
        viewModelScope.launch(Dispatchers.IO) {
            val newState: ProductInfoViewState
            if (id > 0) {
                val photoInfo = getPhoto()
                val product = repo.getProduct(id)
                newState = ProductInfoViewState(
                    product.id,
                    name = product.name,
                    description = product.description,
                    photoInfo = photoInfo
                )
            } else {
                newState = ProductInfoViewState.NONE
            }
            _productInfoViewState.emit(newState)
        }
    }

    fun saveProduct() {
        if (id != 0) {
            viewModelScope.launch(Dispatchers.IO) {
                val data = ProductInfo(
                    id = id,
                    name = nameLabel.text.toString(),
                    description = descriptionLabel.text.toString(),
                    upcCode = "",
                    photoInfo = PhotoInfo(0, "", id)
                )
                id = repo.addProduct(data).toInt()
                val collectionId = savedStateHandle.get<Int>("collectionId")
                repo.addProductCollectionCrossRef(id, collectionId = collectionId!!)
            }
        }
    }

    fun setPhoto(uri: String){
        viewModelScope.launch(Dispatchers.IO){
            repo.setPhoto(
                PhotoInfo(
                    photoId = 0,
                    uri = uri,
                    productId = id
                )
            )
        }
    }

    // A function to trigger the fetch, often called from the UI or an init block.
    suspend fun getPhoto(): PhotoInfoViewState {
        return withContext(Dispatchers.IO) {
            val photo = repo.getPhotosByProductId(id)
            if (photo.isEmpty()) {
                return@withContext PhotoInfoViewState.NONE
            }
            PhotoInfoViewState(
                photoId = photo[0].photoId,
                uri = photo[0].uri,
                productId = photo[0].productId
            ) // The last expression is the return value of the withContext block.
        }
    }

}