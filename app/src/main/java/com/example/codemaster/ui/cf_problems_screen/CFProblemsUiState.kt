package com.example.codemaster.ui.cf_problems_screen

import com.example.codemaster.data.model.codeforces_offical.CodeforcesProblemset
import com.example.codemaster.data.model.codeforces_offical.Problem
import com.example.codemaster.data.model.codeforces_offical.ProblemsetResult

sealed class CFProblemsUiState{
    object Empty : CFProblemsUiState()
    object Loading : CFProblemsUiState()
    class Success(val data: List<Problem>, val list: ArrayList<String>, val rating : Int) : CFProblemsUiState()
    class Failure(val message: String) : CFProblemsUiState()
}

