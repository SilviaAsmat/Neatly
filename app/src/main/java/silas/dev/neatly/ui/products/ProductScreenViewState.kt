package silas.dev.neatly.ui.products

import androidx.compose.runtime.Immutable

@Immutable
sealed class ProductScreenViewState() {
    data class Empty(val emptyProduct: ProductInfoViewState) : ProductScreenViewState()
    data class Data(val product: ProductInfoViewState) : ProductScreenViewState()
}