package silas.dev.neatly.domain

import kotlinx.coroutines.flow.Flow
import silas.dev.neatly.data.room.lookup.CollectionProductsCrossRef


interface Repository {
    suspend fun addProduct(product: ProductInfo): Long
    suspend fun addCollection(collection: CollectionInfo)
    suspend fun getCollectionWithProducts(collectionId: Int): List<ProductInfo>
    suspend fun getCollections(): List<CollectionInfo>
    suspend fun getProductsWithCollection(productId: Int): List<CollectionInfo>
    suspend fun getCollectionsWithProducts(): Flow<List<CollectionWithProducts>>
    suspend fun getCollectionByName(collectionName: String): CollectionInfo
    suspend fun getProduct(productId: Int): ProductInfo
    suspend fun addProductCollectionCrossRef(productId: Int, collectionId: Int)
    suspend fun getCollectionById(collectionId: Int): CollectionInfo
}

data class CollectionWithProducts(
    val collection: CollectionInfo,
    val products: List<ProductInfo>,
)