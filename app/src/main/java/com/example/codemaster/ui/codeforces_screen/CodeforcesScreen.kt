package com.example.codemaster.ui.codeforces_screen

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.codemaster.components.ErrorDialog
import com.example.codemaster.data.model.Codeforces
import com.jaikeerthick.composable_graphs.composables.LineGraph
import com.jaikeerthick.composable_graphs.data.GraphData

@Composable
fun CodeforcesScreen (
    data : Codeforces
) {

}
@Composable
fun MainSceen(
    modifier: Modifier,
    codeforcesViewModel: CodeforcesViewModel = hiltViewModel()
) {
    val state = codeforcesViewModel.uiState.collectAsState().value
    when (state) {
        is CodeforcesUiState.Loading ->
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    color = Color(194, 169, 252, 255)
                )
            }
        is CodeforcesUiState.Failure -> ErrorDialog(state.message)
        is CodeforcesUiState.Success -> CodeforcesScreen(state.data)
    }
}