# TvPrime

An Android app consuming TheMovieDb API to display list of tv series, built with Compose, MVVM pattern as
well as Architecture Components.

Min Api Level: 26
Target Api Level : 34

Build System : [Gradle](https://gradle.org/)

## Table of Contents

- [Prerequisite](#prerequisite)
- [App](#app)
- [Architecture](#architecture)
- [Libraries](#libraries)


## App

The app loads the the tv series from the api and displays them in a list. When the TvSeries is clicked the
app navigates to the Details Screen which shows more details.

## Architecture

The app is built using the Modular MVVM architectural pattern and makes heavy use of a couple of
Android Jetpack components. MVVM allows for the separation of concern which also makes testing
easier. The app has composable screen that communicates to ViewModel which in turn communicates to
the usecase which communicates with Repository to get data.


## Libraries

Libraries used in the whole application are:

- [Jetpack](https://developer.android.com/jetpack)🚀
    - [Viewmodel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Manage UI
      related data in a lifecycle conscious way
    - [Compose](https://developer.android.com/courses/pathways/compose) - A modern declarative way
      to build android ui
    - [Paging 3](https://developer.android.com/topic/libraries/architecture/paging/v3-overview) used
      for paging
- [Retrofit](https://square.github.io/retrofit/) - Type-safe HTTP client and supports coroutines out
  of the box. Used for the network calls.
- [Gson](https://github.com/google/gson) - Used to convert JSON to Java/Kotlin classes for the
  Retrofit
- [okhttp-logging-interceptor](https://github.com/square/okhttp/blob/master/okhttp-logging-interceptor/README.md)
    - logs HTTP request and response data.
- [kotlinx.coroutines](https://github.com/Kotlin/kotlinx.coroutines) - Library Support for
  coroutines
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - Used for
  Dependency injection
