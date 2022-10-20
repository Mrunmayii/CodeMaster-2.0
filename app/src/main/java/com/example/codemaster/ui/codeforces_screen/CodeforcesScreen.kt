package com.example.codemaster.ui.codeforces_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.codemaster.components.ErrorDialog
import com.example.codemaster.data.model.Codeforces
import com.example.codemaster.data.model.codeforces_offical.UserInfo


@Composable
fun CodeforcesScreen (
    data : UserInfo
) {
    Box(
        modifier = Modifier
    ){
        Column(modifier = Modifier.padding(20.dp)) {
            Row(modifier = Modifier.padding(10.dp)){
                Text(
                    text = "CODEFORCES",
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
                            Column(modifier = Modifier.padding(10.dp)) {
                                val painter = rememberAsyncImagePainter(model = data.result[0].avatar)
                                Image(
                                    painter = painter,
                                    contentDescription = "Profile_picture",
                                    modifier = Modifier
                                        .height(75.dp)
                                        .width(60.dp)
                                )
                            }
                            Column(modifier = Modifier.padding(10.dp)) {
                                Box() {
                                    Text(text = "@${data.result[0].handle}")
                                }
                                Box() {
                                    Text(text = data.result[0].rank)
                                }
                            }
                        }
                        Row(){
                            Column() {
                                Box() {
                                    Text(text = "Max Rating: ${data.result[0].rating}")
                                }
                                Box() {
                                    Text(text = "Rating: ${data.result[0].maxRating}")
                                }
                                Box() {
                                    Text(text = "Country Rank: ${data.result[0].rank}")
                                }
                                Box() {
                                    Text(text = "Global Rank: ${data.result[0].maxRank}")
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
                    Text(text = "GRAPH")
                }
            }
        }
    }
}
@Composable
fun MainCFSceen(
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