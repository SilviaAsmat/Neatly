package silas.dev.neatly.data.room.photos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PhotoInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPhoto(photo: PhotoInfoEntity)
    @Query("SELECT * FROM photos WHERE productId = :productId")
    fun getPhotosByProductId(productId: Int): List<PhotoInfoEntity>

}