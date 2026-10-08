package org.example.memorymatch.domain

enum class CardFace {
    FACE_DOWN,
    FACE_UP,
    MATCHED
}

data class Card(
    val id: Int,
    val emoji: String,
    val face: CardFace = CardFace.FACE_DOWN
)