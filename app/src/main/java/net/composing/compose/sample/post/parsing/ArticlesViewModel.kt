package net.composing.compose.sample.post.parsing

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.composing.compose.sample.common.Lce
import javax.inject.Inject

@HiltViewModel
class ArticlesViewModel @Inject constructor() : ViewModel(){

    companion object {
        private const val URL =
            "https://www.ilpost.it/2021/03/13/vaccino-coronavirus-johnson-johnson-come-funziona/"
    }

    val article = MutableLiveData<Lce<Article>>()

    fun loadArticle() {
        article.value = Lce.Loading

        viewModelScope.launch(Dispatchers.IO) {
            val articleData = parseArticle(url = URL)
            article.postValue(Lce.Success(articleData))
        }
    }
}