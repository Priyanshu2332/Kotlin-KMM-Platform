package com.example.kmmgithubcommits.android

import com.example.kmmgithubcommits.CommitViewModel
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val viewModel = CommitViewModel()
    private lateinit var commitAdapter: CommitAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.commit_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        commitAdapter = CommitAdapter(emptyList())
        recyclerView.adapter = commitAdapter

        lifecycleScope.launch {
            viewModel.commits.collect { commits ->
                Log.d("MainActivity", "Received commits: ${commits.size}")
                commitAdapter = CommitAdapter(commits)
                recyclerView.adapter = commitAdapter
            }
        }

        viewModel.fetchCommits()
    }
}
