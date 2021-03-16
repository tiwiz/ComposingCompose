package net.composing.compose.sample.post.parsing

import assertk.assertThat
import assertk.assertions.hasSize
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import org.junit.Test

class ParserTest {

    companion object {
        const val TEST_URL =
            "https://www.ilpost.it/2021/03/13/vaccino-coronavirus-johnson-johnson-come-funziona/"
    }

    @Test
    fun `verify title is parsed correctly`() {
        val expectedTitle = "Come funziona il vaccino di Johnson & Johnson"

        val title = parseArticle(TEST_URL).components.filterIsInstance<Title>()

        assertThat(title).hasSize(1)
        assertThat(title.firstOrNull()).isNotNull()
        assertThat(title.first().text).isEqualTo(expectedTitle)
    }

    @Test
    fun `verify header is parsed correctly`() {
        val expectedHeader =
            "In che modo insegna al nostro sistema immunitario a riconoscere il coronavirus e ad affrontarlo, senza il rischio di ammalarsi"

        val header = parseArticle(TEST_URL).components.filterIsInstance<Header>()

        assertThat(header).hasSize(1)
        assertThat(header.firstOrNull()).isNotNull()
        assertThat(header.first().text).isEqualTo(expectedHeader)
    }

    @Test
    fun `verify promo image is parsed correctly`() {
        val imageUrl =
            "https://cdn.ilpost.it/wp-content/uploads/2021/03/GettyImages-1231560614.jpg?x72029"
        val expectedCaption = "(Michael Ciaglo/Getty Images)"

        val image = parseArticle(TEST_URL).components.filterIsInstance<Image>()
            .filter { it.isPromo }

        assertThat(image).hasSize(1)
        assertThat(image.firstOrNull()).isNotNull()
        with(image.first()) {
            assertThat(link).isEqualTo(imageUrl)
            assertThat(caption).isEqualTo(expectedCaption)
        }
    }

    @Test
    fun `verify content is parsed correctly`() {
        val article = parseArticle(TEST_URL).components
        val images = article.filterIsInstance<Image>()
            .filter { !it.isPromo }
        val paragraphs = article.filterIsInstance<Paragraph>()
        val gifs = article.filterIsInstance<GifImage>()
        val paragraphsWithSubtitle = paragraphs.filter { it.title != null }
        val newsletter = article.filterIsInstance<Newsletter>()

        assertThat(paragraphs).hasSize(16)
        assertThat(paragraphsWithSubtitle).hasSize(7)
        assertThat(images).hasSize(1)
        assertThat(newsletter).hasSize(1)
        assertThat(gifs).hasSize(1)
    }
}