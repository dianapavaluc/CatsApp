package com.dapcoding.catsapp.breeds.presentation.breeddetails.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dapcoding.catsapp.breeds.presentation.breeddetails.BreedDetailsAction

@Composable
fun LinkSection(label: String, url: String, onAction: (BreedDetailsAction) -> Unit) {
    TextButton(onClick = {
        onAction(BreedDetailsAction.OnWikipediaLink(url))
    }) {
        Text(
            text = label,
            color = MaterialTheme.colorScheme.primary,
            fontSize = 14.sp,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}