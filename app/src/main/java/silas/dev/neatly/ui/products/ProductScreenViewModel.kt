package silas.dev.neatly.ui.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import silas.dev.neatly.domain.Repository
import silas.dev.neatly.ui.home.HomeScreenViewState
import javax.inject.Inject

@HiltViewModel
class ProductScreenViewModel @Inject constructor(
    private val repo: Repository
) : ViewModel() {
    private val _productInfoViewState =
        MutableStateFlow<ProductInfoViewState>(ProductInfoViewState.NONE)
    val productInfoViewState: StateFlow<ProductInfoViewState> = _productInfoViewState

    fun initWithId(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val newState: ProductInfoViewState
            if (id != -1) {
                val product = repo.getProduct(id)
                newState = ProductInfoViewState(
                    product.id,
                    name = product.name,
                    description = product.description
                )
            }
            else {
                newState = ProductInfoViewState.NONE
            }
            _productInfoViewState.emit(newState)
        }
    }
}