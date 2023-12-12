package com.studiozeest.composeapp.ui.posters

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.atLeast
import androidx.constraintlayout.compose.atMost
import com.studiozeest.composeapp.model.Problems
import com.studiozeest.composeapp.ui.theme.JComposeAppTheme
import com.studiozeest.composeapp.utils.NetworkImage

@Composable
fun MedicationTile(
  poster: Problems.DiseaseInfo,
) {
  val context = LocalContext.current
  Surface(
    modifier = Modifier
      .fillMaxWidth()
      .padding(4.dp)
      .clickable(
        onClick = { Toast.makeText(context,poster.toString(),Toast.LENGTH_SHORT).show() }
      ),
    color = MaterialTheme.colors.onBackground,
    elevation = 8.dp,
    shape = RoundedCornerShape(8.dp)
  ) {
    ConstraintLayout(
      modifier = Modifier.padding(8.dp)
    ) {
      val (image, title) = createRefs()

      NetworkImage(
        modifier = Modifier
          .constrainAs(image) {
            centerVerticallyTo(parent)
            end.linkTo(title.start)
          }
          .height(64.dp)
          .aspectRatio(1f)
          .clip(RoundedCornerShape(4.dp)),
        url = "https://cdn-icons-png.flaticon.com/512/507/507579.png"
      )

      Text(
        modifier = Modifier
          .constrainAs(title) {
            start.linkTo(image.end)
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
          }
          .padding(horizontal = 12.dp),
        text = poster.medications.toString(),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.h2
      )
    }
  }
}

@Composable
@Preview(name = "RadioPoster Light")
private fun RadioPosterPreviewLight() {
  JComposeAppTheme(darkTheme = false) {
    MedicationTile(
      poster = Problems.DiseaseInfo.mock()
    )
  }
}
