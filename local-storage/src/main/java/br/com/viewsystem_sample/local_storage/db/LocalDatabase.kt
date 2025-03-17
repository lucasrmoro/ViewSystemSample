package br.com.viewsystem_sample.local_storage.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.viewsystem_sample.entities.db.FooDbEntity
import br.com.viewsystem_sample.local_storage.db.dao.FooDao

@TypeConverters(br.com.viewsystem_sample.local_storage.db.TypeConverters::class)
@Database(entities = [FooDbEntity::class], version = 1, exportSchema = false)
internal abstract class LocalDatabase : RoomDatabase() {

    abstract fun fooDao(): FooDao

}