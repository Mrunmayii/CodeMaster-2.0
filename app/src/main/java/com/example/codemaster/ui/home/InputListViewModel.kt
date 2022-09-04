package com.example.codemaster.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codemaster.data.source.local.enitity.Username
import com.example.codemaster.data.source.repository.ContestRepository
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class InputListViewModel @Inject constructor(
    private val repository: ContestRepository
): ViewModel() {
        var username by mutableStateOf("")
          private set

//      private val _uiEvent = Channel<Ui>


    fun onEvent(event : InputListEvent) {
        when(event){
            is InputListEvent.OnUsernameChange -> {
                username = event.userName
            }
            is InputListEvent.onSaveClick -> {
                viewModelScope.launch {
                    repository.storeCodechefUsername(
                        Username(
                            username = username,
                            contest = "codechef"
                        )
                    )

                }
            }
        }
    }


}