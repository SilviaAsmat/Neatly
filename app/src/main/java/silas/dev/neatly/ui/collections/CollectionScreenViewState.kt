package silas.dev.neatly.ui.collections

import androidx.compose.runtime.Immutable
import silas.dev.neatly.ui.products.ProductInfoViewState

@Immutable
class CollectionScreenViewState(
    val products: List<CollectionProductItemViewState>,
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

// wraps ProductInfoViewState so we can associate a URI with it
@Immutable
data class CollectionProductItemViewState(
    val productInfo: ProductInfoViewState,
    val photoUri: String?,
)