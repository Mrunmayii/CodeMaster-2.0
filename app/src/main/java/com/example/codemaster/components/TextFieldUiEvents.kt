package com.example.codemaster.components

sealed class TextFieldUiEvents{
    object OnCCClick : TextFieldUiEvents()
    object OnCFClick : TextFieldUiEvents()
    object OnLCClick : TextFieldUiEvents()
}
