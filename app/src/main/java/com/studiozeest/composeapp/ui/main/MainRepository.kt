package com.studiozeest.composeapp.ui.main

import androidx.annotation.WorkerThread
import com.studiozeest.composeapp.model.Problems
import com.studiozeest.composeapp.network.ApiService
import com.studiozeest.composeapp.persistence.PosterDao
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import timber.log.Timber
import javax.inject.Inject

class MainRepository @Inject constructor(
  private val apiService: ApiService,
  private val posterDao: PosterDao
) {

  init {
    Timber.d("Injection MainRepository")
  }

  @WorkerThread
  fun loadDisneyPosters(
    onStart: () -> Unit,
    onCompletion: () -> Unit,
    onError: (String) -> Unit
  ) = flow {
    val posters: List<Problems> = posterDao.getPosterList()
    if (posters.isEmpty()) {
      // request API network call asynchronously.
      apiService.fetchDisneyPosterList()
        // handle the case when the API request gets a success response.
        .suspendOnSuccess {
          posterDao.insertPosterList(data)
          emit(data)
        }
        // handle the case when the API request is fails.
        // e.g. internal server error.
        .onFailure { onError(message()) }
    } else {
      emit(posters)
    }
  }.onStart { onStart() }.onCompletion { onCompletion() }.flowOn(Dispatchers.IO)
}
