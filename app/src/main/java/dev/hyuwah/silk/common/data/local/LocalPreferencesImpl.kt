package dev.hyuwah.silk.common.data.local

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject

class LocalPreferencesImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : LocalPreferences {

    enum class Key {
        UserId,
        Token
    }
    override var userId: String
        get() = sharedPreferences.getString(Key.UserId.name, "").orEmpty()
        set(value) {
            sharedPreferences.edit { putString(Key.UserId.name, value) }
        }
    override var token: String
        get() = sharedPreferences.getString(Key.Token.name, "").orEmpty()
        set(value) {
            sharedPreferences.edit { putString(Key.Token.name, value) }
        }
}