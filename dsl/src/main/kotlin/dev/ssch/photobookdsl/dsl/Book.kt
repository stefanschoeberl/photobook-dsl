package dev.ssch.photobookdsl.dsl

fun book(init: Book.() -> Unit): Book {
    val book = Book()
    book.init()
    return book
}

class Book (
    val pages: MutableList<Page> = mutableListOf()
) {

    fun page(type: PageType, init: Page.() -> Unit) {
        val page = Page(type)
        page.init()
        pages.add(page)
    }

    fun emptyPage() {
        pages.add(Page(PageType.EMPTY))
    }
}