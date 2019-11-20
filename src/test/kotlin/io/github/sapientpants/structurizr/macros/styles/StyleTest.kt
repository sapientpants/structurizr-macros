package io.github.sapientpants.structurizr.macros.styles

import com.structurizr.Workspace
import com.structurizr.view.ViewSet
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class StyleTest {
    class TestStyle : Style() {
        override fun applyToViews(views: ViewSet) {
            applyBaseStyles(views)
        }
    }

    @Test
    fun `applyBaseStyles() should apply base styles`() {
        val workspace = Workspace("WorkspaceName", "WorkspaceDescription")
        val views = workspace.views
        val style = TestStyle()

        style.applyBaseStyles(views)

        assertEquals(11, views.configuration.styles.elements.size)
        assertEquals(0, views.configuration.styles.relationships.size)
    }
}
