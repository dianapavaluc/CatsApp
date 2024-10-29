package com.dapcoding.catsapp.di

import android.app.Application
import androidx.room.Room
import com.dapcoding.catsapp.BuildConfig
import com.dapcoding.catsapp.breeds.data.database.FavoriteBreedsDatabase
import com.dapcoding.catsapp.breeds.data.networking.BreedsApi
import com.dapcoding.catsapp.breeds.data.networking.RemoteBreedDataSource
import com.dapcoding.catsapp.breeds.data.repository.BreedsRepositoryImpl
import com.dapcoding.catsapp.breeds.data.repository.FavoriteBreedsRepositoryImpl
import com.dapcoding.catsapp.breeds.data.networking.BreedDataSource
import com.dapcoding.catsapp.breeds.domain.repository.BreedsRepository
import com.dapcoding.catsapp.breeds.domain.repository.FavoriteBreedsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideBreedsApi(): BreedsApi {
        val connectTimeout: Long = 40
        val readTimeout: Long = 40

        fun provideHttpClient(): OkHttpClient {
            val okHttpClientBuilder = OkHttpClient.Builder()
                .connectTimeout(connectTimeout, TimeUnit.SECONDS)
                .readTimeout(readTimeout, TimeUnit.SECONDS)
            if (BuildConfig.DEBUG) {
                val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
                okHttpClientBuilder.addInterceptor(httpLoggingInterceptor)
            }
            okHttpClientBuilder.build()
            return okHttpClientBuilder.build()
        }
        return Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_URL)
            .client(provideHttpClient())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @Singleton
    @Provides
    fun provideRemoteBreedDataSource(api: BreedsApi): BreedDataSource {
        return RemoteBreedDataSource(api)
    }

    @Singleton
    @Provides
    fun provideFavoriteBreedsDatabase(app: Application): FavoriteBreedsDatabase {
        return Room.databaseBuilder(
            app,
            FavoriteBreedsDatabase::class.java,
            "favorite_breed.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideFavoriteBreedsRepository(db: FavoriteBreedsDatabase): FavoriteBreedsRepository {
        return FavoriteBreedsRepositoryImpl(db.dao)
    }

    @Singleton
    @Provides
    fun provideBreedsRepository(
        remote: RemoteBreedDataSource,
        local: FavoriteBreedsRepository,
    ): BreedsRepository {
        return BreedsRepositoryImpl(remote, local)
    }

}