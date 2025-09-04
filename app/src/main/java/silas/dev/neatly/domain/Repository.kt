package silas.dev.neatly.domain


interface Repository {
    suspend fun addProduct(product: ProductInfo)
    suspend fun addCollection(collection: CollectionInfo)
    suspend fun getCollectionWithProducts(collectionId: Int): List<ProductInfo>
    suspend fun getProductsWithCollection(productId: Int): List<CollectionInfo>
}