package io.github.sapientpants.structurizr.macros.builder

import com.structurizr.Workspace
import com.structurizr.model.Model
import com.structurizr.view.ViewSet

typealias Builder = (workspace: Workspace, model: Model, views: ViewSet) -> Unit