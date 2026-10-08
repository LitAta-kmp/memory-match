package org.example.memorymatch.domain

private val EMOJIS = listOf("🐶", "🐱", "🦊", "🐼", "🦁", "🐸", "🐵", "🐰")

fun createShuffledDeck(pairsCount: Int = 8): List<Card> {
    val selectedEmojis = EMOJIS.take(pairsCount)
    val pairedEmojis = selectedEmojis + selectedEmojis
    return pairedEmojis
        .shuffled()
        .mapIndexed { index, emoji -> Card(id = index, emoji = emoji) }
}