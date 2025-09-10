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
): ViewModel() {
    private val _productInfoViewState = MutableStateFlow<ProductInfoViewState>(ProductInfoViewState.NONE)
    val productInfoViewState: StateFlow<ProductInfoViewState> = _productInfoViewState

    init {
        viewModelScope.launch(Dispatchers.IO) {

        }
    }

    fun onEditIconClicked() {

    }

    fun onSave() {

    }

    fun onDelete() {

    }

}