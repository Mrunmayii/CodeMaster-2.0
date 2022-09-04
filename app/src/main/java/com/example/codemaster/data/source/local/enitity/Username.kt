package com.example.codemaster.data.source.local.enitity

import androidx.room.Entity
import androidx.room.PrimaryKey


// Dao Model class
@Entity
data class Username(

    @PrimaryKey
    val Id:Int? = null,
    val username: String? = null,
    val contest : String
)
