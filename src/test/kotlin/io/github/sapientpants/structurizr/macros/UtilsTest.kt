package io.github.sapientpants.structurizr.macros

import com.structurizr.model.Element
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
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

    @BeforeEach
    fun setUp() {
        System.clearProperty(TEST_PROPERTY)
    }

    @Test
    fun `filter returns all elements when no tags are provided`() {
        val element1 = TestElement()
        val element2 = TestElement()
        val elements = setOf(element1, element2)
        val actual = Utils.filter(elements, emptySet<String>())
        assertEquals(elements, actual)
    }

    @Test
    fun `filter returns an empty set when input elements are empty`() {
        val elements = emptySet<Element>()
        val actual = Utils.filter(elements, setOf<String>("Some Tag"))
        assertEquals(elements, actual)
    }

    @Test
    fun `filter returns expected elements`() {
        val tag = "Some Tag"
        val element1 = TestElement()
        element1.addTags(tag)
        val element2 = TestElement()
        val elements = setOf(element1, element2)
        val actual = Utils.filter(elements, setOf<String>(tag))
        assertEquals(setOf<Element>(element1), actual)
    }

    @Test
    fun `getProperty returns value from system properties when defined there`() {
        System.setProperty(TEST_PROPERTY, SYSTEM_PROPERTIES_TEST_VALUE)
        assertEquals(SYSTEM_PROPERTIES_TEST_VALUE, Utils.getProperty(TEST_PROPERTY))
    }

    @Test
    fun `getProperty returns value from dotenv when not defined in system properties`() {
        assertEquals(DOTENV_TEST_VALUE, Utils.getProperty(TEST_PROPERTY))
    }

    @Test
    fun `getProperty returns null when the key is unknown`() {
        assertNull(Utils.getProperty("KEY_1393019301"))
    }
}