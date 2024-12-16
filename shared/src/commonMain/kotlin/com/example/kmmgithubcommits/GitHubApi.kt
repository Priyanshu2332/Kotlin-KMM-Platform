package com.example.kmmgithubcommits

import Commit
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.bodyAsText
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class GithubApi {
    private val client = HttpClient() {
        install(io.ktor.client.plugins.contentnegotiation.ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun fetchCommits(): List<Commit> {
        return try {
            println("Fetching commits from GitHub API...")
            val response: String = client.get("https://api.github.com/repos/flutter/flutter/commits").bodyAsText()
            println("Raw API Response: $response")

            val commits: List<Commit> = Json {
                ignoreUnknownKeys = true
            }.decodeFromString(response)

            println("Parsed commits size: ${commits.size}")
            commits
        } catch (e: Exception) {
            println("Error fetching commits: ${e.message}")
            emptyList()
        }
    }
}