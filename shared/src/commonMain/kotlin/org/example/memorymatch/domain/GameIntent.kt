package org.example.memorymatch.domain

sealed class GameIntent {
    data class CardClicked(val cardId: Int) : GameIntent()
    object HideMismatchedCards : GameIntent()
    object RestartGame : GameIntent()
}