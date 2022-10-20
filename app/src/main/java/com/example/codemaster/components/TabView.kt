package com.example.codemaster.components
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.codemaster.ui.contest_screen.Contest
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import androidx.compose.material3.Tab
import androidx.compose.ui.draw.clip
import com.example.codemaster.ui.contest_screen.FutureContest
import com.example.codemaster.ui.contest_screen.Ongoing
import com.example.codemaster.ui.contest_screen.OngoingCard
import com.example.codemaster.ui.contest_screen.OngoingContest
import kotlinx.coroutines.Dispatchers

// Tab Holder
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabView(){
    val pagerState = rememberPagerState(0)
    Column(
        modifier = Modifier.background(Color.White)
    ) {
        Tabs(pagerState = pagerState)
        TabsContent(pagerState = pagerState)
    }
}

// Tabs
@ExperimentalPagerApi
@Composable
fun Tabs(pagerState: PagerState) {
    val list = listOf(
        "Ongoing",
        "In 24 hrs",
        "Future"
    )
    val scope = rememberCoroutineScope()

    // Tab row
    ScrollableTabRow(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Unspecified),
        selectedTabIndex = pagerState.currentPage,
        containerColor = Color.White,
//        edgePadding = 24.dp,
        contentColor = Color.Black,
        indicator = {
            Box(
                Modifier
                    .tabIndicatorOffset(it[pagerState.currentPage])
                    .height(height = 1.dp)
                    .padding(start = 14.dp, end = 14.dp)
                    .background(color = Color.Black)
            )
        },
        divider = {
            Divider(
                color = Color.White
            )
        }
    ) {
        list.forEachIndexed { index, _ ->
            Tab(
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(Color.White)
                    .padding(vertical = 2.dp),
                text = {
                    Text(
                        text = list[index],
                        color = if (pagerState.currentPage == index) MaterialTheme.colors.primary else Color.LightGray,
                        fontSize = 14.sp
                    )
                },
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch{
                        pagerState.animateScrollToPage(index)
                    }
                }
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@ExperimentalPagerApi
@Composable
fun TabsContent(pagerState: PagerState) {
    HorizontalPager(state = pagerState, count = 3) { page ->
        when (page) {
            0 -> OngoingContest()
            1 -> Contest()
            2 -> FutureContest()
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview(showBackground = true)
fun Main(){
    TabView()
}