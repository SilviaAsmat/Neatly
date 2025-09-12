package silas.dev.neatly.data.room.collection

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CollectionsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCollection(collection: CollectionsEntity)
    @Delete
    fun deleteCollection(collection: CollectionsEntity)
    @Query("SELECT * FROM collections")
    fun getAllCollections(): List<CollectionsEntity>
    @Query("SELECT * FROM collections")
    fun getAllCollectionsFlow(): Flow<List<CollectionsEntity>>
    @Query("SELECT * FROM collections WHERE name = :name")
    fun getCollectionByName(name: String): CollectionsEntity
    @Query("SELECT * FROM collections WHERE collectionId = :id")
    fun getCollectionById(id: Int): CollectionsEntity

}