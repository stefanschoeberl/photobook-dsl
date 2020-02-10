package dev.ssch.photobookdsl.renderer.page

import dev.ssch.photobookdsl.dsl.Page
import dev.ssch.photobookdsl.dsl.PageType
import dev.ssch.photobookdsl.renderer.DocumentSettings
import dev.ssch.photobookdsl.renderer.PageSide
import org.apache.pdfbox.pdmodel.PDDocument

interface PageRenderer {
    fun renderPage(document: PDDocument, pageSide: PageSide, page: Page, documentSettings: DocumentSettings, drawBorders: Boolean)
}

fun getRenderer(pageType: PageType): PageRenderer {
    return when (pageType) {
        PageType.ONE_ASPECT_FIT -> OneAspectFitRenderer()
        PageType.ONE_ASPECT_FILL -> OneAspectFillRenderer()
        PageType.TWO_SIDE_BY_SIDE_FILL -> TwoSideBySideFill()
        PageType.TWO_SIDE_BY_SIDE_FIT -> TwoSideBySideFit()
        PageType.THREE_BIG_LEFT_FILL -> ThreeBigLeftFillRenderer()
        PageType.THREE_BIG_RIGHT_FILL -> ThreeBigRightFillRenderer()
        PageType.FOUR_ASPECT_FILL -> FourAspectFillRenderer()
        PageType.FOUR_ASPECT_FIT -> FourAspectFitRenderer()
        PageType.EMPTY -> EmptyRenderer()
    }
}