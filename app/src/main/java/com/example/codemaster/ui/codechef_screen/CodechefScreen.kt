package com.example.codemaster.ui.codechef_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.codemaster.data.model.Codechef


@Composable
fun CodechefScreen(data:Codechef){
    Box(
       contentAlignment = Alignment.Center
    ){
        Text(text = "hghh ${data.max_rating}")
    }
}

@Composable
fun Setdetail(codechefViewModel: CodechefViewModel = hiltViewModel()) {
    val heightInPx = with(LocalDensity.current) { LocalConfiguration.current
        .screenHeightDp.dp.toPx()
    }
    Column(

    ) {
        when (val state = codechefViewModel.uiState.collectAsState().value) {
            is CodechefUiState.Empty -> Text(
                text = "No data available",
                modifier = Modifier.padding(16.dp)
            )
            is CodechefUiState.Loading ->
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator(
                        color = Color(194, 169, 252, 255)
                    )
                }
            is CodechefUiState.Failure -> ErrorDialog(state.message)
            is CodechefUiState.Success -> CodechefScreen(state.data)
        }
    }

}
@Composable
fun ErrorDialog(message: String) {
    val openDialog = remember { mutableStateOf(true) }
    if (openDialog.value) {
        Column(modifier = Modifier.fillMaxSize().background(Color.Cyan)){
            AlertDialog(
                onDismissRequest = {
                    openDialog.value = false
                },
                title = {
                    Text(text = "Dialog Title")
                },
                text = {
                    Text(text = message)
                },
                confirmButton = {
                    Button(
                        onClick = {
                            openDialog.value = false
                        }) {
                        Text("This is the Confirm Button")
                    }
                },
                dismissButton = {
                    Button(
                        onClick = {
                            openDialog.value = false
                        }) {
                        Text("This is the dismiss Button")
                    }
                }
            )
        }

    }
}