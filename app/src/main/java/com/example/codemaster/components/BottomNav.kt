package com.example.codemaster.components
import androidx.compose.foundation.layout.height
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.codemaster.R

@Composable
fun BottomNav(){
    val items = listOf(
        R.drawable.icons_contest,
        R.drawable.icons_codechef,
        R.drawable.icons_codeforces,
        R.drawable.icons_leetcode
    )
    var selectedItem by remember { mutableStateOf(0) }
    BottomNavigation(
        backgroundColor = Color.White,
        elevation = 120.dp,
        modifier = Modifier.height(62.dp),
        contentColor = Color.Gray
    ){
        items.forEachIndexed { index, item ->
            BottomNavigationItem(
                selected = selectedItem == index,
                icon = { Icon(painterResource(id = item), contentDescription = null) },
                onClick = { selectedItem = index },
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.LightGray
            )
        }
    }
}
