package com.example.codemaster.data.source.local.enitity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LCUsername(
    @PrimaryKey
    val id: Int = 1,
    val leetcode : String?,
)