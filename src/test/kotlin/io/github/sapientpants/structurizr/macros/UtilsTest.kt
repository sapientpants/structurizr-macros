package io.github.sapientpants.structurizr.macros

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class UtilsTest {
    @Test
    fun `keyifying "My spec14l kEy" should equal "my_spec14l_key"`() {
        assertEquals("my-spec14l-key", Utils.keyify("My spec14l kEy"))
    }
}