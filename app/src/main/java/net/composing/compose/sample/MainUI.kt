package net.composing.compose.sample

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import net.composing.compose.sample.post.parsing.PostParsingUI

@Composable
fun MainUI() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Navigation.ARTICLE.asString) {
        composable(Navigation.HOME.asString) {

        }
        composable(Navigation.ARTICLE.asString) {
            PostParsingUI()
        }
    }
}

enum class Navigation(val asString: String) {
    HOME("home"),
    ARTICLE("article-parsing")
}