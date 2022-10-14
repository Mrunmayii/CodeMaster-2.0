package com.example.codemaster.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Shimmer(){
    val colors= listOf(
        Color.LightGray.copy(0.4f),
        Color.White.copy(0.4f),
        Color.LightGray.copy(0.2f)
    )
    val transition = rememberInfiniteTransition()
    val translateAnimation = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            )
        )
    )

    val brush = Brush.linearGradient(
        colors = colors,
        start = Offset.Zero,
        end = Offset(x = translateAnimation.value, y = translateAnimation.value)
    )
     ShimmerGridItem(brush = brush)
}

@Composable
fun ShimmerGridItem(
    brush: Brush
) {
    Column(
        modifier = Modifier.padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ){
        Spacer(modifier = Modifier
            .height(55.dp)
            .fillMaxWidth(0.7f)
            .background(brush)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Spacer(modifier = Modifier
            .height(55.dp)
            .fillMaxWidth(0.9f)
            .background(brush)
        )
    }
}