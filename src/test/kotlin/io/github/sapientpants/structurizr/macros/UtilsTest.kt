package io.github.sapientpants.structurizr.macros

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

const val DOTENV_TEST_VALUE = "DOTENV_TEST_VALUE"
const val SYSTEM_PROPERTIES_TEST_VALUE = "SYSTEM_PROPERTIES_TEST_VALUE"
const val TEST_PROPERTY = "TEST_KEY"

class UtilsTest {
    @BeforeEach
    fun setUp() {
        System.clearProperty(TEST_PROPERTY)
    }

    @Test
    fun `keyifying "My spec14l kEy" should equal "my_spec14l_key"`() {
        assertEquals("my-spec14l-key", Utils.keyify("My spec14l kEy"))
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