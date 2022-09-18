package com.example.codemaster.ui.codeforces_screen

import com.example.codemaster.data.model.Codeforces
import com.example.codemaster.ui.codechef_screen.CodechefUiState

sealed class CodeforcesUiState {
    object Loading : CodeforcesUiState()
    class Success(val data: Codeforces) : CodeforcesUiState()
    class Failure(val message: String) : CodeforcesUiState()
}