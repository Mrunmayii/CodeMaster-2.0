package com.example.codemaster.utils


/**
 *  send uiEvents implementation to uiState
 */

sealed class UiEvent {

    data class ShowSnackbar(
        val message : String,
        val action : String? = null
    ) : UiEvent()
}