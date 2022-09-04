package com.example.codemaster.ui.home

sealed interface InputListEvent {

    data class OnUsernameChange(val userName: String): InputListEvent
    object onSaveClick : InputListEvent

}