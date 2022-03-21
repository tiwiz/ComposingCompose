package net.composing.compose.sample.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults.textButtonColors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.composing.compose.sample.ui.theme.ComposingComposeTheme

@Composable
fun buttonColors() =
    textButtonColors(
        backgroundColor = MaterialTheme.colors.primary
    )

@Composable
fun HomeUI(
    onPostParsingClicked: () -> Unit = {},
    onAccordionExampleClicked: () -> Unit = {},
    onDialogSampleClicked: () -> Unit = {},
    onSimpleUiClicked: () -> Unit = {},
    onNestedNavigationClicked: () -> Unit = {}
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = onPostParsingClicked,
            colors = buttonColors(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp)
        ) {
            Text(
                text = "Post Parsing".uppercase(),
                color = MaterialTheme.colors.background
            )
        }

        Button(
            onClick = onAccordionExampleClicked,
            colors = buttonColors(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp)
        ) {
            Text(
                text = "Accordion sample".uppercase(),
                color = MaterialTheme.colors.background
            )
        }

        Button(
            onClick = onDialogSampleClicked,
            colors = buttonColors(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp)
        ) {
            Text(
                text = "Dialog sample".uppercase(),
                color = MaterialTheme.colors.background
            )
        }

        Button(
            onClick = onSimpleUiClicked,
            colors = buttonColors(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp)
        ) {
            Text(
                text = "Simple UI".uppercase(),
                color = MaterialTheme.colors.background
            )
        }

        Button(
            onClick = onNestedNavigationClicked,
            colors = buttonColors(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp)
        ) {
            Text(
                text = "Nested navigation".uppercase(),
                color = MaterialTheme.colors.background
            )
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun Preview() {
    ComposingComposeTheme {
        HomeUI()
    }
}