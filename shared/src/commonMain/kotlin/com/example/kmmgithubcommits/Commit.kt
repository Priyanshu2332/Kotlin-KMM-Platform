import kotlinx.serialization.Serializable

@Serializable
data class Commit(
    val sha: String,
    val node_id: String,
    val commit: CommitDetails,
    val url: String,
    val author: GitHubUser? = null
)

@Serializable
data class CommitDetails(
    val author: Author,
    val committer: Committer,
    val message: String,
    val verification: Verification
)

@Serializable
data class Author(
    val name: String,
    val email: String,
    val date: String,
    val login: String? = null
)

@Serializable
data class Committer(
    val name: String,
    val email: String,
    val date: String
)

@Serializable
data class Verification(
    val verified: Boolean,
    val reason: String
)
@Serializable
data class GitHubUser(
    val login: String,
    val id: Int,
    val avatar_url: String
)