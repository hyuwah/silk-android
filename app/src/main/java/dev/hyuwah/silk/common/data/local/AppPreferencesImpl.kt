package dev.hyuwah.silk.common.data.local

import android.content.SharedPreferences
import androidx.core.content.edit
import com.squareup.moshi.Moshi
import dev.hyuwah.silk.common.data.remote.response.GetUserReponse
import dev.hyuwah.silk.common.data.toUserData
import dev.hyuwah.silk.common.data.toUserDto
import dev.hyuwah.silk.common.domain.model.UserData
import javax.inject.Inject

class AppPreferencesImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val moshi: Moshi,
) : AppPreferences {

    private val userDtoAdapter by lazy {
        moshi.adapter(GetUserReponse.UserDto::class.java)
    }

    enum class Key {
        UserData,
        Token
    }

    override var userData: UserData?
        get() {
            val jsonString = sharedPreferences.getString(Key.UserData.name, "").orEmpty()
            return try {
                userDtoAdapter.fromJson(jsonString)?.toUserData()
            } catch (e: Exception) {
                null
            }
        }
        set(value) {
            try {
                sharedPreferences.edit {
                    putString(
                        Key.UserData.name,
                        if (value != null) {
                            userDtoAdapter.toJson(value.toUserDto())
                        } else {
                            ""
                        }
                    )
                }
            } catch (e: Exception) {

            }
        }
    override var token: String
        get() = sharedPreferences.getString(Key.Token.name, "").orEmpty()
        set(value) {
            sharedPreferences.edit { putString(Key.Token.name, value) }
        }
}