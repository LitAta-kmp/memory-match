package org.example.memorymatch.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.memorymatch.domain.Card
import org.example.memorymatch.domain.CardFace

@Composable
fun CardItem(
    card: Card,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundColor = when (card.face) {
        CardFace.FACE_DOWN -> MaterialTheme.colorScheme.primary
        CardFace.FACE_UP -> MaterialTheme.colorScheme.secondaryContainer
        CardFace.MATCHED -> MaterialTheme.colorScheme.tertiaryContainer
    }

    Box(
        modifier = modifier
            .aspectRatio(1f)
            .clip(RoundedCornerShape(12.dp))
            .background(backgroundColor)
            .clickable(enabled = card.face == CardFace.FACE_DOWN, onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        if (card.face != CardFace.FACE_DOWN) {
            Text(text = card.emoji, fontSize = 32.sp, fontWeight = FontWeight.Bold)
        }
    }
}