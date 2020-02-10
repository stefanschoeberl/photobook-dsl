package dev.ssch.photobookdsl.dsl

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class Image (
    val path: String,
    val alignment: ImageAlignment
) {
    fun loadImage(): BufferedImage {
        return ImageIO.read(File(path))
    }
}