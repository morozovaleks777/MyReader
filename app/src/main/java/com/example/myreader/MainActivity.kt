package com.example.myreader

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.myreader.ui.theme.MyReaderTheme
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyReaderTheme {
                val db=FirebaseFirestore.getInstance()
                val user:MutableMap<String,Any> =HashMap()
                user["firstname"]="Sasha"
                user["lastname"]="Morozov"

                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    db.collection("users")
                        .add(user)
                        .addOnSuccessListener {
                            Log.d("Test", "onCreate: $it.id")
                        }
                        .addOnFailureListener {
                            Log.d("Test", "onCreate: $it ")
                        }
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyReaderTheme {
        Greeting("Android")
    }
}