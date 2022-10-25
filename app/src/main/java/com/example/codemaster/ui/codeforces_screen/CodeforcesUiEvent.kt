package com.example.codemaster.ui.codeforces_screen

sealed class CodeforcesUiEvent{
    object OnProblemsClick: CodeforcesUiEvent()
    object OnRatingsClick: CodeforcesUiEvent()
}
