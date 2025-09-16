package silas.dev.neatly.data.room.product_info

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProductInfoDao {
    @Insert(onConflict = 1)
    fun insertProduct(product: ProductInfoEntity): Long
    @Query("DELETE FROM product_info WHERE productId = :id")
    fun deleteProduct(id: Int)
    @Query("SELECT * FROM product_info WHERE productId = :id")
    fun getProduct(id: Int): ProductInfoEntity

}