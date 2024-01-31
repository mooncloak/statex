@file:Suppress("MemberVisibilityCanBePrivate")

package com.mooncloak.kodetools.statex.buildSrc

object LibraryConstants {

    const val group = "com.mooncloak.kodetools.statex"
    const val owner = "mooncloak.kodetools"
    const val repoName = "statex"
    const val versionName = "1.0.0-alpha01"
    val versionDescription = "Release $versionName ($versionCode)"
    const val license = "Apache-2.0"
    const val vcsUrl = "https://github.com/mooncloak/statex.git"

    object Android {

        const val compileSdkVersion = 34
        const val minSdkVersion = 21
        const val targetSdkVersion = 34
    }
}
