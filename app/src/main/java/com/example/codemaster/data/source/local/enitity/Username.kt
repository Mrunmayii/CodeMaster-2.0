package com.example.codemaster.data.source.local.enitity

import androidx.room.Entity
import androidx.room.PrimaryKey


// Dao Model class
@Entity
data class Username(

    @PrimaryKey
    val id: Int = 1,
    val codechef : String?,
    val codeforces : String?,
    val leetcode : String?
)
