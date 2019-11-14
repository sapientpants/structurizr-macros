package io.github.sapientpants.structurizr.macros.views

import com.structurizr.model.Model
import com.structurizr.view.ViewSet
import io.github.sapientpants.structurizr.macros.Utils

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
     * @see Utils.filter
     */
    fun addToViews(model: Model, views: ViewSet, tags: Set<String> = emptySet()) {
        val softwareSystems = Utils.filter(model.softwareSystems, tags)
        if (softwareSystems.size <= 1) {
            return
        }

        val enterprise = model.enterprise
        val tagLine = Utils.tagLine(tags)
        val systemLandscapeView = views.createSystemLandscapeView(
            "SystemLandscape-${enterprise.name}",
            "System landscape diagram for ${enterprise.name}$tagLine"
        )

        softwareSystems.forEach(systemLandscapeView::add)

        val people = Utils.filter(model.people, tags)
        people.forEach(systemLandscapeView::add)
    }
}
