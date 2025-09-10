package silas.dev.neatly.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import silas.dev.neatly.domain.CollectionInfo
import silas.dev.neatly.domain.Repository
import silas.dev.neatly.ui.collections.CollectionInfoViewState
import silas.dev.neatly.ui.collections.CollectionRowViewState
import silas.dev.neatly.ui.products.ProductInfoViewState
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repo: Repository
) : ViewModel() {
    private val _homeScreenViewState =
        MutableStateFlow<HomeScreenViewState>(HomeScreenViewState.Loading)
    val homeScreenViewState: StateFlow<HomeScreenViewState> = _homeScreenViewState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val collectionsWithProducts = repo.getCollectionsWithProducts()
            val data = HomeScreenViewState.Data(
                rows = collectionsWithProducts.map { collectionWithProducts ->
                    CollectionRowViewState(
                        collection = CollectionInfoViewState(
                            id = collectionWithProducts.collection.collectionId,
                            name = collectionWithProducts.collection.name,
                            description = collectionWithProducts.collection.description
                        ),
                        products = collectionWithProducts.products.map { product ->
                            ProductInfoViewState(
                                id = product.id,
                                name = product.name,
                                description = product.description
                            )
                        }
                    )
                }
            )
            _homeScreenViewState.update {
                data
            }
        }
    }

    fun onAddCollection(collectionName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val collection = CollectionInfo(
                name = collectionName,
                description = "",
                collectionId = 0
            )
            repo.addCollection(collection)
        }

    }

}