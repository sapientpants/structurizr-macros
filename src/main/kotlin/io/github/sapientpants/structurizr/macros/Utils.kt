package io.github.sapientpants.structurizr.macros

import io.github.cdimascio.dotenv.Dotenv

object Utils {
    fun keyify(s: String): String {
        return s.toLowerCase().replace(" ", "-")
    }

    fun getProperty(key: String): String? {
        val systemPropertyValue = System.getProperty(key)
        if (systemPropertyValue != null) {
            return systemPropertyValue
        }

        val dotenv = Dotenv.load()
        return dotenv[key]
    }
}