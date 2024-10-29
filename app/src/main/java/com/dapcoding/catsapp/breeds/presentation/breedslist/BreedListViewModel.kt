package com.dapcoding.catsapp.breeds.presentation.breedslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dapcoding.catsapp.breeds.domain.model.Breed
import com.dapcoding.catsapp.breeds.domain.usecases.DeleteBreedFromFavouriteUseCase
import com.dapcoding.catsapp.breeds.domain.usecases.GetBreedsUseCase
import com.dapcoding.catsapp.breeds.domain.usecases.SaveBreedToFavouriteUseCase
import com.dapcoding.catsapp.breeds.presentation.breeddetails.BreedDetailsAction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BreedListViewModel @Inject constructor(
    private val getBreedsUseCase: GetBreedsUseCase,
    private val saveBreedToFavouriteUseCase: SaveBreedToFavouriteUseCase,
    private val deleteBreedFromFavouriteUseCase: DeleteBreedFromFavouriteUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(BreedState())
    val state = _state
        .onStart { loadBreeds() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            BreedState()
        )

    private val _events = Channel<BreedListEvent>()
    val events = _events.receiveAsFlow()

    fun onAction(action: Any) {
        when (action) {
            is BreedListAction -> handleBreedListAction(action)
            is BreedDetailsAction -> handleBreedsDetailAction(action)
        }
    }

    private fun handleBreedListAction(action: BreedListAction) {
        when (action) {
            is BreedListAction.OnBreedClick -> {
                selectBreed(action.breed)
            }

            is BreedListAction.OnFavoriteClick -> {
                toggleFavorites(action.breed)
            }
        }
    }

    private fun handleBreedsDetailAction(action: BreedDetailsAction) {
        when (action) {
            is BreedDetailsAction.OnDetailsBack -> {
                // Placeholder
            }

            is BreedDetailsAction.OnFavoriteClick -> {
                toggleFavorites(action.breed)
            }

            is BreedDetailsAction.OnWikipediaLink -> {
                // Placeholder
            }
        }
    }

    private fun toggleFavorites(breed: Breed) {
        viewModelScope.launch {
            if (!breed.isFavorite) {
                saveBreedToFavouriteUseCase(breed)
            } else {
                deleteBreedFromFavouriteUseCase(breed)
            }

            val updatedBreeds = state.value.breeds.map { currentBreed ->
                if (currentBreed.id == breed.id) {
                    currentBreed.copy(isFavorite = !breed.isFavorite)
                } else {
                    currentBreed
                }
            }

            _state.value = _state.value.copy(
                breeds = updatedBreeds
            )
        }
    }

    private fun selectBreed(breed: Breed?) {
        _state.update { it.copy(selectedBreed = breed) }
    }

    private fun loadBreeds() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isListLoading = true
                )
            }

            try {
                getBreedsUseCase().collect { result ->
                    if (result.isNotEmpty()) {
                        _state.update {
                            it.copy(
                                isListLoading = false,
                                breeds = result
                            )
                        }
                    } else {
                        _events.send(BreedListEvent.Error("\"Failed to retrieve the data, please try again later!"))
                    }
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isListLoading = false,
                    )
                }
                _events.send(BreedListEvent.Error("\"Failed to retrieve the data, please try again later!"))
            }
        }
    }
}