package com.sapientpants.structurizr.macros

object Utils {
    fun keyify(s: String): String {
        return s.toLowerCase().replace(" ", "-")
    }
}