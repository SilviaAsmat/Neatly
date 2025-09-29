package silas.dev.neatly.ui.products

import androidx.compose.runtime.Immutable

@Immutable
data class ProductInfoViewState(
    val id: Int,
    val name: String,
    val description: String,
    val photoInfo: PhotoInfoViewState
) {
    companion object {
        val NONE = ProductInfoViewState(
            id = -1,
            name = "Add Product Name",
            description = "Add Description",
            photoInfo = PhotoInfoViewState.NONE
        )
    }
}

@Immutable
data class PhotoInfoViewState(
    val photoId: Int,
    val uri: String,
    val productId: Int
){
    companion object {
        val NONE = PhotoInfoViewState(
            photoId = 0,
            uri = "",
            productId = 0
        )
    }
}
