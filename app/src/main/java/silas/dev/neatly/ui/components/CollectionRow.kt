package silas.dev.neatly.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import silas.dev.neatly.ui.collections.CollectionRowViewState
import silas.dev.neatly.ui.products.ProductInfoViewState

@Composable
fun CollectionRow(
    collectionRowViewState: CollectionRowViewState,
    onProductClick: (ProductInfoViewState) -> Unit
) {
//    Column {
//
//    }
    HeaderLabel(collectionRowViewState.collection.name)
    RowOfProducts(collectionRowViewState.products, onProductClick)
}

