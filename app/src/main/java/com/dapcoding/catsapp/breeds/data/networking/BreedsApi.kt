package com.dapcoding.catsapp.breeds.data.networking

import com.dapcoding.catsapp.BuildConfig
import com.dapcoding.catsapp.breeds.data.networking.dto.BreedDto
import retrofit2.http.GET
import retrofit2.http.Header

interface BreedsApi {
    @GET("breeds")
    suspend fun getBreeds(
//        @Query("page") page: Int = 0,
//        @Query("limit") limit: Int = 20,
        @Header("x-api-key") apiKey: String = BuildConfig.SERVER_API
    ): List<BreedDto>?
}