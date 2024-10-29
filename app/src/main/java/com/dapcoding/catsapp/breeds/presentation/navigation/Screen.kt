package com.dapcoding.catsapp.breeds.presentation.navigation

sealed class Screen(val route: String) {
    data object BreedList : Screen("breedList")
    data object BreedDetail : Screen("breedDetail/{breedId}") {
        fun createRoute(breedId: String) = "breedDetail/$breedId"
    }
}