package com.example.instagramclone.presentation.auth.main

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.instagramclone.DestinationScreen
import com.example.instagramclone.viewModel.IgViewModel


@Composable
fun NotificationMessage(vm: IgViewModel){
    val notifState = vm.popupNotification.value
    val notifMessage = notifState?.getContentOrNull()

    if (notifMessage != null) {
        Toast.makeText(LocalContext.current, notifMessage, Toast.LENGTH_LONG).show()
    }
}

@Composable
fun CommonProgessSpinner(){
    Row(
        modifier = Modifier
            .alpha(0.8f)
            .background(Color.Black)
            .fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
        CircularProgressIndicator()
    }
}

fun navigateTo(navController: NavController, dest: DestinationScreen){
    navController.navigate(dest.route){  //make sure that we don't end up having large number of screen in our back stack
        popUpTo(dest.route)
        launchSingleTop = true
    }
}

@Composable
fun CheckSignedIn(navController: NavController, vm: IgViewModel){
    val alreadyLoggedIn = remember{ mutableStateOf(false) }
    val signedIn = vm.signedIn.value
    if(signedIn && !alreadyLoggedIn.value){
        alreadyLoggedIn.value = true
        navController.navigate(DestinationScreen.Feed.route){
            popUpTo(0)
        }
    }
}