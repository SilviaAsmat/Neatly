package silas.dev.neatly.ui.products

import androidx.compose.runtime.Immutable

@Immutable
data class ProductInfoViewState(
    val id: Int,
    val name: String,
    val description: String,
) {
    companion object {
        val NONE = ProductInfoViewState(
            id = -1,
            name = "Add Product Name",
            description = "Add Description",
        )
    }
}
