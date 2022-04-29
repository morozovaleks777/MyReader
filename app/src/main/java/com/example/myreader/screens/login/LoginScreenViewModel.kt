package com.example.myreader.screens.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.myreader.model.MUser
import com.example.myreader.navigation.ReaderScreens
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class LoginScreenViewModel:ViewModel() {
    val loadingState = MutableStateFlow(LoadingState.IDLE)
    private val auth: FirebaseAuth=Firebase.auth
    private val _loading =MutableLiveData(false)
    val loading:LiveData<Boolean> = _loading

    fun createUserWithEmailAndPassword(email: String,password: String,home:() -> Unit){
        if(_loading.value==false) {_loading.value=true
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val displayName = task.result.user?.email?.split("@")?.get(0)
                createUser(displayName)
                home()

            } else {
                Log.d("Test", "createUserWithEmailAndPassword: ${task.result.toString()}")
            }
        }
            _loading.value= false
        }


    }

    private fun createUser(displayName: String?) {
       val userId = auth.currentUser?.uid
        val user = MUser(
        userId = userId.toString(),
        displayName=displayName.toString(),
        quote = "life is life",
        profession = "android developer",
        avatarUrl = "",
        id=null).toMap()
        FirebaseFirestore.getInstance().collection("users")
            .add(user)

    }


    fun signInWithEmailAndPassword(email:String, password:String,home:() -> Unit)=viewModelScope.launch{
        try {
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {task ->
                if(task.isSuccessful){
                    Log.d("Test", "signInWithEmailAndPassword: yeayea ${task.result.toString()} ")
                    home()
                }else {
                    Log.d("Test", "signInWithEmailAndPassword:${task.result.toString()} ")

                }
            }

        }catch (ex:Exception){
            Log.d("Test", "signInWithEmailAndPassword: ${ex.message}")

        }

    }

}