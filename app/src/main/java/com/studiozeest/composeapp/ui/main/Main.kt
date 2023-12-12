package com.studiozeest.composeapp.ui.main

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.studiozeest.composeapp.ui.details.PosterDetails
import com.studiozeest.composeapp.ui.posters.Posters
import com.studiozeest.composeapp.ui.theme.JComposeAppTheme

@Composable
fun MainScreen(
  username: String
) {
  val navController = rememberNavController()

  val colors = MaterialTheme.colors
  val systemUiController = rememberSystemUiController()

  var statusBarColor by remember { mutableStateOf(colors.primaryVariant) }
  var navigationBarColor by remember { mutableStateOf(colors.primaryVariant) }

  val animatedStatusBarColor by animateColorAsState(
    targetValue = statusBarColor,
    animationSpec = tween()
  )
  val animatedNavigationBarColor by animateColorAsState(
    targetValue = navigationBarColor,
    animationSpec = tween()
  )

  NavHost(navController = navController, startDestination = NavScreen.Home.route) {
    composable(NavScreen.Home.route) {
      Posters(
        viewModel = hiltViewModel(),
        username = username,
        selectPoster = {
          navController.navigate("${NavScreen.PosterDetails.route}/$it")
        }
      )

      LaunchedEffect(Unit) {
        statusBarColor = colors.primaryVariant
        navigationBarColor = colors.primaryVariant
      }
    }
    composable(
      route = NavScreen.PosterDetails.routeWithArgument,
      arguments = listOf(
        navArgument(NavScreen.PosterDetails.argument0) { type = NavType.LongType }
      )
    ) { backStackEntry ->
      val posterId =
        backStackEntry.arguments?.getLong(NavScreen.PosterDetails.argument0) ?: return@composable

      PosterDetails(posterId = posterId, viewModel = hiltViewModel()) {
        navController.navigateUp()
      }

      LaunchedEffect(Unit) {
        statusBarColor = Color.Transparent
        navigationBarColor = colors.background
      }
    }
  }

  LaunchedEffect(animatedStatusBarColor, animatedNavigationBarColor) {
    systemUiController.setStatusBarColor(animatedStatusBarColor)
    systemUiController.setNavigationBarColor(animatedNavigationBarColor)
  }
}

sealed class NavScreen(val route: String) {

  object Home : NavScreen("Home")

  object PosterDetails : NavScreen("PosterDetails") {

    const val routeWithArgument: String = "PosterDetails/{posterId}"

    const val argument0: String = "posterId"
  }
}
@Preview(showBackground = true, device = "id:Nexus One", showSystemUi = true)
@Composable
fun MainScreenPreview() {
  JComposeAppTheme {
    MainScreen(username = "user")
  }
}

@Preview(showBackground = true, device = "id:Nexus One", showSystemUi = true)
@Composable
fun MainScreenPreviewDark() {
  JComposeAppTheme(darkTheme = true) {
    MainScreen(username = "user")
  }
}
