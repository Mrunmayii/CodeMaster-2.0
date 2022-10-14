package com.example.codemaster

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.codemaster.components.BottomNav
import com.example.codemaster.components.TopAppBar
import com.example.codemaster.components.font
import com.example.codemaster.ui.cf_problems_screen.CFProblemScreen
import com.example.codemaster.ui.cf_ratingChange_screen.CFRatingChangeScreen
import com.example.codemaster.ui.home.HomeScreen
import com.example.codemaster.utils.NavigationGraph
import dagger.hilt.android.AndroidEntryPoint
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            val navController = rememberNavController()
//            Scaffold(
//                topBar = { TopAppBar() },
//                bottomBar = { BottomNav(navController = navController) }
//                ) {
//                NavigationGraph(navController = navController)
//            }
//            Main()
//            HomeScreen()
            CFRatingChangeScreen()
//            CFProblemScreen()
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun Main(){
    val navController = rememberNavController()
    val state = rememberCollapsingToolbarScaffoldState()
    CollapsingToolbarScaffold(
        modifier = Modifier,
        state = state,
        scrollStrategy = ScrollStrategy.EnterAlways,
        toolbar = {
            Text(
                text = "CodeMaster",
                style = TextStyle(color = Color.Black),
                fontSize = 34.sp,
                modifier = Modifier.padding(start = 20.dp, top = 15.dp),
                textAlign = TextAlign.Start,
                fontFamily =  font
            )
        }
    ){
        Scaffold(
        bottomBar = { BottomNav(navController = navController) }
    ){
        NavigationGraph(navController = navController)
    }
    }
}
