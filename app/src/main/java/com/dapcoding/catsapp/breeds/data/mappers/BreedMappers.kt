package com.dapcoding.catsapp.breeds.data.mappers

import com.dapcoding.catsapp.breeds.data.database.BreedEntity
import com.dapcoding.catsapp.breeds.data.networking.dto.BreedDto
import com.dapcoding.catsapp.breeds.domain.model.Breed

fun BreedDto.toBreed(): Breed {
    return Breed(
        id = id,
        weightMetric = weight?.metric,
        name = name,
        temperament = temperament?.split(",")?.map { it.trim() },
        origin = origin,
        description = description,
        affectionLevel = affectionLevel,
        adaptability = adaptability,
        childFriendly = childFriendly,
        dogFriendly = dogFriendly,
        energyLevel = energyLevel,
        grooming = grooming,
        healthIssues = healthIssues,
        intelligence = intelligence,
        sheddingLevel = sheddingLevel,
        socialNeeds = socialNeeds,
        strangerFriendly = strangerFriendly,
        vocalisation = vocalisation,
        referenceImageId = referenceImageId,
        wikipediaURL = wikipediaUrl
    )
}

fun Breed.toBreedEntity(): BreedEntity {
    return BreedEntity(
        id = id,
        weightMetric = weightMetric,
        name = name,
        temperament = temperament?.joinToString { ", " },
        origin = origin,
        description = description,
        affectionLevel = affectionLevel,
        adaptability = adaptability,
        childFriendly = childFriendly,
        dogFriendly = dogFriendly,
        energyLevel = energyLevel,
        grooming = grooming,
        healthIssues = healthIssues,
        intelligence = intelligence,
        sheddingLevel = sheddingLevel,
        socialNeeds = socialNeeds,
        strangerFriendly = strangerFriendly,
        vocalisation = vocalisation,
        referenceImageId = referenceImageId,
        wikipediaURL = wikipediaURL
    )
}

fun BreedEntity.toBreed(): Breed {
    return Breed(
        id = id,
        weightMetric = weightMetric,
        name = name,
        temperament = temperament?.split(",")?.map { it.trim() },
        origin = origin,
        description = description,
        referenceImageId = referenceImageId
    )
}