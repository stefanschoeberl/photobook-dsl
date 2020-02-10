package dev.ssch.photobookdsl.renderer

class DocumentSettings(
    val documentWidth: Int,
    val documentHeight: Int,
    val pageX: Int,
    val pageY: Int,
    val contentXLeft: Int,
    val contentXRight: Int,
    val contentY: Int
) {

    val pageWidth = documentWidth - pageX - pageX
    val pageHeight = documentHeight - pageY - pageY

    val defaultSpacing = contentY - pageY

    val contentWidth = documentWidth - contentXLeft - contentXRight
    val contentHeight = documentHeight - 2 * contentY
}