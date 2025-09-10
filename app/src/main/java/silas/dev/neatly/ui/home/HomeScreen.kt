package silas.dev.neatly.ui.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import silas.dev.neatly.ui.components.CollectionRow
import silas.dev.neatly.ui.components.HomeButton
import silas.dev.neatly.ui.components.HomeTopBar
import silas.dev.neatly.ui.products.ProductInfoViewState

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel,
    onProductClick: (ProductInfoViewState) -> Unit,
) {
    val viewState by viewModel.homeScreenViewState.collectAsState()
    HomeScreen(viewState, onProductClick, viewModel::onAddCollection)
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
private fun HomeScreen(
    homeViewState: HomeScreenViewState,
    onProductClick: (ProductInfoViewState) -> Unit,
    onValueChange: (String) -> Unit
) {
    Scaffold(
        topBar = { HomeTopBar() },
        floatingActionButton = {HomeButton(onValueChange)}
    ) { innerPadding ->
        when (homeViewState) {
            is HomeScreenViewState.Data ->
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize() // TODO maybe remove?
                        .padding(innerPadding),
                ) {
                    items(homeViewState.rows.size) { index ->
                        CollectionRow(homeViewState.rows[index], onProductClick)
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