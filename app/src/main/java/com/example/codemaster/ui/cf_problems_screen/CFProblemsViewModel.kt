package com.example.codemaster.ui.cf_problems_screen

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
class CFProblemsViewModel @Inject constructor(
    val repository: ContestRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<CFProblemsUiState>(CFProblemsUiState.Empty)
    val uistate: StateFlow<CFProblemsUiState> = _uiState

    init{
        fetchProblems()
    }

    private fun fetchProblems() {
        _uiState.value = CFProblemsUiState.Loading
        viewModelScope.launch {
            try {
                val tag = "implementation"
                val resp = repository.getProblemset(tag)
                if(resp.data != null){
                    _uiState.value = CFProblemsUiState.Success(
                        data = resp.data
                    )
                    Log.d("cfProblems", "${resp.data}")
                }
                else{
                    _uiState.value = CFProblemsUiState.Failure(
                        message = "Something Went Wrong"
                    )
                }
            }
            catch (ex: Exception) {
                _uiState.value = CFProblemsUiState.Failure(
                    message = "Something went wrong"
                )
            }
        }
    }
}