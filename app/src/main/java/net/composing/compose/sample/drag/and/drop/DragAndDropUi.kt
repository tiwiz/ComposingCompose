package net.composing.compose.sample.drag.and.drop

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.burnoutcrew.reorderable.detectReorderAfterLongPress
import org.burnoutcrew.reorderable.draggedItem
import org.burnoutcrew.reorderable.move
import org.burnoutcrew.reorderable.rememberReorderState
import org.burnoutcrew.reorderable.reorderable

@Preview
@Composable
fun DragAndDropUi() {
    val state = rememberReorderState()
    val modelsState = models.toMutableStateList()

    LazyColumn(
        state = state.listState,
        modifier = Modifier
            .reorderable(state, { fromPos, toPos ->
                modelsState.move(fromPos.index, toPos.index)
            })
            .detectReorderAfterLongPress(state) // 3.
    ) {
        items(modelsState, { it }) { item ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .padding(4.dp)
                    .border(width = 2.dp, color = MaterialTheme.colors.primaryVariant)
                    .draggedItem(state.offsetByKey(item))
                    .detectReorderAfterLongPress(state),
                contentAlignment = Alignment.Center
            ) {
                Text(text = item)
            }
        }
    }

}

private val models = listOf(
    "Item 1",
    "Item 2",
    "Item 3",
    "Item 4",
    "Item 5",
    "Item 6",
    "Item 7",
    "Item 8",
    "Item 9",
    "Item 10",
    "Item 11",
    "Item 12",
    "Item 13",
    "Item 14",
    "Item 15",
    "Item 16",
    "Item 17",
    "Item 18",
    "Item 19",
    "Item 20",
)