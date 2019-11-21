package io.github.sapientpants.structurizr.macros.views

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ViewsTest {
    @Test
    fun `tagLine returns an empty string when no tags are provided`() {
        val expected = ""
        val actual = Views.tagLine(emptySet())
        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun `tagLine returns the expected string when tags are provided`() {
        val tags = setOf<String>(
            "tag1", "tag2"
        )
        val expected = " and tags (tag1, tag2)"
        val actual = Views.tagLine(tags)
        Assertions.assertEquals(expected, actual)
    }
}
