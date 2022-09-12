package com.example.codemaster.ui.codechef_screen

import com.example.codemaster.data.model.Codechef


/**
 * In the ViewModel layer we will introduce UI State which will be a sealed class of all possible
 * View states to alert View of State changes and to support the concept of ViewModel being the
 * source of truth for the State of our UI.
 */
sealed class CodechefUiState {
    object Empty : CodechefUiState()
    object Loading : CodechefUiState()
    class Success(val data: Codechef) : CodechefUiState()
    class Failure(val message: String) : CodechefUiState()
}