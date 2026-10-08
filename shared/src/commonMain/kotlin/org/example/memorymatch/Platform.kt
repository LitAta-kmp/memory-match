package org.example.memorymatch

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform