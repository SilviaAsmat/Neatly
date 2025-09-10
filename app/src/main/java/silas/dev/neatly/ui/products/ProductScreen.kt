package silas.dev.neatly.ui.products

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun ProductScreen (
    viewModel: ProductScreenViewModel
){
    val viewState by viewModel.productInfoViewState.collectAsState()
    ProductScreen(viewState)
}

@Composable
private fun ProductScreen(
    productInfo: ProductInfoViewState
){

}