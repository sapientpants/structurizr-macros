package io.github.sapientpants.structurizr.macros.builder

import com.structurizr.model.Model
import com.structurizr.view.ViewSet

typealias ModelAndViewsBuilder = (model: Model, views: ViewSet) -> Unit
