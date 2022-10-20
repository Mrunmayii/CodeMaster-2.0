package com.example.codemaster.ui.cf_problems_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codemaster.data.model.codeforces_offical.CodeforcesProblemset
import com.example.codemaster.data.model.codeforces_offical.Problem
import com.example.codemaster.data.model.codeforces_offical.ProblemsetResult
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
    val tags = ArrayList<String>()

    init{
        fetchProblems(tags,0)
        Log.d("kalp","$tags")
    }
    fun fetchProblems(tags: ArrayList<String>,rating : Int) {
        _uiState.value = CFProblemsUiState.Loading
        viewModelScope.launch {
            try {
                var tag = ""
                val res = ArrayList<Problem>()
                for(items in tags){
                    tag += "$items;"
                }
                val resp = repository.getProblemset(tag)
                if(resp.data != null){
                    if(rating!=0) {
                        for (item in resp.data.result.problems) {
                            if (item.rating == rating) {
                                res.add(item)
                            }
                        }
                    }
                    else res.addAll(resp.data.result.problems)
                    _uiState.value = CFProblemsUiState.Success(data = res, list = tags, rating = rating)
                    Log.d("kalp", rating.toString())
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
