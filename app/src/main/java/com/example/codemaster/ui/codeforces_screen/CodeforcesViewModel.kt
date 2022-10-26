package com.example.codemaster.ui.codeforces_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codemaster.data.source.local.enitity.CCUsername
import com.example.codemaster.data.source.local.enitity.CFUsername
import com.example.codemaster.data.source.repository.ContestRepository
import com.example.codemaster.utils.Nav2
import com.example.codemaster.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CodeforcesViewModel @Inject constructor(
    val repository : ContestRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<CodeforcesUiState>(CodeforcesUiState.Loading)
    val uiState: StateFlow<CodeforcesUiState> = _uiState

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init{
        viewModelScope.launch {
            val username = repository.getCFUsername(1)?.codeforces ?: "codeforces"
            fetchCodeforces(username)
        }

    }

    fun onEvent(event: CodeforcesUiEvent){
        when(event){
            is CodeforcesUiEvent.OnProblemsClick->{
                sendUiEvent(UiEvent.Navigate(Nav2.CFPROBLEMS.route))
            }
            is CodeforcesUiEvent.OnRatingsClick->{
                sendUiEvent(UiEvent.Navigate(Nav2.CFRATINGS.route))
            }
        }
    }

    fun fetchCodeforces(username : String) {
        viewModelScope.launch {
            try {
                val resp = repository.getUserInfo(username)
                val graphResp = repository.getCodeforces(username)
                if (resp.data != null) {
                    _uiState.value = CodeforcesUiState.Success(
                        data = resp.data,
                        graphData = graphResp.data!!
                    )
                    Log.d("cfDATA", "${resp.data}")
                } else {
                    _uiState.value = CodeforcesUiState.Failure(
                        message = "Something went wrong"
                    )
                }
            } catch (ex: Exception) {
                _uiState.value = CodeforcesUiState.Failure(
                    message = "Something went wrong"
                )
            }
        }
    }
    fun saveCFUser(CFusername: String){
        viewModelScope.launch {
            repository.storeCodeforcesUsername(
                CFUsername(
                    codeforces = CFusername
                )
            )
        }
    }
    fun validateCodeforcesUser(username : String): Boolean {
        var ans = true
        viewModelScope.launch {
            if(repository.getCodeforces(username).data == null) ans = false
        }
        return ans
    }
    private fun sendUiEvent(event: UiEvent){
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}