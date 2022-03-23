package net.composing.compose.sample.home

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class HomePreviewData : PreviewParameterProvider<Map<String, () -> Unit>> {
    override val values: Sequence<Map<String, () -> Unit>>
        get() = sequenceOf(
            mapOf(
                "Post Parsing" to {},
                "Accordion sample" to {},
                "Dialog sample" to {},
                "Simple UI" to {},
                "Nested navigation" to {},
                "Drag and Drop" to {}
            )
        )
}