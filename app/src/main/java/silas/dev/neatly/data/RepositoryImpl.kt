package silas.dev.neatly.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import silas.dev.neatly.data.room.NeatlyDatabase
import silas.dev.neatly.data.room.collection.CollectionsEntity
import silas.dev.neatly.data.room.lookup.CollectionProductsCrossRef
import silas.dev.neatly.data.room.product_info.ProductInfoEntity
import silas.dev.neatly.domain.CollectionInfo
import silas.dev.neatly.domain.CollectionWithProducts
import silas.dev.neatly.domain.CrossRefInfo
import silas.dev.neatly.domain.ProductInfo
import silas.dev.neatly.domain.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val database: NeatlyDatabase
) : Repository {
    override suspend fun addProduct(product: ProductInfo): Long {
        val entityId = if (product.id > 0) {
            product.id
        } else {
            0
        }
        val productEntity = ProductInfoEntity(
            productId = entityId,
            name = product.name,
            description = product.description,
            upcCode = product.upcCode
        )
        return database.productInfoDao().insertProduct(productEntity)
    }

    override suspend fun addCollection(collection: CollectionInfo) {
        val collectionEntity = CollectionsEntity(
            collectionId = collection.collectionId,
            name = collection.name,
            description = collection.description
        )
        database.collectionsDao().insertCollection(collectionEntity)
    }

    override suspend fun getCollections(): List<CollectionInfo> { // TODO: Convert to Flow
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

    override suspend fun getProductsWithCollection(productId: Int): List<CollectionInfo> { // TODO: Convert to Flow
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

    override suspend fun getCollectionByName(collectionName: String): CollectionInfo {
        val collectionEntity = database.collectionsDao().getCollectionByName(collectionName)
        return CollectionInfo(
            collectionId = collectionEntity.collectionId,
            name = collectionEntity.name,
            description = collectionEntity.description
        )
    }

    override suspend fun getProduct(productId: Int): ProductInfo {
        val productEntity = database.productInfoDao().getProduct(productId)
        return ProductInfo(
            name = productEntity.name,
            description = productEntity.description,
            upcCode = productEntity.upcCode,
            id = productEntity.productId
        )
    }

    override suspend fun addProductCollectionCrossRef(productId: Int, collectionId: Int) {
        val crossRef = CollectionProductsCrossRef(
            productId = productId,
            collectionId = collectionId
        )
        database.collectionProductsCrossRefDao().addProductCollectionCrossRef(crossRef)
    }

    override suspend fun getCollectionById(collectionId: Int): CollectionInfo {
        val collectionEntity = database.collectionsDao().getCollectionById(collectionId)
        return CollectionInfo(
            collectionId = collectionEntity.collectionId,
            name = collectionEntity.name,
            description = collectionEntity.description
        )
    }


    //TODO: Add Delete product/collection
    override suspend fun deleteProduct(product: ProductInfo) {
        val productEntity = ProductInfoEntity(
            productId = product.id,
            name = product.name,
            description = product.description,
            upcCode = product.upcCode
        ) // TODO use entity instead?
        database.productInfoDao().deleteProduct(product.id)
    }

    override suspend fun deleteCollection(collection: CollectionInfo) {
        val collectionEntity = CollectionsEntity(
            collectionId = collection.collectionId,
            name = collection.name,
            description = collection.description
        )
        database.collectionsDao().deleteCollection(collectionEntity)
    }

    override suspend fun deleteProductCollectionCrossRef(crossRef: CrossRefInfo) {
        val crossRefEntity = CollectionProductsCrossRef(
            productId = crossRef.productId,
            collectionId = crossRef.collectionId
        )
        database.collectionProductsCrossRefDao()
            .deleteProductCollectionCrossRef(crossRefEntity)
    }

    override suspend fun getCollectionWithProductsFlow(collectionId: Int): Flow<List<ProductInfo>> =
        database.collectionProductsCrossRefDao()
            .getCollectionWithProductsFlow(collectionId) // TODO CRASH
            .map { collectionWithProducts -> // 'it' is the emitted CollectionWithProducts object
                collectionWithProducts?.products?.map { productEntity ->
                    // This inner map converts each ProductEntity to a ProductInfo
                    ProductInfo(
                        name = productEntity.name,
                        description = productEntity.description,
                        upcCode = productEntity.upcCode,
                        id = productEntity.productId
                    )
                } ?: emptyList()
            }
            .flowOn(Dispatchers.IO)


    override suspend fun getAllCollectionsWithProductsFlow(): Flow<List<CollectionWithProducts>> =
        database.collectionProductsCrossRefDao()
            .getAllCollectionsWithProductsFlow()
            .map { listOfCollectionsWithProducts ->
                listOfCollectionsWithProducts.map { entity ->
                    CollectionWithProducts(
                        collection = CollectionInfo(
                            collectionId = entity.collection.collectionId,
                            name = entity.collection.name,
                            description = entity.collection.description
                        ),
                        products = entity.products.map { productEntity ->
                            ProductInfo(
                                id = productEntity.productId,
                                name = productEntity.name,
                                description = productEntity.description,
                                upcCode = productEntity.upcCode
                            )
                        }
                    )
                }
            }
            .flowOn(Dispatchers.IO)

    override suspend fun getProductsInCollection(collectionId: Int): List<ProductInfo> {
        val products = database
            .collectionProductsCrossRefDao()
            .getCollectionWithProductsSuspend(collectionId)
            .products
        return products.map { productEntity ->
            ProductInfo(
                id = productEntity.productId,
                name = productEntity.name,
                description = productEntity.description,
                upcCode = productEntity.upcCode,
            )
        }
    }

//    override suspend fun getCollectionWithProducts(collectionId: Int): List<ProductInfo> {
//        database.collectionProductsCrossRefDao()
//            .getCollectionWithProductsFlow(collectionId)
//            .map { collectionWithProducts -> // 'it' is the emitted CollectionWithProducts object
//                collectionWithProducts.products.map { productEntity ->
//                    // This inner map converts each ProductEntity to a ProductInfo
//                    ProductInfo(
//                        name = productEntity.name,
//                        description = productEntity.description,
//                        upcCode = productEntity.upcCode,
//                        id = productEntity.productId
//                    )
//                }
//            }
//    }


}

