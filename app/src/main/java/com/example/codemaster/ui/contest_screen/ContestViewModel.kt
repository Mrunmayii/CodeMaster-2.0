package com.example.codemaster.ui.contest_screen

import android.util.Log
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
class ContestViewModel @Inject constructor(
    val repository: ContestRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<ContestUiState>(ContestUiState.Empty)
    val uiState: StateFlow<ContestUiState> = _uiState


    init {
        fetchContestDetails()
    }
    private fun fetchContestDetails(){
        _uiState.value = ContestUiState.Loading
        viewModelScope.launch {
            try {
                val resp = repository.getContestDetails()
                if(resp.data!=null){
                    _uiState.value = ContestUiState.Success(
                        data = resp.data
                    )
                    Log.d("kalp", "${resp.data}")
                }
                else {
                    _uiState.value = ContestUiState.Failure(
                        message = "Something went wrong"
                    )
                    Log.d("kalp", "null from response Something went wrong")
                }
            }
            catch(ex : Exception) {
                _uiState.value = ContestUiState.Failure(
                    message = "Something went wrong"
                )
                Log.d("kalp", "something went wrong ")
            }
        }
    }
}