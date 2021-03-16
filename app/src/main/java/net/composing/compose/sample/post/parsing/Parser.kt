package net.composing.compose.sample.post.parsing

import org.jsoup.Jsoup
import org.jsoup.nodes.Element

fun parseArticle(url: String): Article {
    val document = Jsoup.connect(url).get()
    val components = mutableListOf<Component>()

    with(document.body()) {
        components.add(parseTitle(this))
        components.add(parseHeader(this))
        components.add(parseHeaderImage(this))
        components.addAll(parseContent(selectFirst("div#singleBody")))
    }

    return Article(url, components.toList())
}

private fun parseTitle(element: Element): Title =
    Title(text = element.selectFirst("h1.entry-title").text())

private fun parseHeader(element: Element): Header =
    Header(text = element.selectFirst("h2.tit2").text())

private fun parseHeaderImage(element: Element): Image {
    val figure = element.selectFirst("figure")
    val imageUrl = figure.selectFirst("img").attr("data-src")
    val imageCaption = figure.selectFirst("span.caption").text()

    return Image(
        link = imageUrl,
        caption = imageCaption,
        isPromo = true
    )
}

private fun parseContent(element: Element): List<Component> {
    val children = element.children()
    val components = mutableListOf<Component>()

    for (child: Element in children) {
        if (child.`is`("p")) {
            components.add(parseParagraph(child))
        } else if (child.`is`("div")) {
            components.add(parseDiv(child))
        }
    }
    return components.filter { it !is EmptyComponent }.toList()
}

private fun parseParagraph(element: Element): Component {

    if (element.select("img").size > 0) {
        return parseParagraphImage(element)
    }

    val title = try {
        element.selectFirst("strong").text()
    } catch (e: NullPointerException) {
        null
    }

    val content = if (title != null) {
        element.text().replaceFirst(title, "").trim()
    } else {
        element.text().trim()
    }

    return Paragraph(text = content, title = title)
}

private fun parseParagraphImage(element: Element): Component =
    imageFor(
        link =
        element.selectFirst("img").attr("data-src"),
        caption = null
    )

private fun parseDiv(element: Element): Component {
    return when {
        element.id().contains("attachment", ignoreCase = true) -> {
            val imageLink = element.selectFirst("img").attr("data-src")
            val caption = element.selectFirst("p").text()
            imageFor(link = imageLink, caption = caption)
        }
        element.id().equals("mc_embed_signup", ignoreCase = false) -> {
            val html = "${element.html()}\n$FORM_SCRIPT"
            Newsletter(rawHtml = html)
        }
        else -> {
            EmptyComponent
        }
    }
}

private fun imageFor(link: String, caption: String? = null): Component {

    return if (link.contains(".gif", ignoreCase = true)) {
        GifImage(link = link, caption = caption)
    } else {
        Image(link = link, caption = caption)
    }
}