package com.example.codemaster.ui.contest_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.codemaster.components.ErrorDialog
import com.example.codemaster.components.Shimmer
import com.example.codemaster.data.model.Contest
import com.example.codemaster.data.model.ContestItem
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.rememberShimmer
import com.valentinilk.shimmer.shimmer
import com.valentinilk.shimmer.unclippedBoundsInWindow

@Composable
fun Contest(
    contestViewModel: ContestViewModel = hiltViewModel()
) {
    val state = contestViewModel.uiState.collectAsState().value
    Column {
        when (state) {
            is ContestUiState.Empty -> Column {
                repeat(7){
                    Shimmer()
                }
            }
            is ContestUiState.Loading -> Column {
                repeat(7){
                    Shimmer()
                }
            }
            is ContestUiState.Failure -> ErrorDialog(state.message)
            is ContestUiState.Success -> Contests(data = state.data)
        }
    }
}


@Composable
fun Contests(
    data : Contest
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(data){
            ContestCard(data = it)
        }
    }
}

@Composable
fun ContestCard(
    data : ContestItem
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Divider(
            modifier = Modifier.fillMaxSize(1f),
            color = Color(210, 231, 236, 255),
            thickness = 2.dp
        )
        Spacer(modifier = Modifier.height(4.dp))
        Column(modifier = Modifier.padding(25.dp)) {
            Text(text = data.site)
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = data.name
            )
        }
    }
}