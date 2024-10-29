package com.dapcoding.catsapp.breeds.data.networking.dto

import com.squareup.moshi.Json
import kotlinx.serialization.Serializable

//We can reduce the items that we retrieve to only what we need, but I wanted to be able to play with data
@Serializable
data class BreedDto(
    val weight: Weight?,
    val id: String,
    val name: String?,
    val temperament: String?,
    val origin: String?,
    val description: String?,
    @field:Json(name = "life_span") val lifeSpan: String?,
    val adaptability: Int?,
    @field:Json(name = "affection_level") val affectionLevel: Int?,
    @field:Json(name = "child_friendly") val childFriendly: Int?,
    @field:Json(name = "dog_friendly") val dogFriendly: Int?,
    @field:Json(name = "energy_level") val energyLevel: Int?,
    val grooming: Int?,
    @field:Json(name = "health_issues") val healthIssues: Int?,
    val intelligence: Int?,
    @field:Json(name = "shedding_level") val sheddingLevel: Int?,
    @field:Json(name = "social_needs") val socialNeeds: Int?,
    @field:Json(name = "stranger_friendly") val strangerFriendly: Int?,
    val vocalisation: Int?,
    @field:Json(name = "wikipedia_url") val wikipediaUrl: String? = null,
    @field:Json(name = "reference_image_id") val referenceImageId: String? = null,
    val image: Image? = null
) {
    @Serializable
    data class Weight(
        val imperial: String?,
        val metric: String?
    )

    @Serializable
    data class Image(
        val id: String?,
        val width: Int?,
        val height: Int?,
        val url: String?
    )
}