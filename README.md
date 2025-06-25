# Android - MarvelApp
<img width="250" alt="Screenshot 2025-06-25 at 12 43 22" src="https://github.com/user-attachments/assets/beb33503-aa90-4051-b930-9b85409b03fc" />


## What is this repository for?

- This repository is to show you my skills programming native Android.
If you are interested in my profile, you can take a look in order to
value my experience. You can feel free to to make comments,
suggestions or simply contact me if you think that I can add value to
your company/startup or whatever.

## Quick summary

- The repo is an small application that show the Marvel characters.
  It was written in Kotlin language. I used the latest android
  technologies such as AndroidX Jetpack components.
  Additionally I'm using:
  - Coroutines
    - Suspend functions
    - Cold stream with Flow.
  - App separated in layers following the clean architecture guidelines with and app module and core modules.
  - Hilt for Dependency Injection.
  - Android architecture components.
    - LiveData.
    - ViewModel.
    - MVVM Architecture in the presentation layer.
    - Lifecycle extensions.
  - Networking
    - Retrofit for requests
    - Gson for serialize/deserialize objects.
  - Fragments in order to draw screens and support landscape split mode
    and tablets.
  - Material Design components.
  - Patterns
    - Repository.
    - Singleton.
  - Animations.
  - Test
    - JUnit.
    - Mockito.
    - MockKotlin

## The project include the below features.

- Pull to Refresh
- Pagination support
- App state-preservation/restoration
- Indicator of unread/read post (updated status, after post it’s selected)
- Dismiss Post Button (remove the cell from list. Animations required)
- Support split layout (left side: all posts / right side: detail post)

## Resources

- [Marvel API](https://developer.marvel.com/)

## Outstanding issues
- Implement Room in order to complete the repository pattern.
- Fix UI bugs when split mode is showing.
- Complete test cases.


