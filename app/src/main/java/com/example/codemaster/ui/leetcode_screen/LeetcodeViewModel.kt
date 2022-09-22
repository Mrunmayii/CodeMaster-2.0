package com.example.codemaster.ui.leetcode_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codemaster.data.source.repository.ContestRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LeetcodeViewModel @Inject constructor (
    private val repository: ContestRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow<LeetcodeUiState>(LeetcodeUiState.Loading)
    val uiState: StateFlow<LeetcodeUiState> = _uiState

    init{
        fetchLeetcode()
    }

    private fun fetchLeetcode() {
        _uiState.value = LeetcodeUiState.Loading
        viewModelScope.launch {
            try{
                val username = repository.getUsername(1)?.leetcode?:"leetcode"
                val resp = repository.getLeetCode(username)
                if(resp.data != null){
                    _uiState.value = LeetcodeUiState.Success(
                        data = resp.data
                    )
                }
                else{
                    _uiState.value = LeetcodeUiState.Failure(
                        message = "Something went wrong"
                    )
                }
            }
            catch (ex: Exception){
                _uiState.value = LeetcodeUiState.Failure(
                    message = "Something went wrong"
                )
            }
        }
    }
}