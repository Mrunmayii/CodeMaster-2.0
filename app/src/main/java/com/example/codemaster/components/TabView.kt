package com.example.codemaster.components
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import kotlinx.coroutines.Dispatchers

// Tab Holder
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabView(
    intent : Intent
){
    val pagerState = rememberPagerState(0)
    Column(
        modifier = Modifier.background(Color.White)
    ) {
        Tabs(pagerState = pagerState)
        TabsContent(
            pagerState = pagerState,
            intent = intent
        )
    }
}

// Tabs
@ExperimentalPagerApi
@Composable
fun Tabs(pagerState: PagerState) {
    val list = listOf(
        "Ongoing",
        "Today",
        "Upcoming"
    )
    val scope = rememberCoroutineScope()

    // Tab row
    ScrollableTabRow(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Unspecified),
        selectedTabIndex = pagerState.currentPage,
        containerColor = Color.White,
        edgePadding = 24.dp,
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
                    .background(Color.White)
                    .padding(vertical = 2.dp),
                text = {
                    Text(
                        text = list[index],
                        color = if (pagerState.currentPage == index) Color.Black else Color.LightGray,
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
fun TabsContent(
    pagerState: PagerState,
    intent : Intent
) {
    HorizontalPager(state = pagerState, count = 3) { page ->
        when (page) {
            0 -> Contest(intent)
            1 -> Contest(intent)
            2 -> Contest(intent)
        }
    }
}

//@Composable
//@Preview(showBackground = true)
//fun Main(){
//    TabView()
//}