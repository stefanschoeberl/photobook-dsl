package dev.ssch.photobookdsl.renderer.page

import dev.ssch.photobookdsl.dsl.Page
import dev.ssch.photobookdsl.renderer.DocumentSettings
import dev.ssch.photobookdsl.renderer.PageSide
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.pdmodel.PDPageContentStream

class EmptyRenderer : BasePageRenderer {

    override fun renderContent(
        document: PDDocument,
        contentStream: PDPageContentStream,
        pageSide: PageSide,
        page: Page,
        contentX: Int,
        documentSettings: DocumentSettings
    ) { }
}