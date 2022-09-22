package com.example.codemaster

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.example.codemaster.components.BottomNav
import com.example.codemaster.components.TabView
import com.example.codemaster.components.TopAppBar
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Scaffold(
                topBar = { TopAppBar() },
                bottomBar = { BottomNav() }
            ) {
                Box(modifier = Modifier.padding(it)){
                    GlideImage(
                        imageModel = "https://cdn.codechef.com/sites/default/files/uploads/pictures/835bd048d07254e68265485936d606ed.jpg"
                    )
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun Main(){
    Scaffold(
        topBar = { TopAppBar() },
        bottomBar = { BottomNav() }
    ) {
        Box(modifier = Modifier.padding(it)){
            TabView()
        }
    }
}