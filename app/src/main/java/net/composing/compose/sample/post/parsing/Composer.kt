package net.composing.compose.sample.post.parsing

import android.content.Context
import android.os.Build.VERSION.SDK_INT
import android.webkit.WebView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import coil.ImageLoader
import coil.compose.rememberImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest

@Composable
fun DrawBlogPost(article: Article) {
    SelectionContainer {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {
            article.components.forEach {
                Draw(it)
            }
        }
    }
}

@Composable
private fun Draw(component: Component) {
    when (component) {
        is Title -> DrawTitle(title = component)
        is Header -> DrawHeader(header = component)
        is Image -> DrawImage(image = component)
        is GifImage -> DrawGifImage(image = component)
        is Paragraph -> DrawParagraph(paragraph = component)
        is LinkifiedParagraph -> DrawLinkifiedParagraph(paragraph = component)
        is Newsletter -> DrawNewsletter(newsletter = component)
        else -> Unit
    }
}

@Composable
private fun DrawTitle(title: Title) {
    Text(
        title.text,
        style = MaterialTheme.typography.h1,
        modifier = Modifier.padding(all = 16.dp)
    )
}

@Composable
private fun DrawHeader(header: Header) {
    Text(
        header.text,
        style = MaterialTheme.typography.h2,
        modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
    )
}

@Composable
private fun DrawImage(image: Image) {
    DisableSelection {
        Image(
            painter = rememberImagePainter(image.link),
            contentDescription = image.caption,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp),
            contentScale = ContentScale.FillWidth
        )
        image.caption?.let { caption ->
            Text(
                caption,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = 24.dp, end = 24.dp, bottom = 8.dp)
            )
        }
    }
}

@Composable
private fun DrawGifImage(image: GifImage) {
    DisableSelection {
        val imageRequest = ImageRequest.Builder(LocalContext.current)
            .data(image.link).build()
        Image(
            painter = rememberImagePainter(
                request = imageRequest,
                imageLoader = gifImageLoader(LocalContext.current)
            ),
            contentDescription = image.caption,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp, top = 8.dp)
        )
        image.caption?.let { caption ->
            Text(
                caption,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = 24.dp, end = 24.dp, bottom = 8.dp)
            )
        }
    }
}

@Composable
private fun DrawParagraph(paragraph: Paragraph) {
    paragraph.title?.let { title ->
        Text(
            title,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(top = 8.dp, start = 8.dp, end = 8.dp)
        )
    }

    Text(
        paragraph.text,
        style = MaterialTheme.typography.body1,
        modifier = Modifier.padding(bottom = 8.dp, start = 8.dp, end = 8.dp)
    )
}

@Composable
fun DrawLinkifiedParagraph(paragraph: LinkifiedParagraph) {

    paragraph.title?.let { title ->
        Text(
            title,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(top = 8.dp, start = 8.dp, end = 8.dp)
        )
    }

    val annotatedText = buildAnnotatedString {
        paragraph.text.forEach { linkifiedText ->
            if (linkifiedText.link != null) {
                pushStringAnnotation(tag = "URL", annotation = linkifiedText.link)
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colors.primary,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append(linkifiedText.caption)
                }
                pop()
            } else {
                append(linkifiedText.caption)
            }
        }
    }

    ClickableText(text = annotatedText,
        modifier = Modifier.padding(bottom = 8.dp, start = 8.dp, end = 8.dp),
        style = MaterialTheme.typography.body1,
        onClick = { offset ->
            annotatedText.getStringAnnotations(start = offset, end = offset)
                .firstOrNull()?.let { annotation ->
                    println("Clicked on: ${annotation.tag}-${annotation.item}")
                }
        }
    )
}

@Composable
private fun DrawNewsletter(newsletter: Newsletter) {
    DisableSelection {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 8.dp)
        ) {

            AndroidView(factory = { context -> WebView(context) }) { webview ->
                webview.settings.javaScriptEnabled = true
                webview.loadData(newsletter.rawHtml, "text/html; charset=utf-8", "UTF-8")
            }
        }
    }
}

private fun gifImageLoader(context: Context): ImageLoader = ImageLoader.Builder(context)
    .componentRegistry {
        if (SDK_INT >= 28) add(ImageDecoderDecoder()) else add(GifDecoder())
    }
    .build()