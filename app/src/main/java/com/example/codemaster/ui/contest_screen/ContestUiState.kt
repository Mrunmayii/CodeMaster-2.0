package com.example.codemaster.ui.contest_screen

import com.example.codemaster.data.model.Codeforces
import com.example.codemaster.data.model.Contest
import com.example.codemaster.ui.codeforces_screen.CodeforcesUiState

sealed class ContestUiState {
    object Empty : ContestUiState()
    object Loading : ContestUiState()
    class Success(val data: Contest) : ContestUiState()
    class Failure(val message: String) : ContestUiState()
}