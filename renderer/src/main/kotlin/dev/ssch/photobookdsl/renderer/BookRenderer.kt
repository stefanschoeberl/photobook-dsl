package dev.ssch.photobookdsl.renderer

import dev.ssch.photobookdsl.dsl.Book
import dev.ssch.photobookdsl.renderer.page.getRenderer
import org.apache.pdfbox.pdmodel.PDDocument
import kotlin.collections.forEachIndexed
import kotlin.collections.lastIndex
import kotlin.collections.lastOrNull
import kotlin.collections.takeLast

fun Int.mm(): Float {
    return (this * 72 / 25.4).toFloat()
}

fun Long.mm(): Float {
    return (this * 72 / 25.4).toFloat()
}

fun Float.mm(): Float {
    return (this * 72 / 25.4).toFloat()
}

fun Double.mm(): Float {
    return (this * 72 / 25.4).toFloat()
}

fun Book.renderBook(path: String, documentSettings: DocumentSettings, drawBorders: Boolean = false) {
    val document = PDDocument()
    pages.forEachIndexed { index, page ->
        println("Rendering page ${index + 1} / ${pages.size}")
        getRenderer(page.type).renderPage(
            document,
            getPageSide(index),
            page,
            documentSettings,
            drawBorders
        )
    }
    document.save(path)
    document.close()
}

fun Book.renderLastPage(path: String, documentSettings: DocumentSettings, drawBorders: Boolean = false) {
    val document = PDDocument()
    val page = pages.lastOrNull() ?: return
    println("Rendering page ${pages.size} / ${pages.size}")
    getRenderer(page.type).renderPage(
        document,
        getPageSide(pages.lastIndex),
        page,
        documentSettings,
        drawBorders
    )
    document.save(path)
    document.close()
}

fun Book.renderLastNPages(n: Int, path: String, documentSettings: DocumentSettings, drawBorders: Boolean = false) {
    val document = PDDocument()
    pages.takeLast(n).forEachIndexed { index, page ->
        println("Rendering page ${pages.size - n + index + 1} / ${pages.size}")
        getRenderer(page.type).renderPage(
            document,
            getPageSide(pages.size - n + index),
            page,
            documentSettings,
            drawBorders
        )
    }
    document.save(path)
    document.close()
}