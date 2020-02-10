package dev.ssch.photobookdsl.renderer.page

import dev.ssch.photobookdsl.dsl.ImageAlignment
import dev.ssch.photobookdsl.dsl.Page
import dev.ssch.photobookdsl.renderer.DocumentSettings
import dev.ssch.photobookdsl.renderer.PageSide
import dev.ssch.photobookdsl.renderer.Util
import dev.ssch.photobookdsl.renderer.mm
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.pdmodel.PDPage
import org.apache.pdfbox.pdmodel.PDPageContentStream
import org.apache.pdfbox.pdmodel.common.PDRectangle
import org.apache.pdfbox.pdmodel.graphics.image.JPEGFactory
import java.awt.Color
import java.awt.image.BufferedImage

enum class ImageRenderMode {
    ASPECT_FIT,
    ASPECT_FILL
}

interface BasePageRenderer : PageRenderer {

    override fun renderPage(
        document: PDDocument,
        pageSide: PageSide,
        page: Page,
        documentSettings: DocumentSettings,
        drawBorders: Boolean
    ) {
        val pdfPage = PDPage(PDRectangle(documentSettings.documentWidth.mm(), documentSettings.documentHeight.mm()))
        document.addPage(pdfPage)
        val contentX = when (pageSide) {
            PageSide.LEFT -> documentSettings.contentXLeft
            PageSide.RIGHT -> documentSettings.contentXRight
        }
        val contentStream = PDPageContentStream(document, pdfPage)
        renderContent(document, contentStream, pageSide, page, contentX, documentSettings)

        // draw borders
        if (drawBorders) {
            contentStream.setStrokingColor(Color.GRAY)
            contentStream.addRect(documentSettings.pageX.mm(), documentSettings.pageY.mm(), documentSettings.pageWidth.mm(), documentSettings.pageHeight.mm())
            contentStream.stroke()
            contentStream.addRect(contentX.mm(), documentSettings.contentY.mm(), documentSettings.contentWidth.mm(), documentSettings.contentHeight.mm())
            contentStream.stroke()
        }

        contentStream.close()
    }

    fun renderImage(document: PDDocument, contentStream: PDPageContentStream, image: BufferedImage, renderMode: ImageRenderMode, alignment: ImageAlignment, x: Double, y: Double, width: Double, height: Double) {

        when (renderMode) {
            ImageRenderMode.ASPECT_FIT -> {
                val aspectRatioContent = height / width
                val aspectRatioImage = image.height.toDouble() / image.width

                val pdfImage = JPEGFactory.createFromImage(document, image)

                if (aspectRatioImage > aspectRatioContent) {
                    val imageWidth = height / aspectRatioImage
                    contentStream.drawImage(pdfImage, (x + (width - imageWidth) / 2.0).mm(), y.mm(), imageWidth.mm(), height.mm())
                } else {
                    val imageHeight = width * aspectRatioImage
                    contentStream.drawImage(pdfImage, x.mm(), (y + (height - imageHeight) / 2.0).mm(), width.mm(), imageHeight.mm())
                }
            }
            ImageRenderMode.ASPECT_FILL -> {
                val pdfImage = JPEGFactory.createFromImage(document, Util.cropToAspectRatio(image, alignment, width, height))
                contentStream.drawImage(pdfImage, x.mm(), y.mm(), width.mm(), height.mm())
            }
        }
    }

    fun renderContent(document: PDDocument, contentStream: PDPageContentStream, pageSide: PageSide, page: Page, contentX: Int, documentSettings: DocumentSettings)
}