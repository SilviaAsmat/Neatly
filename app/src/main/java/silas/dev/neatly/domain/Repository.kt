package silas.dev.neatly.domain

import kotlinx.coroutines.flow.Flow


interface Repository {
    suspend fun addProduct(product: ProductInfo): Long
    suspend fun addCollection(collection: CollectionInfo)
    suspend fun getCollectionWithProductsFlow(collectionId: Int): Flow<List<ProductInfo>>
    suspend fun getCollections(): List<CollectionInfo>
    suspend fun getProductsWithCollection(productId: Int): List<CollectionInfo>
    suspend fun getCollectionByName(collectionName: String): CollectionInfo
    suspend fun getProduct(productId: Int): ProductInfo
    suspend fun addProductCollectionCrossRef(productId: Int, collectionId: Int)
    suspend fun getCollectionById(collectionId: Int): CollectionInfo
    suspend fun deleteProduct(product: ProductInfo)
    suspend fun deleteCollection(collection: CollectionInfo)
    suspend fun deleteProductCollectionCrossRef(crossRef: CrossRefInfo)
    suspend fun getAllCollectionsWithProductsFlow(): Flow<List<CollectionWithProducts>>
    suspend fun getProductsInCollection(collectionId: Int): List<ProductInfo>
}

data class CollectionWithProducts(
    val collection: CollectionInfo,
    val products: List<ProductInfo>,
)