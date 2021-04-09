package net.composing.compose.sample.wordpress.rest

data class WpMedia(
    val _links: Links,
    val alt_text: String,
    val amp_enabled: Boolean,
    val amp_validity: Any,
    val author: Int,
    val caption: Caption,
    val comment_status: String,
    val date: String,
    val date_gmt: String,
    val description: Description,
    val guid: Guid,
    val id: Int,
    val link: String,
    val media_details: MediaDetails,
    val media_type: String,
    val meta: List<Any>,
    val mime_type: String,
    val modified: String,
    val modified_gmt: String,
    val ping_status: String,
    val post: Int,
    val slug: String,
    val source_url: String,
    val status: String,
    val template: String,
    val title: Title,
    val type: String
)


data class Caption(
    val rendered: String
)

data class Description(
    val rendered: String
)


data class MediaDetails(
    val `file`: String,
    val height: Int,
    val image_meta: ImageMeta,
    val sizes: Sizes,
    val width: Int
)

data class ImageMeta(
    val aperture: String,
    val camera: String,
    val caption: String,
    val copyright: String,
    val created_timestamp: String,
    val credit: String,
    val focal_length: String,
    val iso: String,
    val keywords: List<Any>,
    val orientation: String,
    val shutter_speed: String,
    val title: String
)

data class Sizes(
    val full: Full,
    val large: Large,
    val medium: Medium,
    val medium_large: MediumLarge,
    val thumbnail: Thumbnail
)

data class Full(
    val `file`: String,
    val height: Int,
    val mime_type: String,
    val source_url: String,
    val width: Int
)

data class Large(
    val `file`: String,
    val height: Int,
    val mime_type: String,
    val source_url: String,
    val width: Int
)

data class Medium(
    val `file`: String,
    val height: Int,
    val mime_type: String,
    val source_url: String,
    val width: Int
)

data class MediumLarge(
    val `file`: String,
    val height: Int,
    val mime_type: String,
    val source_url: String,
    val width: Int
)

data class Thumbnail(
    val `file`: String,
    val height: Int,
    val mime_type: String,
    val source_url: String,
    val width: Int
)