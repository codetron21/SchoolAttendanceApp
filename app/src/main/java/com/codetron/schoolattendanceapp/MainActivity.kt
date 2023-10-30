package com.codetron.schoolattendanceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.codetron.schoolattendanceapp.screens.ForgotPasswordScreen
import com.codetron.schoolattendanceapp.screens.LoginScreen
import com.codetron.schoolattendanceapp.ui.theme.SchoolAttendanceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SchoolAttendanceAppTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "login") {
                    composable("login") {
                        LoginScreen()
                    }

                    composable("forgot-password") {
                        ForgotPasswordScreen()
                    }
                }
            }
        }
    }
}
