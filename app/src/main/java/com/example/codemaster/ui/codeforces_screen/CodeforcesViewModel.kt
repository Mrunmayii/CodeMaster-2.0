package com.example.codemaster.ui.codeforces_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codemaster.data.source.repository.ContestRepository
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
                val username = repository.getUsername(1)?.codeforces ?: "codeforces"
                val resp = repository.getUserInfo(username)
                if (resp.data != null) {
                    _uiState.value = CodeforcesUiState.Success(
                        data = resp.data
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
}