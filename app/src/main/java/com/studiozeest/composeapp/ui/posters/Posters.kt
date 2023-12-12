package com.studiozeest.composeapp.ui.posters

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.studiozeest.composeapp.model.Problems
import com.studiozeest.composeapp.ui.main.MainViewModel
import com.studiozeest.composeapp.ui.theme.purple200
import java.util.Calendar

@Composable
fun Posters(
  viewModel: MainViewModel,
  username: String,
  selectPoster: (Long) -> Unit
) {
  val posters: List<Problems> by viewModel.posterList.collectAsState(initial = listOf())
  val isLoading: Boolean by viewModel.isLoading

  ConstraintLayout {
    val (body, progress) = createRefs()
    Scaffold(
      backgroundColor = MaterialTheme.colors.primarySurface,
      topBar = { PosterAppBar(
        username = username
      ) },
      modifier = Modifier.constrainAs(body) {
        top.linkTo(parent.top)
      }
    ) { innerPadding ->
      val modifier = Modifier.padding(innerPadding)
      RadioPosters(modifier, posters, selectPoster)
    }
    if (isLoading) {
      CircularProgressIndicator(
        modifier = Modifier
          .constrainAs(progress) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
          }
      )
    }
  }
}
@Composable
private fun PosterAppBar(
  username: String
) {
  val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
  val greeting : String
  if(currentHour in 0..11){
    greeting = "Good Morning"
  }else if(currentHour in 12..15){
    greeting = "Good Afternoon"
  }else
    greeting = "Good Evening"
  TopAppBar(
    elevation = 6.dp,
    backgroundColor = purple200,
    modifier = Modifier
      .statusBarsPadding()
      .height(58.dp)
  ) {
    Text(
      modifier = Modifier
        .padding(8.dp)
        .align(Alignment.CenterVertically),
      text = "$greeting $username!",
      color = Color.White,
      fontSize = 18.sp,
      fontWeight = FontWeight.Bold
    )
  }
}
