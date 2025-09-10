package silas.dev.neatly.ui.collections

import androidx.compose.runtime.Immutable
import silas.dev.neatly.ui.products.ProductInfoViewState

@Immutable
class CollectionScreenViewState (
    val products: List<ProductInfoViewState>,
    val collectionName: String
){
    companion object Companion {
        val NONE = CollectionScreenViewState(
        products = emptyList(),
        collectionName = ""
    )}

}