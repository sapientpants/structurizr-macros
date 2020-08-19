package io.github.sapientpants.structurizr.macros.builder

import com.structurizr.model.Model
import com.structurizr.view.ViewSet

public typealias ModelAndViewsBuilder = (model: Model, views: ViewSet) -> Unit
