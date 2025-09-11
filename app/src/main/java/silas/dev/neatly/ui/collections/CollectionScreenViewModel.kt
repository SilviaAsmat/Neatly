package silas.dev.neatly.ui.collections

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import silas.dev.neatly.domain.ProductInfo
import silas.dev.neatly.domain.Repository
import silas.dev.neatly.ui.products.ProductInfoViewState
import javax.inject.Inject

@HiltViewModel
class CollectionScreenViewModel @Inject constructor(
    private val repo: Repository
) : ViewModel() {
    private val _collectionsScreenViewState =
        MutableStateFlow<CollectionScreenViewState>(CollectionScreenViewState.NONE)
    val collectionsScreenViewState =
        _collectionsScreenViewState as StateFlow<CollectionScreenViewState>

    fun initWithName (collectionName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val data = repo.getCollectionByName(collectionName)
            val products = repo.getCollectionWithProducts(data.collectionId)
            val newState = CollectionScreenViewState(
                collectionName = data.name,
                products = products.map { product ->
                    ProductInfoViewState(
                        product.id,
                        product.name,
                        description = product.description
                    )
                }
            )
            _collectionsScreenViewState.emit(newState)
        }
    }

}