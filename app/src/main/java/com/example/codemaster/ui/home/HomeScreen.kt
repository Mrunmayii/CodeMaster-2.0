package com.example.codemaster.ui.home

import android.view.KeyEvent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi



@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HomeScreen(
    viewModel: InputListViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    Box (modifier = Modifier.background(Color.Cyan)) {
        Column {
            Card(
                modifier = Modifier
                    .padding(60.dp)
                    .fillMaxHeight()
            ) {
                Column(
                    modifier = Modifier
                        .padding(20.dp)
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
                                      Text(text = viewModel.cc)
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
                            Text(text = viewModel.cf)
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
                                Text(text = viewModel.lc)
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
                        onClick = {
                            viewModel.onEvent(InputListEvent.OnClick)
                        }
                    ){
                        Text(text = "Save")
                    }
                    Text(text = viewModel.cc)
                    Text(text = viewModel.cf)
                    Text(text = viewModel.lc)
                }
            }
        }
    }
}
