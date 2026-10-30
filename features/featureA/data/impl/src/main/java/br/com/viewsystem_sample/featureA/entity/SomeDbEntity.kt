package br.com.viewsystem_sample.featureA.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "some_table")
data class SomeDbEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val text: String
)