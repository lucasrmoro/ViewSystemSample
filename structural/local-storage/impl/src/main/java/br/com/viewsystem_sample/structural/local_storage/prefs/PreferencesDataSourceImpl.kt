package br.com.viewsystem_sample.structural.local_storage.prefs

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first

internal class PreferencesDataSourceImpl(
    private val dataStoreFactory: PreferencesDataStoreFactory
) : PreferencesDataSource {

    override suspend fun getString(key: String, defValue: String?): String? =
        dataStoreFactory.create().data.first()[stringPreferencesKey(key)] ?: defValue

    override suspend fun putString(key: String, value: String?) {
        val preferenceKey = stringPreferencesKey(key)
        dataStoreFactory.create().edit {
            if (value == null) it.remove(preferenceKey) else it[preferenceKey] = value
        }
    }

    override suspend fun getBoolean(key: String, defValue: Boolean): Boolean =
        dataStoreFactory.create().data.first()[booleanPreferencesKey(key)] ?: defValue

    override suspend fun putBoolean(key: String, value: Boolean) {
        dataStoreFactory.create().edit { it[booleanPreferencesKey(key)] = value }
    }

    override suspend fun getInt(key: String, defValue: Int): Int =
        dataStoreFactory.create().data.first()[intPreferencesKey(key)] ?: defValue

    override suspend fun putInt(key: String, value: Int) {
        dataStoreFactory.create().edit { it[intPreferencesKey(key)] = value }
    }

    override suspend fun getLong(key: String, defValue: Long): Long =
        dataStoreFactory.create().data.first()[longPreferencesKey(key)] ?: defValue

    override suspend fun putLong(key: String, value: Long) {
        dataStoreFactory.create().edit { it[longPreferencesKey(key)] = value }
    }

}
