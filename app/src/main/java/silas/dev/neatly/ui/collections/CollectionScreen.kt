package silas.dev.neatly.ui.collections

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import silas.dev.neatly.ui.components.AddProductButton
import silas.dev.neatly.ui.components.HeaderLabel
import silas.dev.neatly.ui.components.ProductCard
import silas.dev.neatly.ui.products.ProductInfoViewState
import silas.dev.neatly.ui.products.ProductWithCollectionViewState

@Composable
fun CollectionScreen(
    viewModel: CollectionScreenViewModel,
    onProductClick: (ProductWithCollectionViewState) -> Unit,
    onAddProductClick: (ProductWithCollectionViewState) -> Unit

) {
    val viewState by viewModel.collectionsScreenViewState.collectAsState()
    CollectionScreen(viewState, onProductClick, onAddProductClick)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CollectionScreen(
    viewState: CollectionScreenViewState,
    onProductClick: (ProductWithCollectionViewState) -> Unit,
    onAddProductClick: (ProductWithCollectionViewState) -> Unit = {}
) {
    Scaffold(floatingActionButton = {
        AddProductButton {
            onAddProductClick(
                ProductWithCollectionViewState(
                    -1,
                    viewState.collectionId
                )
            )
        }
    }, topBar = { topAppBar(viewState.collectionName) }
    )
    { innerPadding ->
        LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.padding(innerPadding)) {
            items(viewState.products.size) { index ->
                ProductCard(
                    viewState.products[index]
                ) {
                    onProductClick(
                        ProductWithCollectionViewState(
                            viewState.products[index].id,
                            viewState.collectionId
                        )
                    )
                }
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun topAppBar(title: String) {
    TopAppBar(title = { Text(text = title) })
}
