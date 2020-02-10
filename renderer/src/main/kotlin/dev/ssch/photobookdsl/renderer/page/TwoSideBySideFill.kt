package dev.ssch.photobookdsl.renderer.page

import dev.ssch.photobookdsl.dsl.Page
import dev.ssch.photobookdsl.renderer.DocumentSettings
import dev.ssch.photobookdsl.renderer.PageSide
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.pdmodel.PDPageContentStream

class TwoSideBySideFill : BasePageRenderer {

    override fun renderContent(
        document: PDDocument,
        contentStream: PDPageContentStream,
        pageSide: PageSide,
        page: Page,
        contentX: Int,
        documentSettings: DocumentSettings
    ) {
        val imageWidth = (documentSettings.contentWidth  - documentSettings.defaultSpacing) / 2.0

        val imageLeft = page.images.getOrNull(0) ?: return
        renderImage(document, contentStream, imageLeft.loadImage(), ImageRenderMode.ASPECT_FILL, imageLeft.alignment, contentX.toDouble(), documentSettings.contentY.toDouble(), imageWidth, documentSettings.contentHeight.toDouble())

        val imageRight = page.images.getOrNull(1) ?: return
        renderImage(document, contentStream, imageRight.loadImage(), ImageRenderMode.ASPECT_FILL, imageRight.alignment, contentX + imageWidth + documentSettings.defaultSpacing, documentSettings.contentY.toDouble(), imageWidth, documentSettings.contentHeight.toDouble())
    }
}