package com.example.codemaster.data.source.local.enitity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CFUsername(
    @PrimaryKey
    val id: Int = 1,
    val codeforces : String?,
)