package silas.dev.neatly.data.room.lookup

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import silas.dev.neatly.data.room.product_info.ProductInfoEntity

@Dao
interface CollectionProductsCrossRefDao {
    @Transaction
    @Query("SELECT * FROM collections")
    fun getCollectionWithProducts(): List<CollectionWithProducts>

    @Transaction
    @Query("SELECT * FROM product_info")
    fun getProductsWithCollection(): List<ProductWithCollections>
}