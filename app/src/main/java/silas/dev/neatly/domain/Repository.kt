package silas.dev.neatly.domain

import kotlinx.coroutines.flow.Flow


interface Repository {
    suspend fun addProduct(product: ProductInfo)
    suspend fun addCollection(collection: CollectionInfo)
    suspend fun getCollectionWithProducts(collectionId: Int): List<ProductInfo>
    suspend fun getCollections(): List<CollectionInfo>
    suspend fun getProductsWithCollection(productId: Int): List<CollectionInfo>
    suspend fun getCollectionsWithProducts(): Flow<List<CollectionWithProducts>>
}

data class CollectionWithProducts(
    val collection: CollectionInfo,
    val products: List<ProductInfo>,
)