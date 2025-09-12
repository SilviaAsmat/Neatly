package silas.dev.neatly.ui.collections

import androidx.compose.runtime.Immutable
import silas.dev.neatly.ui.products.ProductInfoViewState

@Immutable
class CollectionScreenViewState(
    val products: List<ProductInfoViewState>,
    val collectionId: Int,
    val collectionName: String
) {
    companion object {
        val NONE = CollectionScreenViewState(
            products = emptyList(),
            collectionId = 0,
            collectionName = ""
        )
    }

}