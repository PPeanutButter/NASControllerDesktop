package data

data class Aria2Result(
    val id: Int,
    val jsonrpc: String,
    val result: List<Aria2Task>
)
