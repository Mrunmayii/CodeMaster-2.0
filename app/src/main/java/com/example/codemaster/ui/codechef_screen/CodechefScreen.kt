package com.example.codemaster.ui.codechef_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.example.codemaster.components.ErrorDialog
import com.example.codemaster.data.model.Codechef
import com.madrapps.plot.line.DataPoint
import com.madrapps.plot.line.LineGraph
import com.madrapps.plot.line.LinePlot

@Composable
fun CodechefScreen(
    data:Codechef
){
    Box(
        modifier = Modifier
            .background(Color(0xFFEEF0FD))
            .fillMaxSize()
    ){
        Column(modifier = Modifier.padding(10.dp)) {
            Row(modifier = Modifier.padding(10.dp)) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth(),
                    elevation = 5.dp
                ) {
                    Column(modifier = Modifier.padding(10.dp)) {
                        Row() {
                            Column(modifier = Modifier.padding(10.dp)) {
                                val painter = rememberImagePainter(data = data.avatar)
                                Image(
                                    painter = painter,
                                    contentDescription = "Profile_picture",
                                    modifier = Modifier
                                        .size(80.dp)
                                )
                            }
                            Column(modifier = Modifier.padding(10.dp)) {
                                Text(
                                    text = "@${data.username}",
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF2A265C)
                                )
                                Row {
                                    Text(
                                        text = data.div,
                                        modifier = Modifier.padding(end = 15.dp)
                                    )
                                    Text(
                                        text = data.stars,
                                        color =
                                        if (data.rating < "1400") Color(0xFF666666)
                                        else if (data.rating < "1600" && data.rating >= "1400")
                                            Color(0xFF1E7D22)
                                        else if (data.rating < "1800" && data.rating >= "1600")
                                            Color(0xFF3366CB)
                                        else if (data.rating < "2000" && data.rating >= "1800")
                                            Color(0xFF684273)
                                        else if (data.rating < "2200" && data.rating >= "2000")
                                            Color(0xFFFEBE00)
                                        else if (data.rating < "2500" && data.rating >= "2200")
                                            Color(0xFFFE7F00)
                                        else
                                            Color.Red
                                    )
                                }
                                Text(text = "Max Rating: ${data.max_rating}")
                                Text(text = "Rating: ${data.rating}")
                            }
                        }
                    }
                }
            }
            Row(modifier = Modifier.padding(10.dp)) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth(),
                    elevation = 5.dp
                ) {
                    CCGraph(data = data)
                }
            }
        }
    }
}

@Composable
fun CCGraph(data:Codechef){
    val mylist = mutableListOf<DataPoint>()
    var i = 0
    for(j in data.contests) {
        mylist.add(DataPoint(i.toFloat(), j.toFloat()))
        i += 1
    }

    val lines: List<List<DataPoint>> = listOf(mylist)
    LineGraph(
        plot = LinePlot(
            listOf(
                LinePlot.Line(
                    lines[0],
                    LinePlot.Connection(Color(0xFF6767C7), 1.dp),
                    LinePlot.Intersection(Color(0xFF6767C7), 4.dp),
                    LinePlot.Highlight(Color.Black, 4.dp),
                    LinePlot.AreaUnderLine(Color(0xffDEDEFA), 0.3f)
                ),
                LinePlot.Line(
                    lines[0],
                    LinePlot.Connection(Color(0xFF6767C7), 2.dp),
                    LinePlot.Intersection { center, _ ->
                        val px = 2.dp.toPx()
                        val topLeft = Offset(center.x - px, center.y - px)
                        drawRect(Color(0xFF6767C7), topLeft, Size(px * 2, px * 2))
                    },
                ),
            ),
            selection =  LinePlot.Selection(
                highlight = LinePlot.Connection(
                    Color.Black,
                    strokeWidth = 3.dp,
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 20f))
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
fun Setdetail(
    topBar : @Composable ()->Unit,
    codechefViewModel: CodechefViewModel = hiltViewModel()
) {
    val heightInPx = with(LocalDensity.current) { LocalConfiguration.current
        .screenHeightDp.dp.toPx()
    }
    Column {
        topBar()
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