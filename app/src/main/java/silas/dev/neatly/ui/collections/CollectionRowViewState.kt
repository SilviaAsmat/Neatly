package silas.dev.neatly.ui.collections

import androidx.compose.runtime.Immutable
import silas.dev.neatly.ui.products.ProductInfoViewState

@Immutable
data class CollectionRowViewState(
    val collection: CollectionInfoViewState,
    val products: List<ProductInfoViewState>
)