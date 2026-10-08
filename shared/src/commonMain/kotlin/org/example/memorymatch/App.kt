package org.example.memorymatch

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.example.memorymatch.presentation.GameViewModel
import org.example.memorymatch.presentation.components.CardItem

@Composable
@Preview
fun App() {
    MaterialTheme {
        val viewModel: GameViewModel = viewModel { GameViewModel() }
        val gameState by viewModel.state.collectAsState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .safeContentPadding()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Ходов: ${gameState.moves}")
                Button(onClick = { viewModel.onRestartClicked() }) {
                    Text("Заново")
                }
            }

            if (gameState.isGameComplete) {
                Text(
                    "Поздравляем, все пары найдены!",
                    color = MaterialTheme.colorScheme.primary
                )
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                modifier = Modifier.fillMaxSize()
            ) {
                items(gameState.cards, key = { it.id }) { card ->
                    CardItem(
                        card = card,
                        onClick = { viewModel.onCardClicked(card.id) },
                        modifier = Modifier.padding(4.dp)
                    )
                }
            }
        }
    }
}