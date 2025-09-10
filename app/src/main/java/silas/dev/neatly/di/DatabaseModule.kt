package silas.dev.neatly.di;

import androidx.room.Room;
import javax.inject.Singleton
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import silas.dev.neatly.data.room.NeatlyDatabase;

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    companion object {
        private const val DATABASE_NAME = "neatly_database"
}

@Singleton
@Provides
fun provideAppDatabase(@ApplicationContext context: Context): NeatlyDatabase {
    return Room.databaseBuilder(
        context,
        NeatlyDatabase::class.java, DATABASE_NAME
    )
        .fallbackToDestructiveMigration()
        .build()
}

}
