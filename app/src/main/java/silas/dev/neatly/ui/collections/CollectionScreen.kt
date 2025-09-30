package silas.dev.neatly.ui.collections

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import silas.dev.neatly.ui.components.AddProductButton
import silas.dev.neatly.ui.components.ProductCard
import silas.dev.neatly.ui.home.HomeScreen
import silas.dev.neatly.ui.products.ProductWithCollectionViewState

@Composable
fun CollectionScreen(
    viewModel: CollectionScreenViewModel,
    onProductClick: (ProductWithCollectionViewState) -> Unit,
    onAddProductClick: (ProductWithCollectionViewState) -> Unit,
    navController: NavHostController

) {
    val viewState by viewModel.collectionsScreenViewState.collectAsState()
    val showDeleteDialog by viewModel.showDeleteDialog.collectAsState()
    CollectionScreen(
        viewState,
        onProductClick,
        onAddProductClick,
        viewModel::onDeleteIconClicked,
        showDeleteDialog,
        viewModel::onDismissDelete,
        viewModel::onDeleteConfirmed,
        navController
        )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CollectionScreen(
    viewState: CollectionScreenViewState,
    onProductClick: (ProductWithCollectionViewState) -> Unit,
    onAddProductClick: (ProductWithCollectionViewState) -> Unit = {},
    onDeleteIconClicked: () -> Unit,
    showDeleteDialog: Boolean,
    onDismissDelete: () -> Unit = {},
    onConfirmDelete: () -> Unit = {},
    navController: NavHostController
) {

    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { },
            title = { Text("Delete Collection") },
            text = {
                Text(
                    text = "Are you sure you want to delete this collection?"
                )
            },
            confirmButton = {
                TextButton(
                    onClick = { onConfirmDelete(); navController.popBackStack() }
                ) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                TextButton(onClick = { onDismissDelete() }) {
                    Text("Dismiss")
                }
            }
        )
    }
    Scaffold(floatingActionButton = {
        AddProductButton {
            onAddProductClick(
                ProductWithCollectionViewState(
                    -1,
                    viewState.collectionId
                )
            )
        }
    }, topBar = {
        CollectionTopAppBar(
            viewState.collectionName, onEdit = { },
            onDeleteIconClicked = onDeleteIconClicked
        )
    }
    )
    { innerPadding ->
        LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.padding(innerPadding)) {
            items(viewState.products.size) { index ->
                ProductCard(
                    viewState.products[index]
                ) {
                    onProductClick(
                        ProductWithCollectionViewState(
                            viewState.products[index].productInfo.id,
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
fun CollectionTopAppBar(name: String, onDeleteIconClicked: () -> Unit, onEdit: () -> Unit) {
    TopAppBar(title = { Text(text = name) }, actions = {
        IconButton(onClick = onDeleteIconClicked) {
            Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = "Delete Collection"
            )
        }
    })

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun topAppBar(title: String) {
    TopAppBar(title = { Text(text = title) })
}
