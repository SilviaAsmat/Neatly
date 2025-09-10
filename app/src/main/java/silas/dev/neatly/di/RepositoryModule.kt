package silas.dev.neatly.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import silas.dev.neatly.data.RepositoryImpl
import silas.dev.neatly.domain.Repository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun provideMoviesRepository(
        repo: RepositoryImpl
    ): Repository
}