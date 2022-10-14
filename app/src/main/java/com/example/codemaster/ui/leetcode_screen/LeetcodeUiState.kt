package com.example.codemaster.ui.leetcode_screen

import com.example.codemaster.data.model.Leetcode

sealed class LeetcodeUiState{

    object Loading : LeetcodeUiState()
    class Success(val data: Leetcode) : LeetcodeUiState()
    class Failure(val message: String) : LeetcodeUiState()

}
