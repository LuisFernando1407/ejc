package com.ejc.data.remote

import com.ejc.util.isRelease

enum class AppEnvironment(val url: String) {
    API(
        if(isRelease()) "https://howtodoandroid.com/apis/"
        else "https://howtodoandroid.com/apis/"
    )
}