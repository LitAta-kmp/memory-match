package org.example.memorymatch.domain

data class GameState(
    val cards: List<Card> = emptyList(),
    val firstFlippedCardId: Int? = null,
    val mismatchedPair: Pair<Int, Int>? = null,
    val moves: Int = 0,
    val isGameComplete: Boolean = false
)