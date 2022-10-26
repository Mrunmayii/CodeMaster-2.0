package com.example.codemaster.data.source.local.enitity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class CCUsername(
    @PrimaryKey
    val id: Int = 1,
    val codechef : String?,
)