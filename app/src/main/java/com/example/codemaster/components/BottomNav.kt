package com.example.codemaster.components
import androidx.compose.foundation.layout.height
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.codemaster.utils.Nav

@Composable
fun BottomNav(navController: NavController){
    val items = listOf(
        Nav.CONTESTS,
        Nav.CODECHEF,
        Nav.CODEFORCES,
        Nav.LEETCODE
    )
    var selectedItem by remember { mutableStateOf(0) }
    BottomNavigation(
        backgroundColor = Color.White,
        elevation = 120.dp,
        modifier = Modifier.height(62.dp),
        contentColor = Color.Gray,
    ){
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
//                selected = selectedItem == index,
                icon = { Icon(painterResource(id = item.icon), contentDescription = null) },
//                onClick = { selectedItem = index },
                selectedContentColor = MaterialTheme.colors.primary,
                unselectedContentColor = Color.LightGray,
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route){
                        navController.graph.startDestinationRoute?.let { screen_route ->
                            popUpTo(screen_route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}
