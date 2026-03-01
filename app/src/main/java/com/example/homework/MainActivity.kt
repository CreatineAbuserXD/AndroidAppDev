package com.example.homework

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.tooling.preview.Preview
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
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                 title = { Text("Homework 1") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Magenta,
                    titleContentColor = Color.Black
                )
            )
        }
    ) { innerPadding ->
        MainScreen(
            modifier = Modifier.fillMaxSize()
                .padding(innerPadding)

        )
    }
}


@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    var  input1 by remember { mutableStateOf("") }
    var  input2 by remember { mutableStateOf("") }

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = "Philip Stipkovits",
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = input1,
            onValueChange = {input1 = it},
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(unfocusedContainerColor = Color.Transparent, focusedContainerColor = Color.Transparent),
            label = { Text("Number 1")}
        )

        TextField(
            value = input2,
            onValueChange = {input2 = it},
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(unfocusedContainerColor =  Color.Transparent, focusedContainerColor = Color.Transparent),
            label = {Text("Number 2")}
        )

    }
}




@Preview(showBackground = true)
@Composable
fun AppPreview(){
    HomeworkTheme() { App()}
}