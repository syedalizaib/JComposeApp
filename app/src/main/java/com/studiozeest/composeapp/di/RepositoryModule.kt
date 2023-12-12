package com.studiozeest.composeapp.di

import com.studiozeest.composeapp.network.ApiService
import com.studiozeest.composeapp.persistence.PosterDao
import com.studiozeest.composeapp.ui.main.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

  @Provides
  @ViewModelScoped
  fun provideMainRepository(
    apiService: ApiService,
    posterDao: PosterDao
  ): MainRepository {
    return MainRepository(apiService, posterDao)
  }
}
