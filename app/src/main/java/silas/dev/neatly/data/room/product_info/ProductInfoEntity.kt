package silas.dev.neatly.data.room.product_info

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "product_info"
)
data class ProductInfoEntity (
    @PrimaryKey(autoGenerate = true)
    val productId: Int,
    @ColumnInfo(name = "Name")
    val name: String,
    @ColumnInfo(name = "Description")
    val description: String,
    @ColumnInfo(name = "UPC Code")
    val upcCode: String
)