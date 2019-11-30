package io.github.sapientpants.structurizr.macros.views

import com.structurizr.model.Location
import com.structurizr.model.Model
import com.structurizr.model.SoftwareSystem
import com.structurizr.view.ViewSet
import io.github.sapientpants.structurizr.macros.Tags

/**
 * Macros to add system landscape views.
 * @see com.structurizr.view.SystemLandscapeView
 */
object SystemLandscapeView {
    /**
     * Adds a system landscape view to the workspace. If there is only one software system
     * in the provided model, then a system landscape diagram will not be added.
     *
     * Only those SoftwareSystems and People in the model tagged with all of the supplied
     * tags will be included in the diagram.
     *
     * @params model the model of the system of interest
     * @params views the set of views to which the SystemLandscapeView will be added
     * @params tags the tags to use for filtering
     * @see Tags.filter
     */
    fun addToViews(model: Model, views: ViewSet, tags: Set<String> = emptySet()) {
        val softwareSystems = Tags.filter(model.softwareSystems, tags)
        if (skipSystemLandscapeDiagram(softwareSystems)) {
            return
        }

        val enterprise = model.enterprise
        val tagLine = Views.tagLine(tags)
        val systemLandscapeView = views.createSystemLandscapeView(
            "SystemLandscape-${enterprise.name}",
            "System landscape diagram for ${enterprise.name}$tagLine"
        )

        softwareSystems.forEach(systemLandscapeView::add)

        val people = Tags.filter(model.people, tags)
        people.forEach(systemLandscapeView::add)
    }

    private fun skipSystemLandscapeDiagram(softwareSystems: Set<SoftwareSystem>): Boolean {
        return softwareSystems.size <= 1 ||
            softwareSystems.none { it.location == Location.External }
    }
}
