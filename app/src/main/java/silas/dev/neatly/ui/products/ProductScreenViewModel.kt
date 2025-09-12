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
import silas.dev.neatly.domain.ProductInfo
import silas.dev.neatly.domain.Repository
import silas.dev.neatly.ui.home.HomeScreenViewState
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
                val product = repo.getProduct(id)
                newState = ProductInfoViewState(
                    product.id,
                    name = product.name,
                    description = product.description
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
                upcCode = ""
            )
            val newID = repo.addProduct(data)
            val collectionId = savedStateHandle.get<Int>("collectionId")
            repo.addProductCollectionCrossRef(newID.toInt(), collectionId = collectionId!!)
        }
    }
}