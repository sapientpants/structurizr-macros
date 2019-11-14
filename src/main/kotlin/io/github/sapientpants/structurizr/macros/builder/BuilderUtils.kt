package io.github.sapientpants.structurizr.macros.builder

import com.structurizr.model.Model
import com.structurizr.model.SoftwareSystem
import io.github.sapientpants.structurizr.macros.Tags
import io.github.sapientpants.structurizr.macros.Utils

object BuilderUtils {
    fun systemOfInterest(model: Model): SoftwareSystem {
        val softwareSystem =
            Utils.filter(
                model.softwareSystems,
                setOf(Tags.SYSTEM_OF_INTEREST)
            ).firstOrNull()
                ?: model.softwareSystems.firstOrNull()
                ?: throw IllegalStateException("No system of interest found")

        // Ensure the software system we take as the system of interest is
        // tagged as such
        softwareSystem.addTags(Tags.SYSTEM_OF_INTEREST)

        return softwareSystem
    }
}
