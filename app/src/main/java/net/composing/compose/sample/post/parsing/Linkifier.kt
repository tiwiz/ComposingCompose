package net.composing.compose.sample.post.parsing

import org.jsoup.nodes.Element
import org.jsoup.nodes.TextNode

fun parseParagraphWithLinks(element: Element): List<LinkifiedText> {

    return element.childNodes().mapNotNull { child ->
        when (child) {
            is Element -> extractTextWithLinks(child)
            is TextNode -> LinkifiedText(caption = child.text())
            else -> null
        }
    }.toList()
}

private fun extractTextWithLinks(element: Element): LinkifiedText? =
    if (element.hasAttr("href")) {
        LinkifiedText(caption = element.text(), link = element.attr("href"))
    } else {
        null
    }

fun Element.toParagraphWithLinks(title: String?) : List<LinkifiedText> {
    val elements = parseParagraphWithLinks(this)

    return if (elements.first().caption == title) {
        elements.subList(1, elements.size - 1)
    } else {
        elements
    }
}
