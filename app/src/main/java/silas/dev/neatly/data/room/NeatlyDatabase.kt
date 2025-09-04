package silas.dev.neatly.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import silas.dev.neatly.data.room.collection.CollectionsDao
import silas.dev.neatly.data.room.collection.CollectionsEntity
import silas.dev.neatly.data.room.lookup.CollectionProductsCrossRef
import silas.dev.neatly.data.room.lookup.CollectionProductsCrossRefDao
import silas.dev.neatly.data.room.product_info.ProductInfoDao
import silas.dev.neatly.data.room.product_info.ProductInfoEntity

@Database(entities = [
    CollectionsEntity::class, ProductInfoEntity::class, CollectionProductsCrossRef::class
                     ], version = 1)
abstract class NeatlyDatabase : RoomDatabase(){
    abstract fun collectionsDao(): CollectionsDao
    abstract fun productInfoDao(): ProductInfoDao
    abstract fun collectionProductsCrossRefDao(): CollectionProductsCrossRefDao
}