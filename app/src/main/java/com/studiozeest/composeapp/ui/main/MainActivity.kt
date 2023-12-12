package com.studiozeest.composeapp.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import androidx.compose.runtime.CompositionLocalProvider
import androidx.core.view.WindowCompat
import com.studiozeest.composeapp.ui.root.RootViewModel
import com.studiozeest.composeapp.ui.theme.JComposeAppTheme
import com.skydoves.landscapist.coil.LocalCoilImageLoader
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

  @VisibleForTesting
  internal val viewModel: RootViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    WindowCompat.setDecorFitsSystemWindows(window, false)
    setContent {
      CompositionLocalProvider(LocalCoilImageLoader provides viewModel.imageLoader) {
        JComposeAppTheme {
          LoginScreen()
        }
      }
    }
  }
}
