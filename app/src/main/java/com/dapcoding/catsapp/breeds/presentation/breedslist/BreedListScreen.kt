package com.dapcoding.catsapp.breeds.presentation.breedslist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.dapcoding.catsapp.breeds.domain.model.Breed
import com.dapcoding.catsapp.breeds.presentation.breedslist.components.BouncyLoadingIcon
import com.dapcoding.catsapp.breeds.presentation.breedslist.components.BreedListItem
import com.dapcoding.catsapp.breeds.presentation.breedslist.components.previewBreed
import com.dapcoding.catsapp.ui.theme.CatsAppTheme

@Composable
fun BreedListScreen(
    state: BreedState,
    onAction: (BreedListAction) -> Unit,
    modifier: Modifier = Modifier
) {
    if (state.isListLoading) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            BouncyLoadingIcon()
        }
    } else {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(0.dp),
        ) {
            items(state.breeds, key = { item: Breed -> item.id }) { breed ->
                BreedListItem(
                    breed = breed,
                    onAction = onAction,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun BreedListScreenPreview() {
    CatsAppTheme {
        BreedListScreen(
            state = BreedState(
                breeds = (1..100).map {
                    previewBreed.copy(id = it.toString())
                }
            ),
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background),
            onAction = {}
        )
    }
}