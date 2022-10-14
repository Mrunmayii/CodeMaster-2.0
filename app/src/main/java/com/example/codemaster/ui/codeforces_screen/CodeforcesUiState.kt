package com.example.codemaster.ui.codeforces_screen

import com.example.codemaster.data.model.codeforces_offical.UserInfo

sealed class CodeforcesUiState {
    object Loading : CodeforcesUiState()
    class Success(val data: UserInfo) : CodeforcesUiState()
    class Failure(val message: String) : CodeforcesUiState()
}