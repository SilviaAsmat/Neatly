package silas.dev.neatly.ui.home

import androidx.compose.runtime.Immutable

@Immutable
sealed class CollectionsViewState {
    data class Data(val cards: List<ProductCardViewState>) : CollectionsViewState()
    data object Loading : CollectionsViewState()
    data object Empty : CollectionsViewState()
}