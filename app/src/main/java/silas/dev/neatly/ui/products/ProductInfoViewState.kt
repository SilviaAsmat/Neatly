package silas.dev.neatly.ui.products

import androidx.compose.runtime.Immutable

@Immutable
class ProductInfoViewState(
    val id: Int,
    val name: String,
    val description: String
) {
    companion object Companion {
        val NONE = ProductInfoViewState(
            id = 0,
            name = "",
            description = ""
        )
    }
}