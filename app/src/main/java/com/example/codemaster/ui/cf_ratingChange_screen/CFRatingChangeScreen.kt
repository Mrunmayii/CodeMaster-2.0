package com.example.codemaster.ui.cf_ratingChange_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
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
import com.example.codemaster.data.model.codeforces_offical.RatingChangeResult
import com.example.codemaster.data.model.codeforces_offical.UserRatingChange

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
            color = Color(0xFFE6E6F0),
            thickness = 2.dp
        )
        Column(
            modifier = Modifier
                .padding(10.dp)
        ){
            Row() {
                Column(
                    modifier = Modifier
                        .width(150.dp)
                ) {
                    Text(
                        text = data.contestName,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.width(125.dp))
                Column() {
                    val change = data.newRating - data.oldRating
                    Text(
                        text = if (change > 0) "+$change" else "$change",
                        color = if (change > 0) Color(0xFF3D963D) else Color.Red,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.End
                    )
                }
            }
//            Spacer(modifier = Modifier.width(15.dp))
            Row(
                modifier = Modifier
            ) {
                val change = data.newRating-data.oldRating
                Text(
                    text= "New Rating: ${data.newRating}"
                )
            }
//            Spacer(modifier = Modifier.width(25.dp))
            Row(
                modifier = Modifier
            ) {
                Text(text = "Rank: ${data.rank}")
            }
        }
    }
}
