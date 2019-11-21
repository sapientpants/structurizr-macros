package io.github.sapientpants.structurizr.macros

import com.structurizr.model.Element
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

const val DOTENV_TEST_VALUE = "DOTENV_TEST_VALUE"
const val SYSTEM_PROPERTIES_TEST_VALUE = "SYSTEM_PROPERTIES_TEST_VALUE"
const val TEST_PROPERTY = "TEST_KEY"

class UtilsTest {
    class TestElement : Element() {
        override fun getCanonicalName(): String {
            TODO("not implemented")
        }

        override fun getRequiredTags(): MutableSet<String> {
            TODO("not implemented")
        }

        override fun getParent(): Element {
            TODO("not implemented")
        }
    }

    @Test
    fun `filenameize returns an empty string when provided with one`() {
        val expected = ""
        val actual = Utils.filenamize("")
        assertEquals(expected, actual)
    }

    @Test
    fun `filenameize returns a sanitized filename`() {
        val expected = "My_filename.png"
        val actual = Utils.filenamize("My: \t    filename", "png")
        assertEquals(expected, actual)
    }

    @Test
    fun `filter returns all elements when no tags are provided`() {
        val element1 = TestElement()
        val element2 = TestElement()
        val elements = setOf(element1, element2)
        val actual = Tags.filter(elements, emptySet<String>())
        assertEquals(elements, actual)
    }

    @Test
    fun `filter returns an empty set when input elements are empty`() {
        val elements = emptySet<Element>()
        val actual = Tags.filter(elements, setOf<String>("Some Tag"))
        assertEquals(elements, actual)
    }

    @Test
    fun `filter returns expected elements`() {
        val tag = "Some Tag"
        val element1 = TestElement()
        element1.addTags(tag)
        val element2 = TestElement()
        val elements = setOf(element1, element2)
        val actual = Tags.filter(elements, setOf<String>(tag))
        assertEquals(setOf<Element>(element1), actual)
    }
}
