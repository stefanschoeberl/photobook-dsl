package dev.ssch.photobookdsl.renderer.page

import dev.ssch.photobookdsl.dsl.Page
import dev.ssch.photobookdsl.renderer.DocumentSettings
import dev.ssch.photobookdsl.renderer.PageSide
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.pdmodel.PDPageContentStream

class ThreeBigRightFillRenderer : BasePageRenderer {

    override fun renderContent(
        document: PDDocument,
        contentStream: PDPageContentStream,
        pageSide: PageSide,
        page: Page,
        contentX: Int,
        documentSettings: DocumentSettings
    ) {
        val imageWidth = (documentSettings.contentWidth - documentSettings.defaultSpacing) / 2.0
        val imageHeight = (documentSettings.contentHeight - documentSettings.defaultSpacing) / 2.0

        val imageBigRight = page.images.getOrNull(0) ?: return
        renderImage(document, contentStream, imageBigRight.loadImage(), ImageRenderMode.ASPECT_FILL, imageBigRight.alignment,
            contentX + imageWidth + documentSettings.defaultSpacing, documentSettings.contentY.toDouble(), imageWidth, documentSettings.contentHeight.toDouble())

        val imageTopLeft = page.images.getOrNull(1) ?: return
        renderImage(document, contentStream, imageTopLeft.loadImage(), ImageRenderMode.ASPECT_FILL, imageTopLeft.alignment,
            contentX.toDouble(), documentSettings.contentY + imageHeight + documentSettings.defaultSpacing, imageWidth, imageHeight)

        val imageBottomLeft = page.images.getOrNull(2) ?: return
        renderImage(document, contentStream, imageBottomLeft.loadImage(), ImageRenderMode.ASPECT_FILL, imageBottomLeft.alignment,
            contentX.toDouble(), documentSettings.contentY.toDouble(), imageWidth, imageHeight)

    }
}