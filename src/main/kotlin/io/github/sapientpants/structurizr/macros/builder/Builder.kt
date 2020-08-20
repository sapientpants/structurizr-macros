package io.github.sapientpants.structurizr.macros.builder

import com.structurizr.Workspace
import com.structurizr.model.CreateImpliedRelationshipsUnlessSameRelationshipExistsStrategy
import com.structurizr.model.Location
import com.structurizr.model.Model
import com.structurizr.model.SoftwareSystem
import io.github.sapientpants.structurizr.macros.Tags
import io.github.sapientpants.structurizr.macros.styles.Style

public abstract class Builder(
    protected val enterpriseName: String,
    protected val workspaceName: String,
    protected val workspaceDescription: String
) {
    private var addImplicitRelationships = true
    protected var style: Style? = null

    public fun buildAndRender(
        modelAndViewsBuilder: ModelAndViewsBuilder
    ) {
        val workspace = build(modelAndViewsBuilder)
        render(workspace)
    }

    internal abstract fun build(
        modelAndViewsBuilder: ModelAndViewsBuilder
    ): Workspace

    internal abstract fun render(workspace: Workspace)

    public fun addImplicitRelationships(addImplicitRelationships: Boolean): Builder {
        this.addImplicitRelationships = addImplicitRelationships
        return this
    }

    public fun style(style: Style): Builder {
        this.style = style
        return this
    }

    protected fun finalizeModelAndAddViewsToWorkspace(
        workspace: Workspace,
        style: Style
    ) {
        val model = workspace.model

        finalizeModel(model)
        addViewsToWorkspace(workspace, style)
    }

    private fun finalizeModel(model: Model) {
        addImplicitRelationshipsToModel(model, addImplicitRelationships)

        tagInternalPeopleAndSoftwareSystems(model)
        tagExternalPeopleAndSoftwareSystems(model)
    }

    private fun tagExternalPeopleAndSoftwareSystems(model: Model) {
        model.people
            .filter { it.location == Location.External }
            .forEach { it.addTags(Tags.EXTERNAL) }
        model.softwareSystems
            .filter { it.location == Location.External }
            .forEach { it.addTags(Tags.EXTERNAL) }
    }

    private fun tagInternalPeopleAndSoftwareSystems(model: Model) {
        model.people
            .filter { it.location == Location.Internal }
            .forEach { it.addTags(Tags.INTERNAL) }
        model.softwareSystems
            .filter { it.location == Location.Internal }
            .forEach { it.addTags(Tags.INTERNAL) }
    }

    private fun addImplicitRelationshipsToModel(model: Model, addImplicitRelationships: Boolean) {
        if (addImplicitRelationships) {
            model.setImpliedRelationshipsStrategy(CreateImpliedRelationshipsUnlessSameRelationshipExistsStrategy())
        }
    }

    private fun addViewsToWorkspace(workspace: Workspace, style: Style) {
        val views = workspace.views

        views.createDefaultViews()

        // Apply the style
        style.applyToViews(views)
    }

    protected fun systemsOfInterest(model: Model): Set<SoftwareSystem> {
        check(model.softwareSystems.isNotEmpty()) { "No software systems in model" }

        val taggedSystemsOfInterest = Tags.filter(
            model.softwareSystems,
            setOf(Tags.SYSTEM_OF_INTEREST)
        )

        if (taggedSystemsOfInterest.isNotEmpty()) {
            return taggedSystemsOfInterest
        }

        val softwareSystem = model.softwareSystems.first()

        // Ensure the software system we take as the system of interest is
        // tagged as such
        softwareSystem.addTags(Tags.SYSTEM_OF_INTEREST)

        return setOf(softwareSystem)
    }
}
