package com.example.codemaster.ui.codeforces_screen

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.codemaster.R
import com.example.codemaster.components.ErrorDialog
import com.example.codemaster.data.model.Codeforces
import com.example.codemaster.data.model.codeforces_offical.UserInfo
import com.example.codemaster.ui.leetcode_screen.font
import com.example.codemaster.utils.UiEvent
import com.madrapps.plot.line.DataPoint
import com.madrapps.plot.line.LineGraph
import com.madrapps.plot.line.LinePlot

val font = FontFamily(Font(R.font.varelaround_regular))

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CodeforcesScreen (
    data : UserInfo,
    graphData: Codeforces,
    codeforcesViewModel: CodeforcesViewModel = hiltViewModel()
) {
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
                    elevation = 5.dp,
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Column(modifier = Modifier.padding(10.dp) ) {
                        Row() {
                            Column(modifier = Modifier.padding(10.dp)) {
                                val painter = rememberAsyncImagePainter(model = data.result[0].avatar)
                                Image(
                                    painter = painter,
                                    contentDescription = "Profile_picture",
                                    modifier = Modifier
                                        .size(80.dp)
                                )
                            }
                            Column(modifier = Modifier.padding(10.dp)) {
                                    Text(
                                        text = "@${data.result[0].handle}",
                                        fontWeight = FontWeight.ExtraBold,
                                        color = Color(0xFF2A265C),
                                        fontFamily = font,
                                        fontSize = 15.sp
                                    )
                                    Text(
                                        text = data.result[0].rank,
                                        fontFamily = font,
                                        color = Color(0xFF2A265C),
                                    )
                                    Text(
                                        text = "Max Rating: ${data.result[0].rating}",
                                        fontFamily = font,
                                        color = Color(0xFF2A265C),
                                    )
                                    Text(
                                        text = "Rating: ${data.result[0].maxRating}",
                                        fontFamily = font,
                                        color = Color(0xFF2A265C),
                                    )
                            }
                        }
                    }
                }
            }
            Row(modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Card(
                    modifier = Modifier
                        .height(80.dp)
                        .width(140.dp),
                    elevation = 5.dp,
                    onClick = {
                        codeforcesViewModel.onEvent(CodeforcesUiEvent.OnProblemsClick)
                    },
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        text = "PROBLEMS",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.wrapContentSize(),
                        fontFamily = font,
                        color = Color(0xFF2A265C),
                    )
                }
                Card(
                    modifier = Modifier
                        .height(80.dp)
                        .width(140.dp),
                    onClick = {
                        codeforcesViewModel.onEvent(CodeforcesUiEvent.OnRatingsClick)
                    },
                    shape = RoundedCornerShape(10.dp),
                    elevation = 5.dp
                ) {
                    Text(
                        text = "RATINGS",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.wrapContentSize(),
                        fontFamily = font,
                        color = Color(0xFF2A265C),
                    )
                }
            }
            Row(modifier = Modifier.padding(10.dp)) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    elevation = 5.dp
                ) {
                    CFGraph(graphData = graphData)
                }
            }
        }
    }
}

@Composable
fun CFGraph(graphData: Codeforces,){
    val mylist = mutableListOf<DataPoint>()
    var i = 0
    for(j in graphData.contest.reversed()) {
        mylist.add(DataPoint(i.toFloat(), j.toFloat()))
        i += 1
    }
    val lines: List<List<DataPoint>> = listOf(mylist)
    LineGraph(
        plot = LinePlot(
            listOf(
                LinePlot.Line(
                    lines[0],
                    LinePlot.Connection(Color(0xFF46468A), 1.dp),
                    LinePlot.Intersection(Color(0xFF46468A), 4.dp),
                    LinePlot.Highlight(Color.Black, 4.dp),
                    LinePlot.AreaUnderLine(Color(0xffDEDEFA), 0.3f)
                ),
                LinePlot.Line(
                    lines[0],
                    LinePlot.Connection(Color(0xFF46468A), 2.dp),
                    LinePlot.Intersection { center, _ ->
                        val px = 2.dp.toPx()
                        val topLeft = Offset(center.x - px, center.y - px)
                        drawRect(Color(0xFF46468A), topLeft, Size(px * 2, px * 2))
                    },
                ),
            ),
            selection =  LinePlot.Selection(
                highlight = LinePlot.Connection(
                    Color.Black,
                    strokeWidth = 3.dp,
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 15f))
                ),
                detectionTime = 50
            ),
            xAxis = LinePlot.XAxis(stepSize = mylist.size.dp, steps = 1),
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    )
}

@Composable
fun MainCFSceen(
    topBar : @Composable ()->Unit,
    onNavigate: (UiEvent.Navigate) -> Unit,
    codeforcesViewModel: CodeforcesViewModel = hiltViewModel()
) {
    val state = codeforcesViewModel.uiState.collectAsState().value
    Column{
        topBar()
        when (state) {
            is CodeforcesUiState.Loading ->
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator(
                        color = Color(0xFFB3BCF8)
                    )
                }
            is CodeforcesUiState.Failure -> ErrorDialog(state.message)
            is CodeforcesUiState.Success -> CodeforcesScreen(state.data, state.graphData)
        }
    }

    LaunchedEffect(key1 = true) {
        codeforcesViewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> onNavigate(event)
                else -> Unit
            }

        }
    }
}