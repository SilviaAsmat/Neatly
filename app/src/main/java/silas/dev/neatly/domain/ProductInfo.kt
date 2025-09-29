package silas.dev.neatly.domain

data class ProductInfo (
    val id: Int,
    val name: String,
    val description: String,
    val upcCode: String,
    val photoInfo: PhotoInfo
)