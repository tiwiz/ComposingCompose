package net.composing.compose.sample.post.parsing

sealed class Component

data class Title(val text: String) : Component()

data class Header(val text: String) : Component()

data class Image(val link: String, val caption: String?, val isPromo: Boolean = false) : Component()

data class GifImage(val link: String, val caption: String?) : Component()

data class Paragraph(val text: String, val title: String? = null) : Component()

object Newsletter : Component()

object EmptyComponent: Component()

data class Article(
    val url: String,
    val components: List<Component>
)