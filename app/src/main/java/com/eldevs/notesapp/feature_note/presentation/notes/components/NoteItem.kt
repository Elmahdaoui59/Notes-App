package com.eldevs.notesapp.feature_note.presentation.notes.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ExpandCircleDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.eldevs.notesapp.feature_note.domain.model.Note

@Composable
fun NoteItem(
    modifier: Modifier = Modifier,
    note: Note,
    onDeleteClick: () -> Unit
) {
    Box(
        modifier = modifier.background(
            color = Color(note.color), shape = RoundedCornerShape(
                8.dp
            )
        )
    ) {
        var isExpanded by remember { mutableStateOf(false) }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(end = 32.dp)

        ) {
            Text(
                text = note.title,
                style = MaterialTheme.typography.h6,
                color = Color.Black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = note.content,
                style = MaterialTheme.typography.body1,
                color = Color.Black,
                maxLines = if (isExpanded) Int.MAX_VALUE else 2,
                overflow = TextOverflow.Ellipsis
            )
        }
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxHeight()
                .align(Alignment.TopEnd)
        ) {
            IconButton(
                onClick = onDeleteClick,
                modifier = Modifier
                    .background(color = Color.Transparent)
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete Note",
                    tint = Color.Black
                )
            }
            IconButton(
                onClick = { isExpanded = !isExpanded },
                modifier = Modifier
                    .background(color = Color.Transparent)
            ) {
                Icon(
                    imageVector = Icons.Default.ExpandCircleDown,
                    contentDescription = "Expand content",
                    tint = Color.Black
                )
            }
        }
    }
}

