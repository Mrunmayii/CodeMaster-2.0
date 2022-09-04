package com.example.codemaster.data.model

data class Codeforces(
    val avatar: String,
    val contest: List<Int>,
    val max_rank: String,
    val max_rating: Int,
    val platform: String,
    val problem_solved: String,
    val rank: String,
    val rating: Int,
    val status: String,
    val username: String
)