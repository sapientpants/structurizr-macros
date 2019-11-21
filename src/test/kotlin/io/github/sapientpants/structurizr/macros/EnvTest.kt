package io.github.sapientpants.structurizr.macros

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

private const val DOTENV_TEST_VALUE = "DOTENV_TEST_VALUE"
private const val SYSTEM_PROPERTIES_TEST_VALUE = "SYSTEM_PROPERTIES_TEST_VALUE"
private const val TEST_PROPERTY = "TEST_KEY"

class EnvTest {
    @BeforeEach
    fun setUp() {
        System.clearProperty(TEST_PROPERTY)
    }

    @Test
    fun `getProperty returns value from system properties when defined there`() {
        System.setProperty(
            TEST_PROPERTY,
            SYSTEM_PROPERTIES_TEST_VALUE
        )
        assertEquals(
            SYSTEM_PROPERTIES_TEST_VALUE, Env.getProperty(
                TEST_PROPERTY
            ))
    }

    @Test
    fun `getProperty returns value from dotenv when not defined in system properties`() {
        assertEquals(
            DOTENV_TEST_VALUE, Env.getProperty(
                TEST_PROPERTY
            ))
    }

    @Test
    fun `getProperty returns null when the key is unknown`() {
        assertNull(Env.getProperty("KEY_1393019301"))
    }
}
