package com.example.codemaster.ui.codeforces_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codemaster.data.source.repository.ContestRepository
import com.example.codemaster.ui.codechef_screen.CodechefUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CodeforcesViewModel @Inject constructor(
    val repository : ContestRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<CodeforcesUiState>(CodeforcesUiState.Loading)
    val uiState: StateFlow<CodeforcesUiState> = _uiState

    init{
        fetchCodeforces()
    }

    private fun fetchCodeforces() {
        viewModelScope.launch {
            try {
                val username = repository.getCodechefUsername(1)?.codeforces ?: "codeforces"
                val resp = repository.getCodeforces(username)
                if (resp.data != null) {
                    _uiState.value = CodeforcesUiState.Success(
                        data = resp.data
                    )
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
}