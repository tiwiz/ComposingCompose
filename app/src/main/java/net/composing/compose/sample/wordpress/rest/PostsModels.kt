package net.composing.compose.sample.wordpress.rest

class WpRoot : ArrayList<WpRootItem>()

data class WpRootItem(
    val _links: Links,
    val amp_enabled: Boolean,
    val amp_validity: Any,
    val author: Int,
    val categories: List<Int>,
    val comment_status: String,
    val content: Content,
    val date: String,
    val date_gmt: String,
    val excerpt: Excerpt,
    val featured_media: Int,
    val format: String,
    val guid: Guid,
    val id: Int,
    val link: String,
    val meta: List<Any>,
    val modified: String,
    val modified_gmt: String,
    val ping_status: String,
    val slug: String,
    val status: String,
    val sticky: Boolean,
    val tags: List<Int>,
    val template: String,
    val title: Title,
    val type: String
)

data class Links(
    val about: List<About>,
    val author: List<Author>,
    val collection: List<Collection>,
    val curies: List<Cury>,
    val predecessor_version: List<PredecessorVersion>,
    val replies: List<Reply>,
    val self: List<Self>,
    val version_history: List<VersionHistory>,
    val wp_attachment: List<WpAttachment>,
    val wp_featuredmedia: List<WpFeaturedmedia>,
    val wp_term: List<WpTerm>
)

data class Content(
    val `protected`: Boolean,
    val rendered: String
)

data class Excerpt(
    val `protected`: Boolean,
    val rendered: String
)

data class Guid(
    val rendered: String
)

data class Title(
    val rendered: String
)

data class About(
    val href: String
)

data class Author(
    val embeddable: Boolean,
    val href: String
)

data class Collection(
    val href: String
)

data class Cury(
    val href: String,
    val name: String,
    val templated: Boolean
)

data class PredecessorVersion(
    val href: String,
    val id: Int
)

data class Reply(
    val embeddable: Boolean,
    val href: String
)

data class Self(
    val href: String
)

data class VersionHistory(
    val count: Int,
    val href: String
)

data class WpAttachment(
    val href: String
)

data class WpFeaturedmedia(
    val embeddable: Boolean,
    val href: String
)

data class WpTerm(
    val embeddable: Boolean,
    val href: String,
    val taxonomy: String
)