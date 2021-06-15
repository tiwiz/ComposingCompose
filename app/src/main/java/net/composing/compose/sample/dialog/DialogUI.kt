package net.composing.compose.sample.dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import net.composing.compose.sample.R
import net.composing.compose.sample.accordion.LOREM_IPSUM
import net.composing.compose.sample.home.buttonColors

@Composable
fun DialogUI() {

    var openDialog by remember { mutableStateOf(false) }
    var openAlertDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                openDialog = true
            },
            colors = buttonColors(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp)
        ) {
            Text(
                "Open Dialog".uppercase(),
                color = MaterialTheme.colors.background
            )
        }

        if (openDialog) {
            Dialog(
                onDismissRequest = { openDialog = false },
                properties = DialogProperties(
                    dismissOnBackPress = true,
                    dismissOnClickOutside = false
                )
            ) {
                Card {
                    Column(modifier = Modifier.padding(all = 16.dp)) {
                        Text(text = LOREM_IPSUM)
                        Button(
                            onClick = { openDialog = false },
                            colors = buttonColors()
                        ) {
                            Text(
                                "Close Dialog".uppercase(),
                                color = MaterialTheme.colors.background
                            )
                        }
                    }
                }
            }
        }

        Button(
            onClick = {
                openAlertDialog = true
            },
            colors = buttonColors(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp)
        ) {
            Text(
                "Open AlertDialog".uppercase(),
                color = MaterialTheme.colors.background
            )
        }

        if (openAlertDialog) {
            AlertDialog(
                onDismissRequest = { openAlertDialog = false },
                title = { Text("AlertDialog title") },
                text = {
                    Column {
                        Text(text = LOREM_IPSUM)
                        Image(
                            painter = painterResource(id = R.drawable.ic_android),
                            contentDescription = ""
                        )
                    }
                },
                buttons = {
                    Row {
                        Button(
                            onClick = {
                                openAlertDialog = false
                            },
                            colors = buttonColors()
                        ) {
                            Text(
                                "Close #1".uppercase(),
                                color = MaterialTheme.colors.background
                            )
                        }

                        Button(
                            onClick = {
                                openAlertDialog = false
                            },
                            colors = buttonColors()
                        ) {
                            Text(
                                "Close #2".uppercase(),
                                color = MaterialTheme.colors.background
                            )
                        }
                    }
                },
                properties = DialogProperties(
                    dismissOnBackPress = true,
                    dismissOnClickOutside = false
                )
            )
        }
    }
}