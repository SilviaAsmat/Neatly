package silas.dev.neatly.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import silas.dev.neatly.data.room.NeatlyDatabase
import silas.dev.neatly.data.room.collection.CollectionsEntity
import silas.dev.neatly.data.room.product_info.ProductInfoEntity
import silas.dev.neatly.domain.CollectionInfo
import silas.dev.neatly.domain.CollectionWithProducts
import silas.dev.neatly.domain.ProductInfo
import silas.dev.neatly.domain.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val database: NeatlyDatabase
) : Repository {
    override suspend fun addProduct(product: ProductInfo) {
        val productEntity = ProductInfoEntity(
            productId = 0,
            name = product.name,
            description = product.description,
            upcCode = product.upcCode
        )
        database.productInfoDao().insertProduct(productEntity)
    }

    override suspend fun addCollection(collection: CollectionInfo) {
        val collectionEntity = CollectionsEntity(
            collectionId = 0,
            name = collection.name,
            description = collection.description
        )
        database.collectionsDao().insertCollection(collectionEntity)
    }

    override suspend fun getCollectionWithProducts(collectionId: Int): List<ProductInfo> {
        val products = database.collectionProductsCrossRefDao()
            .getCollectionWithProducts(collectionId).products
        val mapped = products.map { productEntity ->
            ProductInfo(
                name = productEntity.name,
                description = productEntity.description,
                upcCode = productEntity.upcCode,
                id = productEntity.productId
            )
        }
        return mapped
    }

    override suspend fun getCollections(): List<CollectionInfo> {
        val collections = database.collectionsDao().getAllCollections()
        val mapped = collections.map { collectionEntity ->
            CollectionInfo(
                collectionId = collectionEntity.collectionId,
                name = collectionEntity.name,
                description = collectionEntity.description
            )
        }
        return mapped
    }

    override suspend fun getProductsWithCollection(productId: Int): List<CollectionInfo> {
        val collections = database.collectionProductsCrossRefDao()
            .getProductsWithCollection(productId).collections
        val mapped = collections.map { collectionEntity ->
            CollectionInfo(
                collectionId = collectionEntity.collectionId,
                name = collectionEntity.name,
                description = collectionEntity.description
            )
        }
        return mapped
    }
    
    override suspend fun getCollectionsWithProducts(): Flow<List<CollectionWithProducts>> =
        database.collectionsDao().getAllCollectionsFlow().map { collectionEntities ->
            val mapped = collectionEntities.map { collectionEntity ->
                val products = database
                    .collectionProductsCrossRefDao()
                    .getCollectionWithProducts(collectionEntity.collectionId)
                // Map from entity to domain model
                CollectionWithProducts(
                    collection = CollectionInfo(
                        collectionId = collectionEntity.collectionId,
                        name = collectionEntity.name,
                        description = collectionEntity.description
                    ),
                    products = products.products.map { productEntity ->
                        ProductInfo(
                            name = productEntity.name,
                            description = productEntity.description,
                            upcCode = productEntity.upcCode,
                            id = productEntity.productId
                        )
                    }
                )
            }
            mapped

        }.flowOn(Dispatchers.IO)


    //TODO: Add Delete product/collection
}