package com.example.codemaster.ui.cf_ratingChange_screen

import com.example.codemaster.data.model.codeforces_offical.UserRatingChange

sealed class CFRatingChangeUiState{

    object Empty : CFRatingChangeUiState()

    object Loading : CFRatingChangeUiState()

    class Success(val data: UserRatingChange) : CFRatingChangeUiState()

    class Failure(val message: String) : CFRatingChangeUiState()
}

