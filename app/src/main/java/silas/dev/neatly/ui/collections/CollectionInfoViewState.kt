package silas.dev.neatly.ui.collections

import androidx.compose.runtime.Immutable

@Immutable
class CollectionInfoViewState(
    val id: Int,
    val name: String,
    val description: String
) {
    companion object Companion {
        val NONE = CollectionInfoViewState(
            id = 0,
            name = "",
            description = ""
        )
    }
}