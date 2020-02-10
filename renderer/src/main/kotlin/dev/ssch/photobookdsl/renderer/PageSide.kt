package dev.ssch.photobookdsl.renderer

enum class PageSide {
    LEFT, RIGHT;
}

fun getPageSide(index: Int): PageSide {
    if (index % 2 == 0) {
        return PageSide.RIGHT
    } else {
        return PageSide.LEFT
    }
}