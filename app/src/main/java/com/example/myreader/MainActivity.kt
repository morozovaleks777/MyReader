package com.example.myreader

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myreader.navigation.ReaderNavigation
import com.example.myreader.ui.theme.MyReaderTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@ExperimentalComposeUiApi
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyReaderTheme {
ReaderApp()


            }
        }
    }
}

@ExperimentalComposeUiApi
@Composable
fun ReaderApp() {
    // A surface container using the 'background' color from the theme
    Surface(color = MaterialTheme.colors.background,
        modifier =Modifier.fillMaxSize() ) {
Column(verticalArrangement = Arrangement.Center,
horizontalAlignment = Alignment.CenterHorizontally) {
ReaderNavigation()
}

    }
}

@ExperimentalComposeUiApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyReaderTheme {
    ReaderApp()
    }
}