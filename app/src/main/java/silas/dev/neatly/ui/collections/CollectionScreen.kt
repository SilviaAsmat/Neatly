package silas.dev.neatly.ui.collections

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import silas.dev.neatly.ui.components.AddProductButton
import silas.dev.neatly.ui.components.HeaderLabel
import silas.dev.neatly.ui.components.ProductCard
import silas.dev.neatly.ui.products.ProductInfoViewState

@Composable
fun CollectionScreen(
    viewModel: CollectionScreenViewModel,
    onProductClick: (Int) -> Unit,
    onAddProductClick: () -> Unit

) {
    val viewState by viewModel.collectionsScreenViewState.collectAsState()
    CollectionScreen(viewState, onProductClick, onAddProductClick)
}

@Composable
private fun CollectionScreen(
    viewState: CollectionScreenViewState,
    onProductClick: (Int) -> Unit,
    onAddProductClick: () -> Unit = {}
) {
    Scaffold(floatingActionButton = { AddProductButton(onAddProductClick) })
    { innerPadding ->
        Text(
            text = viewState.collectionName,
            modifier = Modifier.padding(innerPadding)
        )

        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(viewState.products.size) { index ->
                ProductCard(viewState.products[index], onProductClick)
            }
        }
    }

}