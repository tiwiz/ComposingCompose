package net.composing.compose.sample.nested.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation

fun NavGraphBuilder.nestedGraph(navController: NavController) {
    navigation(startDestination = "Root", route = "nested_navigation") {
        composable("Root") { NestedNavigation {
            navController.navigate("Blue")
        } }
        composable("Blue") { Blue { navController.navigate("Red") } }
        composable("Red") { Red() }
    }
}

@Composable
fun NestedNavigation(onClick: () -> Unit = {}) {

    Column {
        Button(onClick = onClick) {
            Text("Blue")
        }
    }
}

@Composable
private fun Blue(onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Blue)
    ) {
        Button(onClick = onClick) {
            Text("Red")
        }
    }
}

@Composable
private fun Red() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Red)
    )
}