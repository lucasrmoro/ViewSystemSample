package br.com.viewsystem_sample.featureA.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "some_local_table")
data class SomeLocalDTO(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val someField: Any
)