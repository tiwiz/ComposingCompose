package net.composing.compose.sample.wordpress.rest

data class WpAuthor(
    val _links: Links,
    val avatar_urls: AvatarUrls,
    val description: String,
    val id: Int,
    val link: String,
    val meta: List<Any>,
    val name: String,
    val slug: String,
    val url: String
)

data class AvatarUrls(
    val `24`: String,
    val `48`: String,
    val `96`: String
)