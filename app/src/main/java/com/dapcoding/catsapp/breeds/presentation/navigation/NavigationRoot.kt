package com.dapcoding.catsapp.breeds.presentation.navigation

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dapcoding.catsapp.breeds.presentation.breeddetails.BreedDetailScreen
import com.dapcoding.catsapp.breeds.presentation.breeddetails.BreedDetailsAction
import com.dapcoding.catsapp.breeds.presentation.breedslist.BreedListAction
import com.dapcoding.catsapp.breeds.presentation.breedslist.BreedListEvent
import com.dapcoding.catsapp.breeds.presentation.breedslist.BreedListScreen
import com.dapcoding.catsapp.breeds.presentation.breedslist.BreedListViewModel
import com.dapcoding.catsapp.breeds.presentation.util.ObserveAsEvents

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    viewModel: BreedListViewModel = hiltViewModel<BreedListViewModel>()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val navController = rememberNavController()
    val context = LocalContext.current

    ObserveAsEvents(events = viewModel.events) { event ->
        when (event) {
            is BreedListEvent.Error -> {
                Toast.makeText(
                    context,
                    event.error,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = Screen.BreedList.route
    ) {
        composable(Screen.BreedList.route) {
            BreedListScreen(
                state = state,
                onAction = { action ->
                    when (action) {
                        is BreedListAction.OnBreedClick -> {
                            viewModel.onAction(action)
                            navController.navigate(Screen.BreedDetail.createRoute(action.breed.id))
                        }

                        is BreedListAction.OnFavoriteClick -> {
                            viewModel.onAction(action)
                        }
                    }
                },
                modifier = modifier
            )
        }

        composable(Screen.BreedDetail.route) {

            BreedDetailScreen(
                state = state,
                onAction = { action ->
                    when (action) {
                        is BreedDetailsAction.OnDetailsBack -> {
                            navController.popBackStack()
                        }

                        is BreedDetailsAction.OnFavoriteClick -> {
                            viewModel.onAction(action)
                        }

                        is BreedDetailsAction.OnWikipediaLink -> {
                            openLink(context, action.url)
                        }
                    }
                },
            )
        }
    }
}

private fun openLink(context: Context, url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    context.startActivity(intent)
}