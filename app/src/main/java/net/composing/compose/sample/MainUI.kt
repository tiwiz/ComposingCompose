package net.composing.compose.sample

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import net.composing.compose.sample.Navigation.*
import net.composing.compose.sample.accordion.AccordionUI
import net.composing.compose.sample.dialog.DialogUI
import net.composing.compose.sample.home.HomeUI
import net.composing.compose.sample.nested.navigation.NestedNavigation
import net.composing.compose.sample.nested.navigation.nestedGraph
import net.composing.compose.sample.post.parsing.PostParsingUI
import net.composing.compose.sample.simpleui.SimpleUI

@ExperimentalAnimationApi
@Composable
fun MainUI() {
    val navController = rememberNavController()

    fun Navigation.go() {
        navController.navigate(asString)
    }

    @Composable
    fun HomeUiWithParams() = HomeUI(
        onPostParsingClicked = { ARTICLE.go() },
        onAccordionExampleClicked = { ACCORDION.go() },
        onDialogSampleClicked = { DIALOG.go() },
        onSimpleUiClicked = { SIMPLE_UI.go() },
        onNestedNavigationClicked = { NESTED_NAVIGATION.go() }
    )

    val map = mapOf<String, @Composable () -> Unit>(
        HOME.asString to @Composable { HomeUiWithParams() },
        ARTICLE.asString to @Composable { PostParsingUI() },
        ACCORDION.asString to @Composable { AccordionUI() },
        DIALOG.asString to @Composable { DialogUI() },
        SIMPLE_UI.asString to @Composable { SimpleUI() }
    )

    NavHost(navController = navController, startDestination = HOME.asString) {
        map.forEach { (key, destination) ->
            composable(key) { destination() }
        }

        nestedGraph(navController)
    }
}

enum class Navigation(val asString: String) {
    HOME("home"),
    ARTICLE("article-parsing"),
    ACCORDION("accordion-sample"),
    DIALOG("dialog"),
    SIMPLE_UI("simple-ui"),
    NESTED_NAVIGATION("nested_navigation")
}