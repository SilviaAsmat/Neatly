package silas.dev.neatly.ui.products

import androidx.compose.runtime.Immutable

@Immutable
sealed class PhotoInfoViewState {
    data class Data(
        val photoId: Int,
        val uri: String,
        val productId: Int
    ) : PhotoInfoViewState()

    data object Loading : PhotoInfoViewState()
    data object Empty : PhotoInfoViewState()
}