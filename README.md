# Photobook DSL

Kotlin DSL for creating simple photobooks

[Example PDF](example-pdfs/book.pdf)

## Basic Usage

```kotlin
val book = book {

    page(PageType.ONE_ASPECT_FILL) {
        image("path/to/image.jpg")
    }

    page(PageType.TWO_SIDE_BY_SIDE_FIT) {
        image("path/to/image.jpg")
        image("path/to/image.jpg")
    }

    // more pages ...
}

val settings = DocumentSettings(
    303, // actual page width (mm)
    216, // actual page height (mm)
    3,   // trim on the left and right side of the page (mm)
    3,   // trim on the top and bottom side of the page (mm)
    11,  // X coordinate of the content on left sided pages (mm)
    19,  // X coordinate of the content on right sided pages (mm)
    11   // Y coordinate of the content (mm)
)

book.renderBook("book.pdf", settings)
book.renderLastNPages(3, "pages.pdf", settings)
book.renderLastPage("page.pdf", settings)
```

See the [example project](example) for a more extensive example.

## Run example project

```
$ ./gradlew example:run
```

## Demo images
[Pexels](https://www.pexels.com): [License](https://www.pexels.com/photo-license/)