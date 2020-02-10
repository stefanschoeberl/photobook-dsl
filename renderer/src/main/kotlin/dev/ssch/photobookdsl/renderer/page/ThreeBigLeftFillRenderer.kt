package dev.ssch.photobookdsl.renderer.page

import dev.ssch.photobookdsl.dsl.Page
import dev.ssch.photobookdsl.renderer.DocumentSettings
import dev.ssch.photobookdsl.renderer.PageSide
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.pdmodel.PDPageContentStream

class ThreeBigLeftFillRenderer : BasePageRenderer {

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

        val imageBigLeft = page.images.getOrNull(0) ?: return
        renderImage(document, contentStream, imageBigLeft.loadImage(), ImageRenderMode.ASPECT_FILL, imageBigLeft.alignment,
            contentX.toDouble(), documentSettings.contentY.toDouble(), imageWidth, documentSettings.contentHeight.toDouble())

        val imageTopRight = page.images.getOrNull(1) ?: return
        renderImage(document, contentStream, imageTopRight.loadImage(), ImageRenderMode.ASPECT_FILL, imageTopRight.alignment,
            contentX + imageWidth + documentSettings.defaultSpacing, documentSettings.contentY + imageHeight + documentSettings.defaultSpacing, imageWidth, imageHeight)

        val imageBottomRight = page.images.getOrNull(2) ?: return
        renderImage(document, contentStream, imageBottomRight.loadImage(), ImageRenderMode.ASPECT_FILL, imageBottomRight.alignment,
            contentX + imageWidth + documentSettings.defaultSpacing, documentSettings.contentY.toDouble(), imageWidth, imageHeight)
    }
}