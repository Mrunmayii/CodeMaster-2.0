package com.example.codemaster.ui.cf_ratingChange_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.codemaster.components.ErrorDialog

import com.example.codemaster.components.Shimmer
import com.example.codemaster.data.model.codeforces_offical.RatingChangeResult
import com.example.codemaster.data.model.codeforces_offical.UserRatingChange
import com.example.codemaster.ui.leetcode_screen.font

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CFRatingChangeScreen(
    ratingViewModel: CFRatingChangeViewModel = hiltViewModel()
){
    val state = ratingViewModel.uiState.collectAsState().value
    Column{
        when(state) {
            is CFRatingChangeUiState.Empty -> Column {
                repeat(7){
                    Shimmer()
                }
            }
            is CFRatingChangeUiState.Loading -> Column {
                repeat(7){
                    Shimmer()
                }
            }
            is CFRatingChangeUiState.Failure -> ErrorDialog(state.message)
            is CFRatingChangeUiState.Success -> Ratings(data = state.data)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Ratings(
    data: UserRatingChange
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(data.result.reversed()){
            RatingCard(data = it)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RatingCard(
    data: RatingChangeResult
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Divider(
            modifier = Modifier.fillMaxSize(1f),
            color = Color(0xFFFFFFFF),
            thickness = 5.dp
        )
        Row(
            modifier = Modifier.fillMaxSize().background(Color(0xFFEEF0FD)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Column(
                modifier = Modifier
                .padding(15.dp)
                .width(250.dp)
            ) {
                Text(
                    text = data.contestName,
                    fontWeight = FontWeight.Bold,
                    fontFamily = font
                )
                Text(
                    text= "New Rating: ${data.newRating}",
                    fontFamily = font
                )
                Text(
                    text = "Rank: ${data.rank}",
                    fontFamily = font
                )
            }
            Column(
                modifier = Modifier.width(100.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Row {
                    val change = data.newRating - data.oldRating
                    Text(
                        text = if (change > 0) "+$change" else "$change",
                        color = if (change > 0) Color(0xFF3D963D) else Color.Red,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.End,
                        fontFamily = font
                    )
                }
            }
        }
    }
}
