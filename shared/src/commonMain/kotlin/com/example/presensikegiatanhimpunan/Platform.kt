package com.example.presensikegiatanhimpunan

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform