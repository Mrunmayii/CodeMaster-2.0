package com.example.codemaster.ui.contest_screen

import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.codemaster.MyApplication
import com.example.codemaster.R
import com.example.codemaster.broadcasts.SetAlarmBroadcast
import com.example.codemaster.components.ErrorDialog
import com.example.codemaster.components.Shimmer
import com.example.codemaster.data.model.Contest
import com.example.codemaster.data.model.ContestItem
import java.text.SimpleDateFormat
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale
import java.util.TimeZone

val alarmService = SetAlarmBroadcast()
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Contest(
    intent : Intent,
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
            is ContestUiState.Success -> Contests(
                data = state.data,
                intent = intent
            )
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Contests(
    data: Contest,
    intent: Intent
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 52.dp)
    ) {
        items(
            data.filter { it.in_24_hours == "Yes" }
        ) {
            ContestCard(
                data = it,
                intent = intent
            )
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ContestCard(
    data: ContestItem,
    intent : Intent
){
    Column(
        modifier = Modifier
            .fillMaxSize()
    ){
        Divider(
            modifier = Modifier.fillMaxSize(1f),
            color = Color(0xFFF2F2F5),
            thickness = 2.dp
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Column(
                modifier = Modifier
                    .padding(15.dp)
                    .width(250.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    val painter: Painter
                    var colour: Color = Color.Black
                    if(data.site == "CodeChef") {
                        painter = painterResource(id = R.drawable.icons_codechef)
                        colour = Color.Red
                    }
                    else if(data.site == "CodeForces") {
                        painter = painterResource(id = R.drawable.icons_codeforces)
                        colour = Color(0xFF66C1EE)
                    }
                    else if(data.site == "LeetCode")
                        painter = painterResource(id = R.drawable.icons_leetcode)
                    else if(data.site == "HackerRank")
                        painter = painterResource(id = R.drawable.icons_hackerrank)
                    else if(data.site == "HackerEarth")
                        painter = painterResource(id = R.drawable.icons_hackerearth)
                    else if(data.site == "AtCoder")
                        painter = painterResource(id = R.drawable.icons_atcoder)
                    else
                        painter = painterResource(id = R.drawable.icons_google)
                    Image(
                        painter = painter,
                        contentDescription = "logo",
                    )
                    Text(
                        text = data.site,
                        modifier = Modifier.padding(8.dp),
                        color = colour,
                        fontWeight = Bold
                    )
                }
                Text(
                    text = data.name,
                    fontWeight = Bold
                )
                //date
                if(data.site == "CodeChef") {
                    data.start_time.toDate()?.let {
                        Text(
                            text =  it.formatTo("dd MMM, yyyy")
                        )
                    }
                }
                else {
                    val odt = OffsetDateTime.parse(data.start_time)
                    val dtf = DateTimeFormatter.ofPattern("dd MMM, uuuu", Locale.ENGLISH)
                    Text(
                        text = dtf.format(odt)
                    )
                }
                //no. of hours
                val x = (data.duration).toIntOrNull()
                val length = x?.div(3600)
                if(length == null){
                    Text(
                        text = ""
                    )
                }
                else{
                    Text(
                        text = "Duration: ${length.toString()} hrs"
                    )
                }
            }
            Column(
                modifier = Modifier.width(100.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Image(
                    painter = painterResource(id = R.drawable.icons_alarm),
                    contentDescription = "Reminder",
                    modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.End)
                        .clickable {
                            intent.putExtra("platform", data.site)
                            intent.putExtra("contest", data.name)
                            Log.d("kalp", data.name)
                            Log.d("kalp", data.site)
                            alarmService.setAlarm(
                                MyApplication.instance,
                                data.site,
                                data.name,
                                data.start_time
                            )
                        }
                )
            }
        }
    }
}

fun String.toDate(dateFormat: String = "yyyy-MM-dd HH:mm:ss", timeZone: TimeZone = TimeZone.getTimeZone("UTC")): Date? {
    val parser = SimpleDateFormat(dateFormat, Locale.getDefault())
    parser.timeZone = timeZone
    return parser.parse(this)
}
fun Date.formatTo(dateFormat: String, timeZone: TimeZone = TimeZone.getDefault()): String {
    val formatter = SimpleDateFormat(dateFormat, Locale.getDefault())
    formatter.timeZone = timeZone
    return formatter.format(this)
}