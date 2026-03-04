package com.example.homework

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.androidilv.R
import com.example.homework.ui.theme.HomeworkTheme


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HomeworkTheme {
                App()
                }
            }
        }
    }

@OptIn(ExperimentalMaterial3Api::class) //Experimentielle API -> might change
@Composable
fun App() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                topBar = {
                    TopAppBar(
                        title = { Text(stringResource(R.string.app_name)) },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Color.Magenta,
                            titleContentColor = Color.Black
                        )
                    )
                }
            ) { innerPadding ->
                MainScreen(
                    modifier = Modifier.fillMaxSize()
                        .padding(innerPadding),
                    navController = navController
                )
            }
        }
        composable("result/{value}") {
            backStackEntry -> val value = backStackEntry.arguments?.getString("value") ?: "0"
            ResultScreen(value = value)
        }

    }
}

//test

@Composable
fun MainScreen(modifier: Modifier = Modifier, navController: NavController) {
    var  input1 by remember { mutableStateOf("0") }
    var  input2 by remember { mutableStateOf("0") }
    var result by remember { mutableStateOf("0") }
    var sliderValue by remember { mutableStateOf(0f) } //f = float weil Slider braucht float

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.author_name),
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = input1,
            onValueChange = {input1 = it},
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(unfocusedContainerColor = Color.Transparent, focusedContainerColor = Color.Transparent),
            label = { Text(stringResource(R.string.input1_label))}
        )

        TextField(
            value = input2,
            onValueChange = {input2 = it},
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(unfocusedContainerColor =  Color.Transparent, focusedContainerColor = Color.Transparent),
            label = {Text(stringResource(R.string.input2_label))}
        )

        Button(
            onClick = {
                val num1 = input1.toIntOrNull() ?: 0
                val num2 = input2.toIntOrNull() ?: 0
                result = (num1 + num2).toString()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.btn_calculate))
        }

        Text(
            text = result,
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                val num1 = input1.toIntOrNull() ?: 0
                val num2 = input2.toIntOrNull() ?: 0
                navController.navigate("result/${num1 + num2}")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.btn_navigate))
        }

        Slider(
            value = sliderValue,
            onValueChange =  { sliderValue = it},    //sofort updaten beim bewegen
            valueRange =  0f..100f,
            modifier = Modifier.fillMaxWidth()
            )

        Text(
            text = sliderValue.toInt().toString(),
            modifier = Modifier.fillMaxWidth()
        )

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultScreen(value: String){
    Scaffold( //Scaffold könnte man auslagern da same pattern, und clean Arch, aber für die Übung is ok
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.app_name)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Magenta,
                    titleContentColor = Color.Black
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Text(
                text = value,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview(){
    HomeworkTheme() { App()}
}


/*
  App() startet
→ NavHost sagt "beginne bei main"
→ composable("main") wird ausgeführt = Scaffold + MainScreen wird angezeigt
→ navController wird an MainScreen weitergegeben damit er ihn "benutzen" kann

→ User klickt Navigate Button
→ navController.navigate("result/16")
→ NavHost sagt "okay geh zu composable("result/{value}")"
→ composable("result/{value}") wird ausgeführt, value = "16"
→ ResultScreen wird angezeigt

→ User klickt ←
→ popBackStack()
→ zurück zu composable("main")
     */
