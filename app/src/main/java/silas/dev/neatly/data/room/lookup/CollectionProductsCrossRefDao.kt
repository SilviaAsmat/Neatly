package silas.dev.neatly.data.room.lookup

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface CollectionProductsCrossRefDao {
    @Transaction
    @Query("SELECT * FROM collections WHERE collectionId = :collectionId")
    suspend fun getCollectionWithProducts(collectionId: Int): CollectionWithProducts

    @Transaction
    @Query("SELECT * FROM product_info WHERE productId = :productId")
    suspend fun getProductsWithCollection(productId: Int): ProductWithCollections
}