package net.composing.compose.sample.simpleui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.composing.compose.sample.home.buttonColors

@Composable
fun SimpleUI() {

    var clicks by remember { mutableStateOf(0) }

    Column {
        Button(
            onClick = { clicks++ },
            colors = buttonColors(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp)
        ) {
            Text(
                text = "Click me".uppercase(),
                color = MaterialTheme.colors.background
            )
        }

        if (clicks > 0) {
            Text(
                text = "$clicks",
                modifier = Modifier.fillMaxWidth().testTag("clicks"),
                textAlign = TextAlign.Center,
                fontSize = 24.sp
            )
        }
    }
}