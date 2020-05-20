package com.android.labo.enums

object Constants {
    // Configuration socket.io
    const val DOMAIN = "http://192.168.1.37"
    const val PORT = "4000"
    const val URL = "$DOMAIN:$PORT"

    // Events socket.io
    // E2
    const val EVENT_LOGOUT_OTHER_SESSIONS = "logout_other_sessions"

    // E3
    const val EVENT_FORCE_LOGOUT = "force_logout"
}