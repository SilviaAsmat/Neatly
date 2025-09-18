package silas.dev.neatly.data.room.photos

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import silas.dev.neatly.data.room.product_info.ProductInfoEntity

@Entity(
    tableName = "photos",
    foreignKeys = [
        ForeignKey(
            entity = ProductInfoEntity::class,
            parentColumns = ["productId"],
            childColumns = ["productId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class PhotoInfoEntity(
    @PrimaryKey(autoGenerate = true)
    val photoId: Int,
    val uri: String,
    val productId: Int,
    )