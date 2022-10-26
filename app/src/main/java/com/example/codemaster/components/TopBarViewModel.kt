package com.example.codemaster.components

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codemaster.utils.Nav
import com.example.codemaster.utils.Nav2
import com.example.codemaster.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopBarViewModel @Inject constructor(): ViewModel() {

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: TopBarUiEvent){
        when(event){
            is TopBarUiEvent.onSettingsClick->{
                sendUiEvent(UiEvent.Navigate(Nav2.HOME.route))
            }
            else -> Unit
        }
    }

    private fun sendUiEvent(event1: UiEvent){
        viewModelScope.launch {
            _uiEvent.send(event1)
        }
    }
}
