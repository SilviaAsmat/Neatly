package silas.dev.neatly.ui.home

import androidx.compose.runtime.Immutable
import silas.dev.neatly.ui.collections.CollectionRowViewState

@Immutable
sealed class HomeScreenViewState {
    data class Data(val rows: List<CollectionRowViewState>) : HomeScreenViewState()
    data object Loading : HomeScreenViewState()
    data object Empty : HomeScreenViewState()
}