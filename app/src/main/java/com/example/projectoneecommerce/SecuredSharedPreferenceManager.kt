package com.example.projectoneecommerce

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

object SecuredSharedPreferenceManager {

    private const val PREF_FILE_NAME = "confidential_data"
    private lateinit var sharedPreferences: SharedPreferences
    const val IS_LOGGED_IN_SECURED = "isLoggedInSecured"
    const val USER_EMAIL_SECURED = "userEmail"
    const val PASSWORD_SECURED = "password"

    fun init(context: Context) {
        val masterAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

        sharedPreferences = EncryptedSharedPreferences.create(
            PREF_FILE_NAME,
            masterAlias,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }


    fun saveString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun saveBoolean(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    fun saveBooleanAndGetStatus(key: String, value: Boolean): Boolean {
        return sharedPreferences.edit().putBoolean(key, value).commit()
    }

    fun getString(key: String): String {
        return sharedPreferences.getString(key, "not found") ?: "not found"
    }

    fun getBoolean(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    fun clearAllPref() {
        sharedPreferences.edit().clear().apply()
    }
}