package com.example.codemaster.ui.codechef_screen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codemaster.data.model.Codechef
import com.example.codemaster.data.source.local.enitity.CCUsername
import com.example.codemaster.data.source.repository.ContestRepository
import com.example.codemaster.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CodechefViewModel @Inject constructor(
    private val repository: ContestRepository,
): ViewModel(){

    private val _uiState = MutableStateFlow<CodechefUiState>(CodechefUiState.Empty)
    val uiState: StateFlow<CodechefUiState> = _uiState
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        viewModelScope.launch {
            val username = repository.getCCUsername(1)?.codechef ?:"codechef"
            fetchCodechef(username)
        }
    }

    fun fetchCodechef(
        username: String
    ){
        _uiState.value = CodechefUiState.Loading
        viewModelScope.launch {
            try {
                Log.d("kalp", username)
                val resp = repository.getCodechef(username)
                Log.d("kalp", username)
                if(resp.data!=null){
                    _uiState.value = CodechefUiState.Success(
                        data = resp.data
                    )
                    Log.d("mrun", "${resp.data}")
                }
                else {
                    _uiState.value = CodechefUiState.Failure(
                        message = "Something went wrong"
                    )
                }
            }
            catch(ex : Exception) {
                _uiState.value = CodechefUiState.Failure(
                    message = "Something went wrong"
                )
                Log.d("kalp", "something went wrong ")
            }
        }
    }
    fun saveCCUser(CCusername: String){
        viewModelScope.launch {
            repository.storeCodechefUsername(
                CCUsername(
                    codechef = CCusername
                )
            )
        }
    }

    fun validateCodechefUser(username : String): Boolean {
        var ans = true
        viewModelScope.launch {
            if(repository.getCodechef(username).data == null) ans = false
        }
        return ans
    }

    private fun sendUiEvent(event : UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}