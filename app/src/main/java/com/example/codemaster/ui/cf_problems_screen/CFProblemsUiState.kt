package com.example.codemaster.ui.cf_problems_screen

import com.example.codemaster.data.model.codeforces_offical.CodeforcesProblemset

sealed class CFProblemsUiState{
    object Empty : CFProblemsUiState()

    object Loading : CFProblemsUiState()

    class Success(val data: CodeforcesProblemset) : CFProblemsUiState()

    class Failure(val message: String) : CFProblemsUiState()
}

