package silas.dev.neatly.ui.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import silas.dev.neatly.ui.collections.CollectionInfoViewState
import silas.dev.neatly.ui.components.AddCollectionButton
import silas.dev.neatly.ui.components.CollectionRow
import silas.dev.neatly.ui.components.HomeTopBar
import silas.dev.neatly.ui.products.ProductWithCollectionViewState

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel,
    onProductClick: (ProductWithCollectionViewState) -> Unit,
    onCollectionClick: (CollectionInfoViewState) -> Unit
) {
    val viewState by viewModel.homeScreenViewState.collectAsState()
    HomeScreen(viewState, onProductClick, viewModel::onAddCollection, onCollectionClick)
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
private fun HomeScreen(
    homeViewState: HomeScreenViewState,
    onProductClick: (ProductWithCollectionViewState) -> Unit,
    onValueChange: (String) -> Unit,
    onCollectionClick: (CollectionInfoViewState) -> Unit
) {
    Scaffold(
        topBar = { HomeTopBar() },
        floatingActionButton = {AddCollectionButton(onValueChange)},
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        when (homeViewState) {
            is HomeScreenViewState.Data ->
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize() // TODO maybe remove?
                        .padding(innerPadding),
                ) {
                    items(homeViewState.rows.size) { index ->
                        CollectionRow(homeViewState.rows[index], onProductClick, onCollectionClick)
                    }
                }
            HomeScreenViewState.Empty -> LazyColumn(
                modifier = Modifier
                    .fillMaxSize() // TODO maybe remove?
                    .padding(innerPadding),
            ) {}
            HomeScreenViewState.Loading -> LazyColumn(){}// TODO add shimmer state
        }
    }
}