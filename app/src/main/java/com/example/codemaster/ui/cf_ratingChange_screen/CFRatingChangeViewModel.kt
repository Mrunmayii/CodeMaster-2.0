package com.example.codemaster.ui.cf_ratingChange_screen

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
class CFRatingChangeViewModel @Inject constructor(
    val repository: ContestRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<CFRatingChangeUiState>(CFRatingChangeUiState.Empty)
    val uiState: StateFlow<CFRatingChangeUiState> = _uiState

    init{
        fetchRatingChanges()
    }

    private fun fetchRatingChanges() {
        _uiState.value = CFRatingChangeUiState.Loading
        viewModelScope.launch {
            try{
                val username = repository.getUsername(1)?.codeforces ?: "codeforces"
                val resp = repository.getUserRatingChange(username)
                if(resp.data != null){
                    _uiState.value = CFRatingChangeUiState.Success(
                        data = resp.data
                    )
                    Log.d("cfRatings", "${resp.data}")
                }
                else{
                    _uiState.value = CFRatingChangeUiState.Failure(
                        message = "Soemthing Went Wrong"
                    )
                }
            }
            catch (ex : Exception){
                _uiState.value = CFRatingChangeUiState.Failure(
                    message = "Something went wrong"
                )
            }
        }
    }
}