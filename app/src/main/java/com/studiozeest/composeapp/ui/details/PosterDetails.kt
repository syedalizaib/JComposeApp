package com.studiozeest.composeapp.ui.details

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.palette.graphics.Palette
import com.studiozeest.composeapp.extensions.paletteColorList
import com.studiozeest.composeapp.model.Problems
import com.studiozeest.composeapp.ui.custom.StaggeredVerticalGrid
import com.studiozeest.composeapp.ui.posters.MedicationTile
import com.studiozeest.composeapp.utils.NetworkImage
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.palette.rememberPaletteState

@Composable
fun PosterDetails(
  posterId: Long,
  viewModel: DetailViewModel,
  pressOnBack: () -> Unit = {}
) {
  LaunchedEffect(key1 = posterId) {
    viewModel.loadPosterById(posterId)
  }

  val details: Problems? by viewModel.posterDetailsFlow.collectAsState(initial = null)
  details?.let { poster ->
    PosterDetailsBody(poster, pressOnBack)
  }
}

@Composable
private fun PosterDetailsBody(
  poster: Problems,
  pressOnBack: () -> Unit
) {
  var palette by rememberPaletteState(value = null)
  LazyColumn(
    modifier = Modifier
      .fillMaxWidth()
      .background(MaterialTheme.colors.background),
      contentPadding = PaddingValues(bottom = 70.dp)
  ) {

    item {
      ConstraintLayout {
        val (arrow, image, paletteRow, title, content, gifTitle) = createRefs()

        NetworkImage(
          url = "https://cdn-icons-png.flaticon.com/512/507/507579.png",
          modifier = Modifier
            .constrainAs(image) {
              top.linkTo(parent.top)
            }
            .background(MaterialTheme.colors.primaryVariant)
            .fillMaxWidth()
            .aspectRatio(0.85f),
          circularRevealEnabled = true,
          paletteLoadedListener = { palette = it }
        )

        ColorPalettes(
          palette = palette,
          modifier = Modifier
            .constrainAs(paletteRow) {
              top.linkTo(image.bottom)
            }
        )

        Text(
          text = poster.diseaseName,
          style = MaterialTheme.typography.h1,
          overflow = TextOverflow.Ellipsis,
          maxLines = 1,
          modifier = Modifier
            .constrainAs(title) {
              top.linkTo(paletteRow.bottom)
            }
            .padding(start = 16.dp, top = 12.dp)
        )

        Text(
          text = poster.diseaseName,
          style = MaterialTheme.typography.body2,
          modifier = Modifier
            .constrainAs(content) {
              top.linkTo(title.bottom)
            }
            .padding(16.dp)
        )

        Text(
          text = "Medications",
          style = MaterialTheme.typography.h2,
          textAlign = TextAlign.Center,
          modifier = Modifier
            .padding(16.dp)
            .constrainAs(gifTitle) {
              top.linkTo(content.bottom)
            }
        )

        Icon(
          imageVector = Icons.Filled.ArrowBack,
          tint = Color.White,
          contentDescription = null,
          modifier = Modifier
            .constrainAs(arrow) {
              top.linkTo(parent.top)
            }
            .padding(12.dp)
            .statusBarsPadding()
            .clickable(onClick = { pressOnBack() })
        )
      }
    }
    items(
      items = poster.diseaseInfo,
      itemContent = { med ->
        MedicationTile(med)
      }
    )
  }
}

@Composable
private fun ColorPalettes(
  palette: Palette?,
  modifier: Modifier
) {
  val colorList: List<Int> = palette.paletteColorList()
  LazyRow(
    modifier = modifier
      .padding(horizontal = 8.dp, vertical = 16.dp)
  ) {
    items(colorList) { color ->
      Crossfade(
        targetState = color,
        modifier = Modifier
          .padding(horizontal = 8.dp)
          .size(45.dp)
      ) {
        Box(
          modifier = Modifier
            .background(color = Color(it))
            .fillMaxSize()
        )
      }
    }
  }
}
