package com.example.codemaster.components

import android.util.Log
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import com.example.codemaster.ui.codechef_screen.CodechefUiState
import com.example.codemaster.ui.codechef_screen.CodechefViewModel
import com.example.codemaster.ui.codeforces_screen.CodeforcesViewModel
import com.example.codemaster.ui.home.InputListViewModel
import com.example.codemaster.ui.leetcode_screen.LeetcodeViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@Composable
fun AlertBox(
    platform: String,
    openDialog: Boolean,
    onDismiss: () -> Unit,
    value: String,
    keyMap: MutableMap<String,String>,
    viewModel: InputListViewModel = hiltViewModel(),
    codechefViewModel: CodechefViewModel = hiltViewModel(),
    codeforcesViewModel: CodeforcesViewModel = hiltViewModel(),
    leetcodeViewModel: LeetcodeViewModel = hiltViewModel()
) {
    val ccState = codechefViewModel.uiState.collectAsState().value
    val cfState = codeforcesViewModel.uiState.collectAsState().value
    val lcState = leetcodeViewModel.uiState.collectAsState().value
    val (snackbarVisibleState, setSnackBarState) = remember { mutableStateOf(false) }
    SnackbarDemo(snackbarVisibleState = snackbarVisibleState)


    if (openDialog) {
        AlertDialog(
            title = { Text(text = platform) },
            onDismissRequest = onDismiss ,
            text = {
                CustomTextField(
                    value = value,
                    platform = platform
                )
            },
            confirmButton = {
                Button(
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black, contentColor = Color.White),
                    onClick = {
                        if(platform == "codechef"){
                            if(!codechefViewModel.validateCodechefUser(value)){
                                setSnackBarState(!snackbarVisibleState)
                            }
                            else {
                                codechefViewModel.saveCCUser(value)
                                onDismiss()
                                Log.d("koko",value)
                            }
                        }
                        else if(platform == "codeforces"){
                            if(!codeforcesViewModel.validateCodeforcesUser(value)){
                                setSnackBarState(!snackbarVisibleState)
                            }
                            else {
                                codeforcesViewModel.saveCFUser(value)
                                onDismiss()
                                Log.d("db",value)
                            }
                        }
                        if(platform == "leetcode"){
                            if(leetcodeViewModel.validateLeetcodeUser(value)){
                                setSnackBarState(!snackbarVisibleState)
                            }
                            else {
                                leetcodeViewModel.saveLCUser(value)
                                onDismiss()
                                Log.d("koko",value)
                            }
                        }
                        Log.d("kkkk","$keyMap")
                    }
                ) {
                    Text("Done")
                }
            },
            backgroundColor = Color.Black
        )

    }
}