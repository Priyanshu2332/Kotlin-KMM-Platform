package com.example.kmmgithubcommits

import Commit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow



class CommitViewModel : ViewModel() {
    private val api = GithubApi()

    private val _commits = MutableStateFlow<List<Commit>>(emptyList())
    val commits: StateFlow<List<Commit>> = _commits

    fun fetchCommits() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                println("Starting API fetch in ViewModel...")
                val commits = api.fetchCommits().take(25)
                println("Fetched commits: ${commits.size}")
                commits.forEach { commit ->
                    println("Commit: sha=${commit.sha}, message=${commit.commit.message}")
                }

                withContext(Dispatchers.Main) {
                    _commits.value = commits
                }
            } catch (e: Exception) {
                println("Error in ViewModel fetchCommits: ${e.message}")
            }
        }
    }
}