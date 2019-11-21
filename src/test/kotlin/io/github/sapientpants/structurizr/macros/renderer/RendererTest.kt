package io.github.sapientpants.structurizr.macros.renderer

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RendererTest {
    @Test
    fun `filenameize returns an empty string when provided with one`() {
        val expected = ""
        val actual = Renderer.filenamize("")
        assertEquals(expected, actual)
    }

    @Test
    fun `filenameize returns a sanitized filename`() {
        val expected = "My_filename.png"
        val actual = Renderer.filenamize("My: \t    filename", "png")
        assertEquals(expected, actual)
    }
}
