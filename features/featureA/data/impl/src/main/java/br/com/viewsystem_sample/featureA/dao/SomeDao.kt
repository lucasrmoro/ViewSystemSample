package br.com.viewsystem_sample.featureA.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.viewsystem_sample.structural.local_storage.db.entity.SomeDbEntity

@Dao
interface SomeDao {

    @Insert
    suspend fun insert(someDbEntity: SomeDbEntity): Long

    @Update
    suspend fun update(someDbEntity: SomeDbEntity)

    @Delete
    suspend fun delete(someDbEntity: SomeDbEntity)

    @Query("DELETE FROM some_table WHERE id=:id")
    suspend fun deleteBy(id: Long)

    @Query("SELECT * FROM some_table LIMIT :limit OFFSET :offset")
    suspend fun getAll(limit: Int, offset: Int): List<SomeDbEntity>

    @Query("SELECT * FROM some_table WHERE id=:id LIMIT 1")
    suspend fun getBy(id: Long): SomeDbEntity?

    @Query("SELECT COUNT(id) FROM foo_table")
    suspend fun getRowsCount(): Int

}