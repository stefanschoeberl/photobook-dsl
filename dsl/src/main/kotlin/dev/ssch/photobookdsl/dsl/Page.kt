package dev.ssch.photobookdsl.dsl

class Page (
    val type: PageType,
    val images: MutableList<Image> = mutableListOf()
) {
    fun image(path: String, alignment: ImageAlignment = ImageAlignment.CENTER) {
        images.add(Image(path, alignment))
    }
}