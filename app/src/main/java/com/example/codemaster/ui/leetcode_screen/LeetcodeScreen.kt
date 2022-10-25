package com.example.codemaster.ui.leetcode_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.codemaster.components.DonutChart
import com.example.codemaster.components.ErrorDialog
import com.example.codemaster.components.LinearProgressIndicatorSample
import com.example.codemaster.data.model.Leetcode

@Composable
fun LeetcodeScreen(
    data: Leetcode
){
    Box(
        modifier = Modifier
            .background(Color(0xFFEEF0FD))
            .fillMaxSize()
    ){
        Column(modifier = Modifier.padding(10.dp)) {
            Row(modifier = Modifier.padding(10.dp) ){
                Card(
                    modifier = Modifier
                        .fillMaxWidth(),
                    backgroundColor = Color.White,
                    elevation = 10.dp
                ) {
                    Column(modifier = Modifier.padding(10.dp) ) {
                        Column{
                            Box{
                                Row{
                                    Text(
                                        text = "Easy",
                                        color = Color(0xFF00B7A2),
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = buildAnnotatedString {
                                            append(data.easy_questions_solved)
                                            pushStyle(SpanStyle(Color.Gray, fontSize = 13.sp))
                                            append("/${data.total_easy_questions}")
                                            pop()
                                        },
                                        textAlign = TextAlign.End,
                                        modifier = Modifier.padding(start = 5.dp)
                                    )
                                }
                            }
                            Box{
                                LinearProgressIndicatorSample(
                                    que = data.easy_questions_solved.toFloat(),
                                    total_que = data.total_easy_questions.toFloat(),
                                    color = Color(0xFF00B7A2),
                                    trackColor = Color(0xFFDDEEE1)
                                )
                            }
                            Box {
                                Row{
                                    Text(
                                        text = "Medium",
                                        color = Color(0xFFFEBF1E),
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = buildAnnotatedString {
                                            append(data.medium_questions_solved)
                                            pushStyle(SpanStyle(Color.Gray, fontSize = 13.sp))
                                            append("/${data.total_medium_questions}")
                                            pop()
                                        },
                                        textAlign = TextAlign.End,
                                        modifier = Modifier.padding(start = 5.dp)
                                    )
                                }
                            }
                            Box{
                                LinearProgressIndicatorSample(
                                    que = data.medium_questions_solved.toFloat(),
                                    total_que = data.total_medium_questions.toFloat(),
                                    color = Color(0xFFFEBF1E),
                                    trackColor = Color(0xFFF1EDE1)
                                )
                            }
                            Box{
                                Row{
                                    Text(
                                        text = "Hard",color = Color(0xFFEE4743),
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = buildAnnotatedString {
                                            append(data.hard_questions_solved)
                                            pushStyle(SpanStyle(Color.Gray, fontSize = 13.sp))
                                            append("/${data.total_hard_questions}")
                                            pop()
                                        },
                                        textAlign = TextAlign.End,
                                        modifier = Modifier.padding(start = 5.dp)
                                    )
                                }
                            }
                            Box{
                                LinearProgressIndicatorSample(
                                    que = data.hard_questions_solved.toFloat(),
                                    total_que = data.total_hard_questions.toFloat(),
                                    color = Color(0xFFEE4743),
                                    trackColor = Color(0xFFF5E0E1)
                                )
                            }
                        }
                        Row{
                            Column {
                                    Text(text = "Acceptance Rate: ${data.acceptance_rate}")
                                    Text(text = "Total Solved: ${data.total_problems_solved}")
                                    Text(text = "Contribution: ${data.contribution_points}")
                            }
                        }
//
                    }
                }
            }
            Row(modifier = Modifier.padding(10.dp)) {
                DonutChart(
                    modifier = Modifier,
                    progress = listOf(
                        data.easy_questions_solved.toFloat(),
                        data.medium_questions_solved.toFloat(),
                        data.hard_questions_solved.toFloat()
                    ),
                    colors = listOf(
                        Color(0xFF00B7A2),
                        Color(0xFFFEBF1E),
                        Color(0xFFEE4743),
                    ),
                    isDonut = true,
                    percentColor = Color.Black,
                )
            }
        }
    }
}

@Composable
fun MainLCScreen(
    topBar : @Composable ()->Unit,
    leetcodeViewModel: LeetcodeViewModel = hiltViewModel()
){
    val state = leetcodeViewModel.uiState.collectAsState().value
    Column{
        topBar()
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

}