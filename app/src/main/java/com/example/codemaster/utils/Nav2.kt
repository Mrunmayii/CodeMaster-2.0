package com.example.codemaster.utils

sealed class Nav2(
    val route: String
){
    object HOME: Nav2(
        "home"
    )
    object CFPROBLEMS: Nav2(
        "cf_problems"
    )
    object CFRATINGS: Nav2(
        "cf_ratings"
    )
}
