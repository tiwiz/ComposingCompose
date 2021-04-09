package net.composing.compose.sample.accordion

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private const val LOREM_IPSUM =
    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam suscipit, dui vel lobortis dignissim, libero sapien finibus dui, ut vehicula metus ipsum at nisi. Maecenas eget pulvinar quam. Etiam rutrum mauris eu massa imperdiet convallis. Nam suscipit tortor eget imperdiet bibendum. Nullam sed commodo nibh. Integer commodo nibh sit amet mattis facilisis. Etiam sed enim non lectus molestie euismod a id urna."

@ExperimentalAnimationApi
@Composable
fun AccordionUI() {

    var isDescriptionVisible by remember { mutableStateOf(false) }
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Description",
                modifier = Modifier.padding(all = 16.dp),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            IconButton(onClick = { isDescriptionVisible = !isDescriptionVisible }) {
                RotatingButton(isDescriptionVisible = isDescriptionVisible)
            }
        }

        AnimatedVisibility(isDescriptionVisible) {
            Text(
                text = LOREM_IPSUM,
                modifier = Modifier.padding(all = 16.dp),
                fontSize = 14.sp,
                textAlign = TextAlign.Justify
            )
        }

        Divider()

        Text(
            "Some other text not very important",
            modifier = Modifier.padding(all = 16.dp),
            fontSize = 18.sp,
        )
    }
}

@Composable
private fun RotatingButton(isDescriptionVisible: Boolean) {
    Icon(
        imageVector = Icons.Filled.KeyboardArrowDown, contentDescription = "Show/hide description",
        modifier = Modifier.rotate(
            animateFloatAsState(targetValue = if (isDescriptionVisible) 180f else 0f).value
        )
    )
}
