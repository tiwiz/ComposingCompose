package net.composing.compose.sample.post.parsing

import assertk.assertThat
import assertk.assertions.containsAll
import assertk.assertions.doesNotContain
import assertk.assertions.hasSize
import assertk.assertions.isTrue
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.jsoup.parser.Parser
import org.junit.Test

class LinkifierTest {

    companion object {
        private val TARGET_HTML = """
            <p>Con l’autorizzazione di emergenza per il vaccino contro il coronavirus di Johnson &amp; Johnson (J&amp;J) <a href="https://www.ilpost.it/2021/03/11/vaccino-coronavirus-johnson-johnson-autorizzato-unione-europea/">da parte dell’Unione Europea</a> e poi <a href="https://www.aifa.gov.it/web/guest/-/aifa-approva-il-vaccino-janssen">da parte dell’Agenzia Italiana del Farmaco (AIFA)</a>, si è aggiunta una quarta soluzione per contrastare la pandemia oltre a quelle già disponibili di Pfizer-BioNTech, Moderna e AstraZeneca. Il vaccino è stato autorizzato in seguito alla raccomandazione dell’Agenzia europea per i medicinali (EMA) e dovrebbe consentire di accelerare la campagna vaccinale in Europa perché richiede la somministrazione di una sola dose, a differenza degli altri vaccini finora autorizzati che ne richiedono due a distanza di qualche settimana.</p>
        """.trimIndent()

        private val TARGET_HTML_NO_LINKS = """
            <p>Nei mesi scorsi l’Unione Europea aveva contrattato la fornitura di almeno 200 milioni di dosi, a partire dal secondo trimestre di quest’anno. Le prime consegne dovrebbero quindi avvenire tra la fine di marzo e l’inizio di aprile, ma è probabile che non si entrerà a pieno regime da subito, come emerso negli ultimi giorni.</p>
        """.trimIndent()

        private const val TITLE = "Adenovirus"

        private val TARGET_HTML_WITH_TITLE = """
            <p><strong>$TITLE</strong><br> Il vaccino è stato sviluppato da <a href="https://www.janssen.com/it">Janssen Pharmaceutica</a> (di proprietà di J&amp;J) in collaborazione con il <a href="https://www.bidmc.org/">Centro Medico Beth Israel Deaconess</a> (Stati Uniti). A differenza di quelli di Pfizer-BioNTech e di Moderna, <a href="https://www.ilpost.it/2020/11/20/vaccino-coronavirus-mrna-moderna-pfizer-biontech/">basati su RNA messaggero</a>, il vaccino di J&amp;J utilizza un virus (Adenovirus 26, o Ad26) sostanzialmente innocuo per il nostro organismo: in generale, gli adenovirus causano sintomi lievi, simili a quelli del raffreddore.</p>
        """.trimIndent()
    }

    private val targetElement: Element =
        Jsoup.parse(TARGET_HTML, "", Parser.htmlParser())
            .selectFirst("p")

    private val targetElementNoLinks: Element =
        Jsoup.parse(TARGET_HTML_NO_LINKS, "", Parser.htmlParser())
            .selectFirst("p")

    private val targetElementWithTitle: Element =
        Jsoup.parse(TARGET_HTML_WITH_TITLE, "", Parser.htmlParser())
            .selectFirst("p")

    @Test
    fun `verify link are found correctly`() {
        val expectedLinks = arrayOf(
            "https://www.ilpost.it/2021/03/11/vaccino-coronavirus-johnson-johnson-autorizzato-unione-europea/",
            "https://www.aifa.gov.it/web/guest/-/aifa-approva-il-vaccino-janssen"
        )

        val p = targetElement.toParagraphWithLinks(null)
        val links : Array<String> = p.filter { it.link != null }.mapNotNull{ it.link }.toTypedArray()

        assertThat(p).hasSize(5)
        assertThat(links).hasSize(2)
        assertThat(links).containsAll(*expectedLinks)
    }

    @Test
    fun `verify text without links are parsed correctly`() {
        val p = targetElementNoLinks.toParagraphWithLinks(null)

        assertThat(p).hasSize(1)
        assertThat(p.all { it.link == null }).isTrue()
    }

    @Test
    fun `verify text with title and links are parsed correctly`() {
        val p = targetElementWithTitle.toParagraphWithLinks(TITLE)

        val expectedLinks = arrayOf(
            "https://www.janssen.com/it",
            "https://www.bidmc.org/",
            "https://www.ilpost.it/2020/11/20/vaccino-coronavirus-mrna-moderna-pfizer-biontech/"
        )
        val links : Array<String> = p.filter { it.link != null }.mapNotNull{ it.link }.toTypedArray()

        assertThat(p.first().caption).doesNotContain(TITLE)
        assertThat(p).hasSize(7)
        assertThat(links).hasSize(3)
        assertThat(links).containsAll(*expectedLinks)
    }
}