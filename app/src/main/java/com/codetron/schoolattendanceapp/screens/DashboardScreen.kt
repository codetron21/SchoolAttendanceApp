package com.codetron.schoolattendanceapp.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.codetron.schoolattendanceapp.ui.theme.SchoolAttendanceAppTheme

@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController()
) {
    
}

@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    SchoolAttendanceAppTheme {
        DashboardScreen()
    }
}