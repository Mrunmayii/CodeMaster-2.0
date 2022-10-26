package com.example.codemaster.ui.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.codemaster.components.AlertBox
import com.example.codemaster.components.CustomTextField
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment


@Composable
fun HomeScreen(
    viewModel : InputListViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(235, 248, 255, 255))
            .padding(30.dp)
    ){
        Row(
            modifier = Modifier.padding(bottom = 30.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = null
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 20.dp),
                text = "Select Platform Details",
                textAlign = TextAlign.Center
            )
        }
        Text(text = "Codechef", modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp))
        CustomTextField(value = viewModel.CCusername, platform = "codechef")
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "Codeforces", modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp))
        CustomTextField(value = viewModel.CFusername, platform = "codeforces")
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "Leetcode", modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp))
        CustomTextField(value = viewModel.LCusername, platform = "leetcode")
        Spacer(modifier = Modifier.height(12.dp))
    }
}

//    val keyMap = remember { mutableMapOf("codechef" to "", "codeforces" to "", "leetcode" to "") }
//    Column(modifier = Modifier
//        .fillMaxSize()
//        .padding(30.dp))
//    {
//        Cardd(platform = "codechef", viewModel.cc,viewModel.CCusername,keyMap)
//        Spacer(modifier = Modifier.padding(10.dp))
//        Cardd(platform = "codeforces", viewModel.cf,viewModel.CFusername,keyMap)
//        Spacer(modifier = Modifier.padding(10.dp))
//        Cardd(platform = "leetcode", viewModel.lc, viewModel.LCusername,keyMap)
//        Spacer(modifier = Modifier.padding(10.dp))
//        Button(
//            onClick = {
//                viewModel.onEvent(InputListEvent.OnClick)
//                Log.d("kk", keyMap.toString())
//            }
//        ){
//
//        }
//
//    }
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun Cardd(
//    platform: String,
//    username: String,
//    value : String,
//    keyMap : MutableMap<String, String>
//){
//    val openDialog = remember { mutableStateOf(false) }
//    if (openDialog.value) {
//        AlertBox(
//            platform = platform,
//            openDialog = openDialog.value,
//            onDismiss = { openDialog.value = false },
//            value = value,
//            keyMap = keyMap
//        )
//    }
//    Card(
//        modifier = Modifier
//            .size(height = 65.dp, width = 3000.dp)
//            .padding(bottom = 5.dp),
//        colors = CardDefaults.cardColors(
//            Color(241, 233, 255, 255)
//        ),
//        shape = RoundedCornerShape(5.dp),
//        onClick = {
//            openDialog.value = true
//        }
//    ) {
//        Row(
//            modifier = Modifier,
//            horizontalArrangement = Arrangement.SpaceEvenly
//        ) {
//            Text(
//                text = platform,
//                modifier = Modifier
//                    .padding(20.dp),
//                textAlign = TextAlign.Center
//            )
//            Text(
//                text = username,
//                modifier = Modifier.padding(20.dp),
//                textAlign = TextAlign.Center,
//                color = Color.Black
//            )
//            Log.d("kalpp",username)
//        }
//    }

