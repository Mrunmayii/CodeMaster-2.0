package com.example.codemaster.components

import android.util.Log
import android.view.KeyEvent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.codemaster.ui.codechef_screen.CodechefUiState
import com.example.codemaster.ui.codechef_screen.CodechefViewModel
import com.example.codemaster.ui.codeforces_screen.CodeforcesViewModel
import com.example.codemaster.ui.home.InputListEvent
import com.example.codemaster.ui.home.InputListViewModel
import com.example.codemaster.ui.leetcode_screen.LeetcodeViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class,
    ExperimentalComposeUiApi::class, DelicateCoroutinesApi::class
)
@Composable
fun CustomTextField(
    viewModel: InputListViewModel = hiltViewModel(),
    value : String,  //viewModel.CCusername
    platform: String,
    codechefViewModel: CodechefViewModel = hiltViewModel(),
    codeforcesViewModel: CodeforcesViewModel = hiltViewModel(),
    leetcodeViewModel: LeetcodeViewModel = hiltViewModel(),
){
    val ccState = codechefViewModel.uiState.collectAsState().value
    var cfState = codeforcesViewModel.uiState.collectAsState().value
    val lcState = leetcodeViewModel.uiState.collectAsState().value

    val (focusRequester) = FocusRequester.createRefs()
    val focusManager = LocalFocusManager.current
    TextField(
        value = value,
        onValueChange = {
            if(platform == "codechef") {
                viewModel.onEvent(InputListEvent.OnCCUsernameChange(it))
            }
            else if(platform == "codeforces") {
                viewModel.onEvent(InputListEvent.OnCFUsernameChange(it))
            }
            else if(platform == "leetcode") {
                viewModel.onEvent(InputListEvent.OnLCUsernameChange(it))
            }
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(
            onDone = {
                if(platform == "codechef"){
//                    if(!codechefViewModel.validateCodechefUser(value)){
//                        setSnackBarState(!snackbarVisibleState)
//                    }
//                    else {
                    GlobalScope.launch { codechefViewModel.fetchCodechef(value)  }
                    codechefViewModel.saveCCUser(value)
//                        onDismiss()
//                        Log.d("koko",value)

//                    }
                }
                else if(platform == "codeforces"){
//                    if(!codeforcesViewModel.validateCodeforcesUser(value)){
//                        setSnackBarState(!snackbarVisibleState)
//                    }
//                    else {

                    codeforcesViewModel.fetchCodeforces(value)
                    codeforcesViewModel.saveCFUser(value)
//                        onDismiss()
//                        Log.d("db",value)
//                    }
                }
                else if(platform == "leetcode"){
//                    if(leetcodeViewModel.validateLeetcodeUser(value)){
//                        setSnackBarState(!snackbarVisibleState)
//                    }
//                    else {
                    leetcodeViewModel.fetchLeetcode(value)
                    leetcodeViewModel.saveLCUser(value)
//                        onDismiss()
//                        Log.d("koko",value)
//                    }
                }
                focusManager.clearFocus()
            }
        ),
        placeholder = {
            if(platform == "codechef"){
                Text(text = viewModel.cc)
            }
            else if(platform == "codeforces"){
                Text(text = viewModel.cf)
            }
            else if(platform == "leetcode"){
                Text(text = viewModel.lc)
            }
        },
        isError = false,
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            containerColor = Color.White,
        ),
        shape = ShapeDefaults.ExtraSmall,
        textStyle = TextStyle(lineHeight = 120.sp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .onKeyEvent {
                if (it.nativeKeyEvent.keyCode == KeyEvent.KEYCODE_ENTER) {
                    focusRequester.requestFocus()
                    true
                }
                false
            }
    )
}