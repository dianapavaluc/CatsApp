package com.dapcoding.catsapp.breeds.domain.model

import com.dapcoding.catsapp.BuildConfig

data class Breed(
    val id: String,
    val weightMetric: String?,
    val name: String?,
    val temperament: List<String>?,
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
){
    fun getImageUrl(): String {
        return BuildConfig.IMAGES_URL +"$referenceImageId.jpg"
    }
}