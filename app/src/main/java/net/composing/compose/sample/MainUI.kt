package net.composing.compose.sample

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import net.composing.compose.sample.Navigation.ACCORDION
import net.composing.compose.sample.Navigation.ARTICLE
import net.composing.compose.sample.Navigation.DIALOG
import net.composing.compose.sample.Navigation.HOME
import net.composing.compose.sample.accordion.AccordionUI
import net.composing.compose.sample.dialog.DialogUI
import net.composing.compose.sample.home.HomeUI
import net.composing.compose.sample.post.parsing.PostParsingUI

@ExperimentalAnimationApi
@Composable
fun MainUI() {
    val navController = rememberNavController()

    fun Navigation.go() {
        navController.navigate(asString)
    }

    NavHost(navController = navController, startDestination = HOME.asString) {
        composable(HOME.asString) {
            HomeUI(
                onPostParsingClicked = { ARTICLE.go() },
                onAccordionExampleClicked = { ACCORDION.go() },
                onDialogSampleClicked = { DIALOG.go() }
            )
        }
        composable(ARTICLE.asString) {
            PostParsingUI()
        }
        composable(ACCORDION.asString) {
            AccordionUI()
        }
        composable(DIALOG.asString) {
            DialogUI()
        }
    }
}

enum class Navigation(val asString: String) {
    HOME("home"),
    ARTICLE("article-parsing"),
    ACCORDION("accordion-sample"),
    DIALOG("dialog")
}