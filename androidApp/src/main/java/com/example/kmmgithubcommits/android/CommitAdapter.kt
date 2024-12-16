package com.example.kmmgithubcommits.android

import Commit
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class CommitAdapter(private val commits: List<Commit>) :
    RecyclerView.Adapter<CommitAdapter.CommitViewHolder>() {

    class CommitViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val commitMessage: TextView = itemView.findViewById(R.id.commit_message)
        val commitAuthor: TextView = itemView.findViewById(R.id.author_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommitViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.commit_item, parent, false)
        return CommitViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommitViewHolder, position: Int) {
        val commit = commits[position]
        Log.d("CommitAdapter", "Binding commit: ${commit.commit.message}")

        // Display commit message
        holder.commitMessage.text = commit.commit.message

        // Display author name or "Unknown" if author is null
        holder.commitAuthor.text = commit.author?.login ?: "Unknown"

        // Set the background color based on the commit hash's last character
        if (commit.sha.lastOrNull()?.isDigit() == true) {
            holder.itemView.setBackgroundColor(Color.YELLOW)
        } else {
            holder.itemView.setBackgroundColor(Color.WHITE)
        }
    }

    override fun getItemCount(): Int {
        return commits.size
    }
}