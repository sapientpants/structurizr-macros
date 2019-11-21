package io.github.sapientpants.structurizr.macros

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class EnvTest {
    @BeforeEach
    fun setUp() {
        System.clearProperty(TEST_PROPERTY)
    }

    @Test
    fun `getProperty returns value from system properties when defined there`() {
        System.setProperty(TEST_PROPERTY, SYSTEM_PROPERTIES_TEST_VALUE)
        Assertions.assertEquals(SYSTEM_PROPERTIES_TEST_VALUE, Env.getProperty(TEST_PROPERTY))
    }

    @Test
    fun `getProperty returns value from dotenv when not defined in system properties`() {
        Assertions.assertEquals(DOTENV_TEST_VALUE, Env.getProperty(TEST_PROPERTY))
    }

    @Test
    fun `getProperty returns null when the key is unknown`() {
        Assertions.assertNull(Env.getProperty("KEY_1393019301"))
    }
}
