package br.com.viewsystem_sample.local_storage.prefs

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

internal class PreferencesDataSourceImpl(context: Context) : PreferencesDataSource {

    private val sharedPrefs =
        context.getSharedPreferences(SAMPLE_SHARED_PREFS, Context.MODE_PRIVATE)

    private val encryptedSharedPrefsMasterKey =
        MasterKey.Builder(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

    private val encryptedSharedPrefs = EncryptedSharedPreferences.create(
        context,
        SAMPLE_ENCRYPTED_SHARED_PREFS,
        encryptedSharedPrefsMasterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    override fun getString(key: String, defValue: String?): String? =
        if (encryptedSharedPrefs.contains(key)) {
            encryptedSharedPrefs.getString(key, defValue)
        } else {
            sharedPrefs.getString(key, defValue)
        }

    override fun putString(key: String, value: String?, encrypt: Boolean) {
        if (encrypt) {
            encryptedSharedPrefs.edit().putString(key, value).apply()
        } else {
            sharedPrefs.edit().putString(key, value).apply()
        }
    }

    override fun getBoolean(key: String, defValue: Boolean) = sharedPrefs.getBoolean(key, defValue)

    override fun putBoolean(key: String, value: Boolean) =
        sharedPrefs.edit().putBoolean(key, value).apply()

    override fun getInt(key: String, defValue: Int) = sharedPrefs.getInt(key, defValue)

    override fun putInt(key: String, value: Int) = sharedPrefs.edit().putInt(key, value).apply()

    override fun getLong(key: String, defValue: Long) = sharedPrefs.getLong(key, defValue)

    override fun putLong(key: String, value: Long) = sharedPrefs.edit().putLong(key, value).apply()

    companion object {
        private const val SAMPLE_SHARED_PREFS = "sample_shared_prefs"
        private const val SAMPLE_ENCRYPTED_SHARED_PREFS = "sample_encrypted_shared_prefs"
    }
}