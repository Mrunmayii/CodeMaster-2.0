package com.example.codemaster.ui.cf_problems_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.codemaster.components.ErrorDialog
import com.example.codemaster.components.Shimmer
import com.example.codemaster.data.model.codeforces_offical.CodeforcesProblemset
import com.example.codemaster.data.model.codeforces_offical.Problem

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CFProblemScreen(
    cfProblemsViewModel : CFProblemsViewModel = hiltViewModel()
){
    val state = cfProblemsViewModel.uistate.collectAsState().value
    Column{
        when(state){
            is CFProblemsUiState.Empty -> Column {
                repeat(7){
                    Shimmer()
                }
            }
            is CFProblemsUiState.Loading -> Column {
                repeat(7) {
                    Shimmer()
                }
            }
            is CFProblemsUiState.Failure -> ErrorDialog(state.message)
            is CFProblemsUiState.Success -> Problems(data =state.data)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Problems(
    data: CodeforcesProblemset
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
    ) {
//        item { ExposedDropDownMenu() }
        items(data.result.problems){
            ProblemCard(data = it)
        }
    }
}

@Composable
fun ProblemCard(
    data : Problem
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        androidx.compose.material.Divider(
            modifier = Modifier.fillMaxSize(1f),
            color = Color(0xFFE6E6F0),
            thickness = 2.dp
        )
        Column(
            modifier = Modifier
                .padding(10.dp)
        ){
            Row() {
                Text(
                    text = "${data.contestId}${data.index}",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(end = 10.dp)

                )
//                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = data.name,
                    modifier = Modifier
                        .padding(end = 10.dp)
                )
//                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text= "${data.rating}",
                    textAlign = TextAlign.End,
                )
            }
        }
    }
}