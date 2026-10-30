package br.com.viewsystem_sample.structural.local_storage.prefs

interface PreferencesDataSource {

    suspend fun getString(key: String, defValue: String? = null): String?
    suspend fun putString(key: String, value: String?)
    suspend fun getBoolean(key: String, defValue: Boolean): Boolean
    suspend fun putBoolean(key: String, value: Boolean)
    suspend fun getInt(key: String, defValue: Int): Int
    suspend fun putInt(key: String, value: Int)
    suspend fun getLong(key: String, defValue: Long): Long
    suspend fun putLong(key: String, value: Long)

    companion object {
        const val ACCESS_TOKEN = "ACCESS_TOKEN"
        const val REFRESH_TOKEN = "REFRESH_TOKEN"
    }

}