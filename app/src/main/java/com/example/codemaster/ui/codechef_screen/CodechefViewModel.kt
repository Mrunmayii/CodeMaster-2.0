package com.example.codemaster.ui.codechef_screen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codemaster.data.model.Codechef
import com.example.codemaster.data.source.repository.ContestRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CodechefViewModel @Inject constructor(
    private val repository: ContestRepository,
): ViewModel(){

    private val _uiState = MutableStateFlow<CodechefUiState>(CodechefUiState.Empty)
    val uiState: StateFlow<CodechefUiState> = _uiState

    init {
        fetchCodechef()
    }

    private fun fetchCodechef(){
        _uiState.value = CodechefUiState.Loading
        viewModelScope.launch {
            try {
                val username = repository.getUsername(1)?.codechef ?:"codechef"
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
}