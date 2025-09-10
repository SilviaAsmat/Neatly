package silas.dev.neatly.data.room.collection

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CollectionsDao {
    @Insert(onConflict = 1)
    fun insertCollection(collection: CollectionsEntity)
    @Delete
    fun deleteCollection(collection: CollectionsEntity)
    @Query("SELECT * FROM collections")
    fun getAllCollections(): List<CollectionsEntity>

    @Query("SELECT * FROM collections")
    fun getAllCollectionsFlow(): Flow<List<CollectionsEntity>>

}