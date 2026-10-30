package br.com.viewsystem_sample.structural.local_storage.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "some_table")
data class SomeDbEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val text: String
)