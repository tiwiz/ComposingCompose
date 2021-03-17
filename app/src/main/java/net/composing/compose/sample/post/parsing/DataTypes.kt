package net.composing.compose.sample.post.parsing

sealed class Component

data class Title(val text: String) : Component()

data class Header(val text: String) : Component()

data class Image(val link: String, val caption: String?, val isPromo: Boolean = false) : Component()

data class GifImage(val link: String, val caption: String?) : Component()

data class Paragraph(val text: String, val title: String? = null) : Component()

data class LinkifiedText(val caption: String, val link : String? = null)

data class LinkifiedParagraph(val text: List<LinkifiedText>, val title: String? = null) : Component()

data class Newsletter(val rawHtml: String) : Component()

object EmptyComponent: Component()

data class Article(
    val url: String,
    val components: List<Component>
)