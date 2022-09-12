package com.example.codemaster.ui.home

sealed interface InputListEvent {

    data class OnCCUsernameChange(val CCuserName: String): InputListEvent
    data class OnCFUsernameChange(val CFuserName: String): InputListEvent
    data class OnLCUsernameChange(val LCuserName: String): InputListEvent

    object OnClick : InputListEvent

}