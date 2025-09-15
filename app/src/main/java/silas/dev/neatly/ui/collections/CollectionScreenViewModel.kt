package silas.dev.neatly.ui.collections

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import silas.dev.neatly.domain.CollectionInfo
import silas.dev.neatly.domain.CrossRefInfo
import silas.dev.neatly.domain.ProductInfo
import silas.dev.neatly.domain.Repository
import silas.dev.neatly.ui.products.ProductInfoViewState
import javax.inject.Inject

@HiltViewModel
class CollectionScreenViewModel @Inject constructor(
    private val repo: Repository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _collectionsScreenViewState =
        MutableStateFlow<CollectionScreenViewState>(CollectionScreenViewState.NONE)
    val collectionsScreenViewState =
        _collectionsScreenViewState as StateFlow<CollectionScreenViewState>
    private val _showDeleteDialog = MutableStateFlow(false)
    val showDeleteDialog: StateFlow<Boolean> = _showDeleteDialog

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val collectionId = savedStateHandle.get<Int>("collectionId")
            val data = repo.getCollectionById(collectionId!!)

            repo.getCollectionWithProducts(data.collectionId)
                .map { productList ->
                    CollectionScreenViewState(
                        collectionName = data.name,
                        collectionId = data.collectionId,
                        products = productList.map { product ->
                            ProductInfoViewState(
                                name = product.name,
                                description = product.description,
                                id = product.id
                            )
                        }
                    )
                }
                .collect { newState ->
                    _collectionsScreenViewState.emit(newState)
                }
        }
    }

    fun onDeleteIconClicked() {
        // call dialog to confirm delete
        _showDeleteDialog.value = true

    }

    fun onDismissDelete() {
        _showDeleteDialog.value = false
    }

    fun onDeleteConfirmed() {
        _showDeleteDialog.value = false
        viewModelScope.launch(Dispatchers.IO) {
            val collectionId = savedStateHandle.get<Int>("collectionId")
            val collection = repo.getCollectionById(collectionId!!)
            repo.deleteCollection(collection)
            val productFlow = repo.getCollectionWithProducts(collection.collectionId)
            productFlow.map { products ->
                products.forEach { product ->
                    repo.deleteProductCollectionCrossRef(CrossRefInfo(product.id, collectionId))
                    repo.deleteProduct(product)
                }
            }

        }
    }

}