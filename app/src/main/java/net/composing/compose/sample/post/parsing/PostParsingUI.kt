package net.composing.compose.sample.post.parsing

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import net.composing.compose.sample.common.Lce

@Composable
fun PostParsingUI(viewModel: ArticlesViewModel = viewModel()) {
    viewModel.loadArticle()
    PostRenderingUI(viewModel)
}

@Composable
private fun PostRenderingUI(viewModel: ArticlesViewModel = viewModel()) {
    val state by viewModel.article.observeAsState(initial = Lce.Loading)

    if (state is Lce.Success) {
        DrawBlogPost(article = state.data!!)
    }
}