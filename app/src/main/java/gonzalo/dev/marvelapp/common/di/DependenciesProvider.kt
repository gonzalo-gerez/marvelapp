package gonzalo.dev.marvelapp.common.di

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import gonzalo.dev.marvelapp.BuildConfig
import gonzalo.dev.marvelapp.common.annotation.Timeout
import gonzalo.dev.marvelapp.home.data.repository.CharacterRepositoryImpl
import gonzalo.dev.marvelapp.home.data.services.HomeService
import gonzalo.dev.marvelapp.home.data.source.CharacterDataSource
import gonzalo.dev.marvelapp.home.data.source.remote.CharacterRemoteSource
import gonzalo.dev.marvelapp.home.domain.repository.CharacterRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class CharacterRepositoryQ

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class CharacterRemoteSourceQ

    @CharacterRepositoryQ
    @Provides
    fun provideCharacterRepository(
        @CharacterRemoteSourceQ characterDataSource: CharacterDataSource
    ): CharacterRepository {
        return CharacterRepositoryImpl(characterDataSource)
    }

    @CharacterRemoteSourceQ
    @Provides
    fun provideCharacterRemoteSource(homeService: HomeService): CharacterDataSource {
        return CharacterRemoteSource(homeService)
    }
}
/*@Module
@InstallIn(ActivityRetainedComponent::class)
class UseCaseModule {

    @Provides
    fun provideFetchCharacterUseCase(homeService: HomeService): FetchCharacterUseCase = FetchCharacterUseCase(
        CharacterRepositoryImpl(homeService)
    )
}*/

@Module
@InstallIn(SingletonComponent::class)
class NetworkingModule {

    @Provides
    fun provideGsonInstance() = GsonBuilder()
        .enableComplexMapKeySerialization()
        .serializeNulls()
        .setPrettyPrinting()
        .setVersion(1.0)
        .create()

    @Provides
    fun provideHttpInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(provideGsonInstance()))
            .client(okHttpClient)
            .build()

    @Provides
    fun provideOkHttpInstance(interceptor: HttpLoggingInterceptor, timeout: Timeout) =
        OkHttpClient.Builder()
            .connectTimeout(timeout.timeout, TimeUnit.SECONDS)
            .readTimeout(timeout.timeout, TimeUnit.SECONDS)
            .writeTimeout(timeout.timeout, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()

    @Provides
    fun provideHomeService(): HomeService {
        val timeOut = HomeService::class.java.getAnnotation(Timeout::class.java)
        return provideRetrofitInstance(provideOkHttpInstance(provideHttpInterceptor(), timeOut!!))
            .create(HomeService::class.java)
    }
}