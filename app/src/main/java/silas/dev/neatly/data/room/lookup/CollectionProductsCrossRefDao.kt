package silas.dev.neatly.data.room.lookup

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface CollectionProductsCrossRefDao {
    @Transaction
    @Query("SELECT collectionId FROM collections")
    suspend fun getCollectionWithProducts(collectionId: Int): CollectionWithProducts

    @Transaction
    @Query("SELECT productId FROM product_info")
    suspend fun getProductsWithCollection(productId: Int): ProductWithCollections
}