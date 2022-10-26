package com.example.codemaster.ui.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codemaster.data.source.local.enitity.CCUsername
import com.example.codemaster.data.source.local.enitity.Username
import com.example.codemaster.data.source.repository.ContestRepository
import com.example.codemaster.utils.Nav
import com.example.codemaster.utils.Nav2
import com.example.codemaster.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class InputListViewModel @Inject constructor(
    private val repository: ContestRepository,
): ViewModel() {
    var cc by mutableStateOf("")
        private set
    var cf by mutableStateOf("")
        private set
    var lc by mutableStateOf("")
        private set
    init {
        viewModelScope.launch {
            cc = repository.getCCUsername(1)?.codechef ?: "codechef"
            cf = repository.getCFUsername(1)?.codeforces ?: "codeforces"
            lc = repository.getLCUsername(1)?.leetcode ?: "leetcode"
        }
    }



    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState

    var CCusername by mutableStateOf(cc)
        private set
    var CFusername by mutableStateOf(cf)
        private set
    var LCusername by mutableStateOf(lc)
        private set



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
                    repository.storeUsername(
                        Username(
                            codechef = CCusername,
                            codeforces = CFusername,
                            leetcode = LCusername
                        )
                    )
                }
                sendUiEvent(UiEvent.Navigate(Nav.CONTESTS.route))
                Log.d("kkkk","${CCusername},${CFusername},${LCusername}")
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