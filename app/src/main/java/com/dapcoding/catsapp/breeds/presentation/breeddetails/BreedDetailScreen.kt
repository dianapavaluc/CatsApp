package com.dapcoding.catsapp.breeds.presentation.breeddetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.dapcoding.catsapp.R
import com.dapcoding.catsapp.breeds.domain.model.Breed
import com.dapcoding.catsapp.breeds.presentation.breeddetails.components.LinkSection
import com.dapcoding.catsapp.breeds.presentation.breeddetails.components.RatingItem
import com.dapcoding.catsapp.breeds.presentation.breedslist.BreedState
import com.dapcoding.catsapp.breeds.presentation.breedslist.components.previewBreed
import com.dapcoding.catsapp.ui.theme.CatsAppTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun BreedDetailScreen(
    state: BreedState,
    onAction: (BreedDetailsAction) -> Unit,
) {
    var isFavorite by remember { mutableStateOf(state.selectedBreed?.isFavorite == true) }
    val scrollState = rememberScrollState()

    state.selectedBreed?.let { breed ->
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(breed.name ?: stringResource(id = R.string.unknown)) },
                    navigationIcon = {
                        IconButton(onClick = {
                            onAction(BreedDetailsAction.OnDetailsBack)
                        }) {
                            Icon(
                                Icons.AutoMirrored.Filled.ArrowBack,
                                tint = MaterialTheme.colorScheme.onPrimary,
                                contentDescription = "Back Navigation"
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = {
                            isFavorite = !isFavorite
                            onAction(BreedDetailsAction.OnFavoriteClick(breed))
                        }) {
                            Icon(
                                imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                                contentDescription = if (isFavorite) stringResource(R.string.remove_from_favorites) else stringResource(R.string.add_to_favorites),
                                tint = if (isFavorite) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary
                    )
                )
            },
        ) { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .background(MaterialTheme.colorScheme.background)
            ) {
                breed.referenceImageId?.let {
                    AsyncImage(
                        model = breed.getImageUrl(),
                        contentDescription = "${breed.name} image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp),
                        contentScale = ContentScale.Crop
                    )
                }

                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    breed.origin?.let {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Language,
                                contentDescription = "Origin",
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = it,
                                fontSize = 16.sp,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                            )
                        }
                    }

                    breed.description?.let {
                        Text(
                            text = it,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Light,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }

                    breed.temperament?.let { list ->
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp),
                            modifier = Modifier.padding(bottom = 8.dp, top = 8.dp)
                        ) {
                            list.forEach { characteristic ->
                                com.dapcoding.catsapp.breeds.presentation.breedslist.components.Chip(
                                    characteristic
                                )
                            }
                        }
                    }

                    RatingSection(breed = breed)

                    breed.wikipediaURL?.let { url ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            LinkSection(label = stringResource(R.string.read_more_on_wikipedia), url = url, onAction)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RatingSection(breed: Breed) {
    val ratingCategories = listOf(
        stringResource(R.string.adaptability) to breed.adaptability,
        stringResource(R.string.affection_level) to breed.affectionLevel,
        stringResource(R.string.child_friendly) to breed.childFriendly,
        stringResource(R.string.dog_friendly) to breed.dogFriendly,
        stringResource(R.string.energy_level) to breed.energyLevel,
        stringResource(R.string.grooming) to breed.grooming,
        stringResource(R.string.health_issues) to breed.healthIssues,
        stringResource(R.string.intelligence) to breed.intelligence,
        stringResource(R.string.shedding_level) to breed.sheddingLevel,
        stringResource(R.string.social_needs) to breed.socialNeeds,
        stringResource(R.string.stranger_friendly) to breed.strangerFriendly,
        stringResource(R.string.vocalisation) to breed.vocalisation
    )

    Column(modifier = Modifier.fillMaxWidth()) {
        ratingCategories.forEach { (label, rating) ->
            RatingItem(label, rating)
        }
    }
}

@PreviewLightDark
@Composable
fun BreedDetailScreenPreview() {
    CatsAppTheme {
        BreedDetailScreen(
            state = BreedState(
                selectedBreed = previewBreed
            ),
            onAction = {}
        )
    }
}
