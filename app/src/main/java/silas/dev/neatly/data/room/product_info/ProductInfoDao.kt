package silas.dev.neatly.data.room.product_info

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProductInfoDao {
    @Insert(onConflict = 1)
    fun insertProduct(product: ProductInfoEntity): Long
    @Delete
    fun deleteProduct(product: ProductInfoEntity)
    @Query("SELECT * FROM product_info WHERE productId = :id")
    fun getProduct(id: Int): ProductInfoEntity

}