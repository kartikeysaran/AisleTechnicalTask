package kartikey.saran.aisle.Dagger

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kartikey.saran.aisle.API.APIRepository
import kartikey.saran.aisle.API.APIService

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideRepository(apiService: APIService): APIRepository {
        return APIRepository(apiService)
    }
}