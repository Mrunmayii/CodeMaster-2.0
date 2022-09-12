package com.example.codemaster.ui.home

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codemaster.data.source.local.enitity.Username
import com.example.codemaster.data.source.repository.ContestRepository
import com.example.codemaster.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import okhttp3.internal.platform.Platform
import javax.inject.Inject


@HiltViewModel
class InputListViewModel @Inject constructor(
    private val repository: ContestRepository,
): ViewModel() {
    var CCusername by mutableStateOf("")
        private set
    var CFusername by mutableStateOf("")
        private set
    var LCusername by mutableStateOf("")
        private set

    var cc by mutableStateOf("cc")
        private set

    var cf by mutableStateOf("cf")
        private set

    var lc by mutableStateOf("lc")
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            cc = repository.getCodechefUsername(1)?.codechef ?: "codechef"
            cf = repository.getCodechefUsername(1)?.codeforces ?: "codeforces"
            lc = repository.getCodechefUsername(1)?.leetcode ?: "leetcode"
        }
    }

    fun onEvent(event : InputListEvent) {
        when (event) {
            is InputListEvent.OnCCUsernameChange -> {
                CCusername = event.CCuserName
            }
            is InputListEvent.OnCFUsernameChange -> {
                CFusername = event.CFuserName
            }
            is InputListEvent.OnLCUsernameChange -> {
                LCusername = event.LCuserName
            }
            is InputListEvent.OnClick -> {
                viewModelScope.launch {
                    repository.storeCodechefUsername(
                        Username(
                            codechef = CCusername,
                            codeforces = CFusername,
                            leetcode = LCusername
                        )
                    )
                }
            }
        }
    }

    /**
     *   send ui event to the UI State
     */
    private fun sendUiEvent(event : UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

}