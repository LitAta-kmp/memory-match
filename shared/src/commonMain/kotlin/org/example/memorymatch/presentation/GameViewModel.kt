package org.example.memorymatch.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.memorymatch.domain.*

class GameViewModel : ViewModel() {

    private val _state = MutableStateFlow(
        GameState(cards = createShuffledDeck())
    )
    val state: StateFlow<GameState> = _state.asStateFlow()

    fun onRestartClicked() {
        _state.value = reduce(_state.value, GameIntent.RestartGame)
    }
    fun onCardClicked(cardId: Int) {
        _state.value = reduce(_state.value, GameIntent.CardClicked(cardId))

        if (_state.value.mismatchedPair != null) {
            viewModelScope.launch {
                delay(800)
                _state.value = reduce(_state.value, GameIntent.HideMismatchedCards)
            }
        }
    }
}