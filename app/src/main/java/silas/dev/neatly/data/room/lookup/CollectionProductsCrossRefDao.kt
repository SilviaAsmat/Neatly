package silas.dev.neatly.data.room.lookup

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface CollectionProductsCrossRefDao {
    @Transaction
    @Query("SELECT * FROM collections WHERE collectionId = :collectionId")
    fun getCollectionWithProductsFlow(collectionId: Int): Flow<CollectionWithProducts?>

    @Transaction
    @Query("SELECT * FROM collections WHERE collectionId = :collectionId")
    suspend fun getCollectionWithProductsSuspend(collectionId: Int): CollectionWithProducts

    @Transaction
    @Query("SELECT * FROM collections")
    fun getAllCollectionsWithProductsFlow(): Flow<List<CollectionWithProducts>>

    @Transaction
    @Query("SELECT * FROM product_info WHERE productId = :productId")
    suspend fun getProductsWithCollection(productId: Int): ProductWithCollections

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProductCollectionCrossRef(crossRef: CollectionProductsCrossRef)

    @Delete()
    suspend fun deleteProductCollectionCrossRef(crossRef: CollectionProductsCrossRef)

}