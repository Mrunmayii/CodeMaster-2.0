package com.example.codemaster.ui.home

import android.view.KeyEvent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HomeScreen(
    viewModel: InputListViewModel = hiltViewModel()
) {

    Card(
        modifier = Modifier
            .padding(10.dp)
            .blur(20.dp, 20.dp)
            .fillMaxHeight(),
            backgroundColor = Color(160, 225, 255, 255)
        ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .imePadding()
        ) {
            val (focusRequester) = FocusRequester.createRefs()
            val focusManager = LocalFocusManager.current
            TextField(
                value = viewModel.CCusername,
                onValueChange = {
                    viewModel.onEvent(InputListEvent.OnCCUsernameChange(it))
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                    }
                ),
                placeholder = {
                    Text(text = "Codechef username")
                },
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
            Spacer(modifier = Modifier.height(6.dp))
            TextField(
                    value = viewModel.CFusername,
                    onValueChange = {
                        viewModel.onEvent(InputListEvent.OnCFUsernameChange(it))
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                        }
                    ),
                    placeholder = {
                        Text(text = "Codeforces username")
                    },
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
            Spacer(modifier = Modifier.height(6.dp))
            TextField(
                value = viewModel.LCusername,
                onValueChange = {
                    viewModel.onEvent(InputListEvent.OnLCUsernameChange(it))
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                    }
                ),
                placeholder = {
                    Text(text = "Leetcode username")
                },
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
            Spacer(modifier = Modifier.height(6.dp))

            Button(
                onClick = { viewModel.onEvent(InputListEvent.onSaveClick) }
            ){
                Text(text = "save")
            }

            Spacer(modifier = Modifier.height(6.dp))

            Button(
                onClick = { viewModel.onEvent(InputListEvent.onGetClick) }
            ){
                Text(text = "get")
            }

            Spacer(modifier = Modifier.height(6.dp))
            
            Text(text = viewModel.text1)
            Text(text = viewModel.text2)
            Text(text = viewModel.text3)
        }
    }
}


