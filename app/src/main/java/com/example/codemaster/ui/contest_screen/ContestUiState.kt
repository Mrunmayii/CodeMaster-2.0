package com.example.codemaster.ui.contest_screen

import com.example.codemaster.data.model.Contest

sealed class ContestUiState {
    object Empty : ContestUiState()
    object Loading : ContestUiState()
    class Success(val data: Contest) : ContestUiState()
    class Failure(val message: String) : ContestUiState()
}