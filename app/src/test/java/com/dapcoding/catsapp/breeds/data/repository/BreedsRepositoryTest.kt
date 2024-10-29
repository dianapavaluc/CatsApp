package com.dapcoding.catsapp.breeds.data.repository

import com.dapcoding.catsapp.breeds.data.networking.RemoteBreedDataSource
import com.dapcoding.catsapp.breeds.domain.model.Breed
import com.dapcoding.catsapp.breeds.domain.repository.FavoriteBreedsRepository
import com.dapcoding.catsapp.util.MainDispatcherRule
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class BreedsRepositoryImplTest {

    private var remoteDataSource = mock(RemoteBreedDataSource::class.java)
    private var localRepository = mock(FavoriteBreedsRepository::class.java)
    private var breedsRepository = BreedsRepositoryImpl(remoteDataSource, localRepository)

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun `getBreeds returns list of breeds with favorites marked`() = runTest {
        val localFavorites = listOf(testBreed.copy(id = "1"), testBreed.copy(id = "2"))
        val remoteBreeds =
            listOf(testBreed.copy(id = "1"), testBreed.copy(id = "3"), testBreed.copy(id = "4"))

        `when`(localRepository.getFavoriteBreeds()).thenReturn(flow { emit(localFavorites) })
        `when`(remoteDataSource.getBreeds()).thenReturn(remoteBreeds)

        val results = breedsRepository.getBreeds().firstOrNull().orEmpty()

        assertEquals(3, results.size)
        assertEquals(true, results[0].isFavorite)
        assertEquals(false, results[1].isFavorite)
        assertEquals(false, results[2].isFavorite)
    }
}

internal val testBreed = Breed(
    id = "abys",
    weightMetric = "3 - 5",
    name = "Abyssinian",
    temperament = listOf("Active", "Energetic", "Independent", "Intelligent", "Gentle"),
    origin = "Egypt",
    description = "The Abyssinian is easy to care for, and a joy to have in your home. They’re affectionate cats and love both people and other animals.",
    affectionLevel = 5,
    adaptability = 2,
    childFriendly = 1,
    dogFriendly = 3,
    energyLevel = 2,
    grooming = 2,
    healthIssues = 1,
    intelligence = 1,
    sheddingLevel = 3,
    socialNeeds = 1,
    strangerFriendly = 2,
    vocalisation = 1,
    referenceImageId = "0XYvRd7oD",
    wikipediaURL = "test url"
)
