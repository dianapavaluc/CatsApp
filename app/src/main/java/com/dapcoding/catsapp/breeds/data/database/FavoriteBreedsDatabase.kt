package com.dapcoding.catsapp.breeds.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [BreedEntity::class],
    version = 1,
    exportSchema = false
)
abstract class FavoriteBreedsDatabase : RoomDatabase() {
    abstract val dao: LocalFavoriteBreedsDao
}