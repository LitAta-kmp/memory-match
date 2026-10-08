# MemoryMatch

Memory card matching game: flip cards and find all the pairs.
Built with Kotlin Multiplatform and Compose Multiplatform (Android target).
This was my first Kotlin Multiplatform project.

<p>
  <img src="docs/start.png" width="30%" alt="New game" />
  <img src="docs/game.png" width="30%" alt="Game in progress" />
</p>

## Features

- Shuffled deck of cards in a grid
- Flip cards to find matching pairs; mismatched cards flip back after a short delay
- Matched pairs stay revealed
- Restart button

## Tech stack

- Kotlin Multiplatform, Compose Multiplatform
- Jetpack ViewModel with Kotlin Coroutines
- StateFlow for UI state

## Architecture

- `domain`: `Card`, `GameState`, `GameIntent`, a game factory and a pure
  `GameReducer` function that turns the current state and an intent into a new state.
- `presentation`: `GameViewModel` exposes state as `StateFlow` using the
  backing-property pattern. The delay before flipping mismatched cards back is a
  side effect handled in the ViewModel with `viewModelScope`.
- UI: `LazyVerticalGrid` with a stateless `CardItem` composable.

The reducer is a pure function with no Android or Compose dependencies.

## Project structure

    shared/src/commonMain/kotlin/org/example/memorymatch/
    ├── domain/         # models, intents, reducer, deck factory
    └── presentation/   # ViewModel and UI components

## Run

Open the project in Android Studio and run the `androidApp` configuration,
or build a debug APK:

    ./gradlew :androidApp:assembleDebug

## Status

- Android: tested on an emulator and a physical device.
- No unit tests for the game logic yet.
- Only Android is set up in this project.

## What I learned

- Setting up a Kotlin Multiplatform project and building a debug APK
- MVI-style state handling with a pure reducer
- Compose basics: state, recomposition, lazy grids, stateless components
- Handling a timed side effect with coroutines