package silas.dev.neatly.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import silas.dev.neatly.ui.collections.CollectionInfoViewState
import silas.dev.neatly.ui.collections.CollectionRowViewState
import silas.dev.neatly.ui.products.ProductInfoViewState
import silas.dev.neatly.ui.products.ProductWithCollectionViewState

@Composable
fun CollectionRow(
    collectionRowViewState: CollectionRowViewState,
    onProductClick: (ProductWithCollectionViewState) -> Unit,
    onCollectionClick: (CollectionInfoViewState) -> Unit
) {
    HeaderLabel(
        collectionRowViewState.collection.name,
        onCollectionClick = { onCollectionClick(collectionRowViewState.collection) }
    )
    RowOfProducts(collectionRowViewState.products, onProductClick, collectionRowViewState.collection.id)
}

