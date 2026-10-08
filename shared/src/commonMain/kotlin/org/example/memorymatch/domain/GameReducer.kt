package org.example.memorymatch.domain

fun reduce(state: GameState, intent: GameIntent): GameState {
    return when (intent) {
        is GameIntent.CardClicked -> handleCardClicked(state, intent.cardId)
        GameIntent.HideMismatchedCards -> handleHideMismatched(state)
        GameIntent.RestartGame -> handleRestart()
    }
}

private fun handleRestart(): GameState {
    return GameState(cards = createShuffledDeck())
}

private fun handleCardClicked(state: GameState, cardId: Int): GameState {
    if (state.mismatchedPair != null) return state // ждём, пока не совпавшие карточки закроются
    val clickedCard = state.cards.find { it.id == cardId } ?: return state
    if (clickedCard.face != CardFace.FACE_DOWN) return state // уже открыта или найдена

    val cardsWithClickedFlipped = state.cards.map {
        if (it.id == cardId) it.copy(face = CardFace.FACE_UP) else it
    }

    val firstFlippedId = state.firstFlippedCardId
    if (firstFlippedId == null) {
        return state.copy(cards = cardsWithClickedFlipped, firstFlippedCardId = cardId)
    }

    val firstCard = state.cards.first { it.id == firstFlippedId }
    return if (firstCard.emoji == clickedCard.emoji) {
        val updatedCards = cardsWithClickedFlipped.map {
            if (it.id == cardId || it.id == firstCard.id) it.copy(face = CardFace.MATCHED) else it
        }
        state.copy(
            cards = updatedCards,
            firstFlippedCardId = null,
            moves = state.moves + 1,
            isGameComplete = updatedCards.all { it.face == CardFace.MATCHED }
        )
    } else {
        state.copy(
            cards = cardsWithClickedFlipped,
            firstFlippedCardId = null,
            mismatchedPair = firstCard.id to cardId,
            moves = state.moves + 1
        )
    }
}

private fun handleHideMismatched(state: GameState): GameState {
    val pair = state.mismatchedPair ?: return state
    val updatedCards = state.cards.map {
        if (it.id == pair.first || it.id == pair.second) it.copy(face = CardFace.FACE_DOWN) else it
    }
    return state.copy(cards = updatedCards, mismatchedPair = null)
}