package com.example.codemaster.ui.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codemaster.data.source.datastore.User
import com.example.codemaster.data.source.repository.ContestRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class InputListViewModel @Inject constructor(
    private val repository: ContestRepository,
): ViewModel() {
        var CCusername by mutableStateOf("")
          private set
        var CFusername by mutableStateOf("")
            private set
        var LCusername by mutableStateOf("")
            private set

        var text1 by mutableStateOf("")
        var text2 by mutableStateOf("")
        var text3 by mutableStateOf("")

//      private val _uiEvent = Channel<Ui>


    fun onEvent(event : InputListEvent) {
        when(event){
            is InputListEvent.OnCCUsernameChange -> {
                CCusername = event.CCuserName
            }
            is InputListEvent.OnCFUsernameChange -> {
                CFusername = event.CFuserName
            }
            is InputListEvent.OnLCUsernameChange -> {
                LCusername = event.LCuserName
            }
            is InputListEvent.onGetClick -> {
                viewModelScope.launch {
                    repository.getUser().collect{
                        text1 = it.codechef_user
                        text2 = it.codeforces_user
                        text3 = it.leetcode_user
                    }
                }
            }
            is InputListEvent.onSaveClick -> {
                viewModelScope.launch {
                    repository.saveUser(
                        User(
                            codechef_user = CCusername,
                            codeforces_user = CFusername,
                            leetcode_user = LCusername
                        )
                    )

                }
//                viewModelScope.launch {
//                    if(repository.getCodechef(CCusername).isSuccess){
//                        val data = repository.getCodechef(CCusername).toString()
//
//                        if(data == "Success(null)"){
//                            Log.d("kalp", "Failed invalid username")
//                        }
//                        else {
//                            Log.d("kalp","success $CCusername $data")
//                        }
//                    }
//                    else {
//                        Log.d("kalp", "Failed $CCusername")
//                    }
//
//                }
            }
        }
    }

}