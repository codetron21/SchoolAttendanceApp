package com.codetron.schoolattendanceapp.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun AttendanceScreen(
    modifier:Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {

    Text("Attendance")

}