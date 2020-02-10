package dev.ssch.photobookdsl.example

import dev.ssch.photobookdsl.dsl.ImageAlignment
import dev.ssch.photobookdsl.dsl.PageType
import dev.ssch.photobookdsl.dsl.book
import dev.ssch.photobookdsl.renderer.DocumentSettings
import dev.ssch.photobookdsl.renderer.renderBook
import dev.ssch.photobookdsl.renderer.renderLastNPages
import dev.ssch.photobookdsl.renderer.renderLastPage

fun main() {
    val book = book {

        // simple layouts

        page(PageType.ONE_ASPECT_FILL) {
            image("photos/img01.jpg")
        }

        page(PageType.ONE_ASPECT_FIT) {
            image("photos/img01.jpg")
        }

        page(PageType.TWO_SIDE_BY_SIDE_FILL) {
            image("photos/img03.jpg")
            image("photos/img04.jpg")
        }

        page(PageType.TWO_SIDE_BY_SIDE_FIT) {
            image("photos/img03.jpg")
            image("photos/img04.jpg")
        }

        page(PageType.THREE_BIG_LEFT_FILL) {
            image("photos/img03.jpg")
            image("photos/img01.jpg")
            image("photos/img02.jpg")

        }

        page(PageType.THREE_BIG_RIGHT_FILL) {
            image("photos/img03.jpg")
            image("photos/img01.jpg")
            image("photos/img02.jpg")
        }

        page(PageType.FOUR_ASPECT_FILL) {
            image("photos/img01.jpg")
            image("photos/img02.jpg")
            image("photos/img03.jpg")
            image("photos/img04.jpg")
        }

        page(PageType.FOUR_ASPECT_FIT) {
            image("photos/img01.jpg")
            image("photos/img02.jpg")
            image("photos/img03.jpg")
            image("photos/img04.jpg")
        }

        emptyPage()

        // alignments (only in FILL pages)

        page(PageType.ONE_ASPECT_FILL) {
            image("photos/img04.jpg", ImageAlignment.TOP)
        }

        page(PageType.ONE_ASPECT_FILL) {
            image("photos/img04.jpg", ImageAlignment.BOTTOM)
        }

        page(PageType.TWO_SIDE_BY_SIDE_FILL) {
            image("photos/img02.jpg", ImageAlignment.LEFT)
            image("photos/img02.jpg", ImageAlignment.RIGHT)
        }

    }

    val settings = DocumentSettings(
        303,
        216,
        3,
        3,
        11,
        19,
        11
    )

    book.renderBook("example-pdfs/book.pdf", settings)
    book.renderLastNPages(3, "example-pdfs/pages.pdf", settings)
    book.renderLastPage("example-pdfs/page.pdf", settings)

    book.renderBook("example-pdfs/bookWithBorders.pdf", settings, true)
}