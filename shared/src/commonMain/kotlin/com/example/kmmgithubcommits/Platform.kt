package com.example.kmmgithubcommits

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform