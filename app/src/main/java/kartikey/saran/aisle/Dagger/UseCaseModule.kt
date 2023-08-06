package kartikey.saran.aisle.Dagger

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kartikey.saran.aisle.API.APIRepository
import kartikey.saran.aisle.API.UseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    fun providesDataUseCase(apiRepository: APIRepository): UseCase {
        return UseCase(apiRepository)
    }
}