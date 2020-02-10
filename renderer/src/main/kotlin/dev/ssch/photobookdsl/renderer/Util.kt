package dev.ssch.photobookdsl.renderer

import dev.ssch.photobookdsl.dsl.ImageAlignment
import java.awt.image.BufferedImage
import kotlin.math.floor

object Util {

    fun cropToAspectRatio(image: BufferedImage, imageAlignment: ImageAlignment, width: Double, height: Double): BufferedImage {
        val aspectRatioContent = height / width
        val aspectRatioImage = image.height.toDouble() / image.width

        return if (aspectRatioImage > aspectRatioContent) {
            val newHeight = floor(image.width * aspectRatioContent).toInt()
            val centered = image.getSubimage(0, (image.height - newHeight) / 2, image.width, newHeight)
            when (imageAlignment) {
                ImageAlignment.CENTER -> centered
                ImageAlignment.TOP -> image.getSubimage(0, 0, image.width, newHeight)
                ImageAlignment.RIGHT -> centered
                ImageAlignment.BOTTOM -> image.getSubimage(0, image.height - newHeight, image.width, newHeight)
                ImageAlignment.LEFT -> centered
            }
        } else {
            val newWidth = floor(image.height / aspectRatioContent).toInt()
            val centered = image.getSubimage((image.width - newWidth) / 2, 0, newWidth, image.height)
            when(imageAlignment) {
                ImageAlignment.CENTER -> centered
                ImageAlignment.TOP -> centered
                ImageAlignment.RIGHT -> image.getSubimage(image.width - newWidth, 0, newWidth, image.height)
                ImageAlignment.BOTTOM -> centered
                ImageAlignment.LEFT -> image.getSubimage(0, 0, newWidth, image.height)
            }
        }
    }
}