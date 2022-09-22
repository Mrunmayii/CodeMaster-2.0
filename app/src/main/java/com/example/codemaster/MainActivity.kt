package com.example.codemaster

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.compose.rememberNavController
import com.example.codemaster.components.*
import com.example.codemaster.ui.codechef_screen.Setdetail
import com.example.codemaster.ui.home.HomeScreen
import com.example.codemaster.utils.NavigationGraph
import dagger.hilt.android.AndroidEntryPoint
import me.onebone.toolbar.CollapsingToolbar
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
            val navController = rememberNavController()
            Scaffold(
                topBar = { TopAppBar() },
                bottomBar = { BottomNav(navController = navController) }
                ) {
                NavigationGraph(navController = navController)
            }
//            Main()
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
//        Scaffold(
//        bottomBar = { BottomNav(navController = navController) }
//    ){
//        NavigationGraph(navController = navController)
//    }
    }
}
