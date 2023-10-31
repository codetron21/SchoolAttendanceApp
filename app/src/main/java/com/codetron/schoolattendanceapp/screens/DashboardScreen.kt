package com.codetron.schoolattendanceapp.screens

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.codetron.schoolattendanceapp.R
import com.codetron.schoolattendanceapp.ui.theme.SchoolAttendanceAppTheme

sealed class DashboardMenuScreen(
    val route: String,
    @StringRes val titleId: Int,
    @DrawableRes val drawableId: Int
) {
    object Attendance : DashboardMenuScreen(
        "att",
        R.string.attendance,
        R.drawable.ic_attendance
    )

    object ViewAttendance :
        DashboardMenuScreen(
            "view-att",
            R.string.view_attendance,
            R.drawable.ic_list
        )

    object Profile : DashboardMenuScreen(
        "profile",
        R.string.profile,
        R.drawable.ic_account
    )
}

val menus = listOf(
    DashboardMenuScreen.Attendance,
    DashboardMenuScreen.ViewAttendance,
    DashboardMenuScreen.Profile,
)

@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier,
) {
    val navController: NavHostController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Box(modifier = modifier.fillMaxSize()) {
        NavHost(
            navController,
            startDestination = DashboardMenuScreen.Profile.route,
        ) {
            composable(DashboardMenuScreen.Attendance.route) {
                AttendanceScreen(navController = navController)
            }
            composable(DashboardMenuScreen.ViewAttendance.route) {
                ViewAttendanceScreen(navController = navController)
            }
            composable(DashboardMenuScreen.Profile.route) {
                ProfileScreen(navController = navController)
            }
        }

        DashboardMenuContainer(
            menu = menus.first { it.route == currentDestination?.route },
            menuOnClicked = { menu ->
                navController.navigate(menu.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
    }
}

@Composable
fun DashboardMenuContainer(
    modifier: Modifier = Modifier,
    menu: DashboardMenuScreen,
    menuOnClicked: (DashboardMenuScreen) -> Unit,
) {
    Row(
        modifier = modifier
            .padding(dimensionResource(id = R.dimen.layout_padding))
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(4.dp)
            )
    ) {
        menus.forEach { m ->
            DashboardMenu(
                icon = painterResource(id = m.drawableId),
                title = stringResource(m.titleId),
                selected = menu == m,
                modifier = Modifier
                    .weight(1F)
                    .clickable {
                        menuOnClicked(m)
                    }
            )
        }
    }
}

@Composable
fun DashboardMenu(
    modifier: Modifier = Modifier,
    icon: Painter,
    title: String,
    selected: Boolean,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(4.dp)
    ) {
        Icon(
            icon,
            tint = if (selected) MaterialTheme.colorScheme.onPrimary else Color.White,
            contentDescription = null,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            title,
            color = if (selected) MaterialTheme.colorScheme.onPrimary else Color.White,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    SchoolAttendanceAppTheme {
        DashboardScreen()
    }
}