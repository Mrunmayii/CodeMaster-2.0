package com.example.codemaster.ui.leetcode_screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.codemaster.components.ErrorDialog
import com.example.codemaster.data.model.Leetcode

@Composable
fun LeetcodeScreen(
    data: Leetcode
){
    Box(
        modifier = Modifier
    ){
        Column(modifier = Modifier.padding(20.dp)) {
            Row(modifier = Modifier.padding(10.dp)){
                Text(
                    text = "LEETCODE",
                    fontSize = 30.sp,
                    fontFamily = FontFamily.SansSerif
                )
            }
            Row(modifier = Modifier.padding(10.dp) ){
                Card(
                    modifier = Modifier
                        .fillMaxWidth(),
                    backgroundColor = Color(0xffDEDEFA)
                ) {
                    Column(modifier = Modifier.padding(10.dp) ) {
                        Row() {
                            Column() {
                                Box() {
                                    Text(text = "Ranking: ${data.ranking}")
                                }
                                Box() {
                                    Text(text = "Reputation: ${data.reputation}")
                                }
                            }
                        }
                        Row(){
                            Column() {
                                Box() {
                                    Text(text = "Acceptance Rate: ${data.acceptance_rate}")
                                }
                                Box() {
                                    Text(text = "Total Solved: ${data.total_problems_solved}")
                                }
                                Box() {
                                    Text(text = "Contribution: ${data.contribution_points}")
                                }
                            }
                        }
//
                    }
                }
            }
            Row(modifier = Modifier.padding(10.dp)) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    backgroundColor = Color(0xffDEDEFA)
                ) {
                    Text(text = "CHART")
                }
            }
        }
    }
}

@Composable
fun MainLCScreen(
    leetcodeViewModel: LeetcodeViewModel = hiltViewModel()
){
    val state = leetcodeViewModel.uiState.collectAsState().value
    when(state){
        is LeetcodeUiState.Loading ->
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    color = Color(194, 169, 252, 255)
                )
            }
        is LeetcodeUiState.Failure -> ErrorDialog(state.message)
        is LeetcodeUiState.Success -> LeetcodeScreen(state.data)
    }

}