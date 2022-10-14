package com.example.codemaster.utils

import com.example.codemaster.R

sealed class Nav(
    val icon: Int,
    val route: String
) {
    object CONTESTS : Nav(
        R.drawable.icons_contest,"contests"
    )
    object CODECHEF : Nav(
        R.drawable.icons_codechef,"codechef"
    )
    object CODEFORCES : Nav(
        R.drawable.icons_codeforces,"codeforces"
    )
    object LEETCODE : Nav(
        R.drawable.icons_leetcode,"leetcode"
    )
}