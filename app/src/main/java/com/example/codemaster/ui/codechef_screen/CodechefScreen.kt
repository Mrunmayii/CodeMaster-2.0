package com.example.codemaster.ui.codechef_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.example.codemaster.components.ErrorDialog
import com.example.codemaster.data.model.Codechef

@Composable
fun CodechefScreen(data:Codechef){
//    val dataX = mutableListOf<Int>()
//    val dataY = mutableListOf<Int>()
//    var j = 1
//    for(i in data.contests){
//        dataX.add(j)
//        dataY.add(i)
//        j++
//    }
//    LineGraph(
//        xAxisData = dataX.map {
//            GraphData.Number(it)
//        },
//        yAxisData = dataY
//    )
    Box(
        modifier = Modifier
    ){
        Column(modifier = Modifier.padding(20.dp)) {
            Row(modifier = Modifier.padding(10.dp)){
                Text(
                    text = "CODECHEF",
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
                                val painter = rememberImagePainter(data = data.avatar)
                                Image(painter = painter, contentDescription = "Profile_picture")
                            }
                            Column(modifier = Modifier.padding(10.dp)) {
                                Box() {
                                    Text(text = "@${data.username}")
                                }
                                Box() {
                                    Text(text = data.div)
                                }
                                Box() {
                                    Text(
                                        text = "Stars ${data.stars}",
                                        color =
                                            if(data.rating<"1400") Color(0xFF666666)
                                            else if(data.rating<"1600" && data.rating>="1400")
                                                Color(0xFF1E7D22)
                                            else if(data.rating<"1800" && data.rating>="1600")
                                                Color(0xFF3366CB)
                                            else if(data.rating<"2000" && data.rating>="1800")
                                                Color(0xFF684273)
                                            else if(data.rating<"2200" && data.rating>="2000")
                                                Color(0xFFFEBE00)
                                            else if(data.rating<"2500" && data.rating>="2200")
                                                Color(0xFFFE7F00)
                                            else
                                                Color.Red
                                        )
                                }
                            }
                        }
                        Row(){
                            Column() {
                                Box() {
                                    Text(text = "Max Rating: ${data.max_rating}")
                                }
                                Box() {
                                    Text(text = "Rating: ${data.rating}")
                                }
                                Box() {
                                    Text(text = "Country Rank: ${data.country_rank}")
                                }
                                Box() {
                                    Text(text = "Global Rank: ${data.global_rank}")
                                }
                            }
                        }

                    }
                }
            }
            Row(modifier = Modifier.padding(10.dp)) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    backgroundColor = Color(0xffDEDEFA)
                ) {
                    Text("GRAPH")
                }
            }
        }
    }
}

@Composable
fun Setdetail(
    codechefViewModel: CodechefViewModel = hiltViewModel()
) {
    val heightInPx = with(LocalDensity.current) { LocalConfiguration.current
        .screenHeightDp.dp.toPx()
    }
    Column {
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