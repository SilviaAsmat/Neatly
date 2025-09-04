package silas.dev.neatly.data

import silas.dev.neatly.data.room.NeatlyDatabase
import silas.dev.neatly.data.room.collection.CollectionsEntity
import silas.dev.neatly.data.room.product_info.ProductInfoEntity
import silas.dev.neatly.domain.CollectionInfo
import silas.dev.neatly.domain.ProductInfo
import silas.dev.neatly.domain.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val database: NeatlyDatabase
) : Repository {
    override suspend fun addProduct(product: ProductInfo){
        val productEntity = ProductInfoEntity(
            productId = 0,
            name = product.name,
            description = product.description,
            upcCode = product.upcCode
        )
        database.productInfoDao().insertProduct(productEntity)
    }

    override suspend fun addCollection(collection: CollectionInfo){
        val collectionEntity = CollectionsEntity(
            collectionId = 0,
            name = collection.name,
            description = collection.description
        )
        database.collectionsDao().insertCollection(collectionEntity)
    }

    override suspend fun getCollectionWithProducts(collectionId: Int): List<ProductInfo> {
        val products = database.collectionProductsCrossRefDao().getCollectionWithProducts(collectionId).products
        val mapped = products.map { productEntity ->
            ProductInfo(
            name = productEntity.name,
            description = productEntity.description,
            upcCode = productEntity.upcCode
        ) }
        return mapped
    }

    override suspend fun getProductsWithCollection(productId: Int): List<CollectionInfo> {
        val collections = database.collectionProductsCrossRefDao().getProductsWithCollection(productId).collections
        val mapped = collections.map { collectionEntity ->
            CollectionInfo(
                collectionId = collectionEntity.collectionId,
                name = collectionEntity.name,
                description = collectionEntity.description
            ) }
        return mapped
    }

    //TODO: Add Delete product/collection
}