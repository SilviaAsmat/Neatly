package silas.dev.neatly.data.room.lookup

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation
import silas.dev.neatly.data.room.collection.CollectionsEntity
import silas.dev.neatly.data.room.product_info.ProductInfoEntity

@Entity(tableName = "cross_ref", primaryKeys = ["collectionId","productId"])
data class CollectionProductsCrossRef(
    val productId: Int,
    val collectionId: Int
)

data class CollectionWithProducts(
    @Embedded val collection: CollectionsEntity,
    @Relation(
        parentColumn = "collectionId",
        entityColumn = "productId",
        associateBy = Junction(CollectionProductsCrossRef::class)
    )
    val products: List<ProductInfoEntity>
)

data class ProductWithCollections(
    @Embedded val product: ProductInfoEntity,
    @Relation(
        parentColumn = "productId",
        entityColumn = "collectionId",
        associateBy = Junction(CollectionProductsCrossRef::class)
    )
    val collections: List<CollectionsEntity>
)