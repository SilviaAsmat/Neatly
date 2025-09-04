package silas.dev.neatly.data.room.product_info

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert

@Dao
interface ProductInfoDao {
    @Insert(onConflict = 1)
    fun insertProduct(product: ProductInfoEntity)
    @Delete
    fun deleteProduct(product: ProductInfoEntity)
}