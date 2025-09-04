package silas.dev.neatly.data.room.collection

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "collections"
)
data class CollectionsEntity(
    @PrimaryKey(autoGenerate = true)
    val collectionId: Int,
    @ColumnInfo(name = "Name")
    val name: String,
    @ColumnInfo(name = "Description")
    val description: String
)