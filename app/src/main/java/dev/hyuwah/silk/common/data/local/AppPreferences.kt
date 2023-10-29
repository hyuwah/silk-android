package dev.hyuwah.silk.common.data.local

import dev.hyuwah.silk.common.domain.model.UserData

interface AppPreferences {
    var userData: UserData?
    var token: String
}