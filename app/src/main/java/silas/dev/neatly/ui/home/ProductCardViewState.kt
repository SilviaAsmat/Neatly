package silas.dev.neatly.ui.home

import androidx.compose.runtime.Immutable

@Immutable
class ProductCardViewState(
    val name: String,
    val description: String
) {
    companion object {
        val NONE = ProductCardViewState(
            name = "",
            description = ""
        )
    }
}