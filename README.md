
<h1 align="center">JComposeApp</h1></br>
<p align="center">  
A demo app using compose and Hilt based on modern Android tech-stacks and MVVM architecture. Fetching data from the network and integrating persisted data in the database via repository pattern.<br> Declarative UI version using compose.
</p>
</br>

## Mock API for API Response
[Click Here](https://run.mocky.io/v3/2a7c0bc0-6430-4df5-8b78-7ac466b75595) to open API URL.

## Screenshots
<p align="center">
<img src="/preview/preview.gif" width="270"/>
</p>

## Tech stack & Open-source libraries
- [Kotlin](https://kotlinlang.org/) based + [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous.
- Hilt for dependency injection.
- JetPack
  - Compose - A modern toolkit for building native Android UI.
  - Lifecycle - dispose observing data when lifecycle state changes.
  - ViewModel - UI related data holder, lifecycle aware.
  - Room Persistence - construct database.
  - App Startup - Provides a straightforward, performant way to initialize components at application startup.
- Architecture
  - MVVM Architecture (Declarative View - ViewModel - Model)
  - Repository pattern
- Unit tests
- Material Design & Animations
- [Accompanist](https://github.com/google/accompanist) - A collection of extension libraries for Jetpack Compose.
- [Landscapist](https://github.com/skydoves/landscapist) - Jetpack Compose image loading library with shimmer & circular reveal animations.
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - construct the REST APIs and paging network data.
- [Sandwich](https://github.com/skydoves/Sandwich) - construct lightweight http API response and handling error responses.
- [WhatIf](https://github.com/skydoves/whatif) - checking nullable object and empty collections more fluently.
- [Timber](https://github.com/JakeWharton/timber) - logging.