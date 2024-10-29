package com.dapcoding.catsapp.breeds.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BreedEntity (
    @PrimaryKey val id: String,
    val weightMetric: String?,
    val name: String?,
    val temperament: String?,
    val origin: String?,
    val description: String?,
    val affectionLevel: Int? = null,
    val adaptability: Int? = null,
    val childFriendly: Int? = null,
    val dogFriendly: Int? = null,
    val energyLevel: Int? = null,
    val grooming: Int? = null,
    val healthIssues: Int? = null,
    val intelligence: Int? = null,
    val sheddingLevel: Int? = null,
    val socialNeeds: Int? = null,
    val strangerFriendly: Int? = null,
    val vocalisation: Int? = null,
    val referenceImageId: String?,
    val wikipediaURL: String? = null,
    var isFavorite: Boolean = false
)