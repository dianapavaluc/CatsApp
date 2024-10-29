package com.dapcoding.catsapp.breeds.presentation.breedslist

import androidx.compose.runtime.Immutable
import com.dapcoding.catsapp.breeds.domain.model.Breed

@Immutable
data class BreedState(
    val isListLoading: Boolean = false,
    val error: String? = null,
    val breeds: List<Breed> = emptyList(),
    val selectedBreed: Breed? = null
)
