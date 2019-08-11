package io.github.sapientpants.structurizr.macros

import com.structurizr.model.Element
import io.github.cdimascio.dotenv.Dotenv

/**
 * Utility functions used throughout the macros.
 */
object Utils {
    /**
     * Filters elements with all of the supplied tags. When tags is empty, then all the
     * input elements are returned.
     *
     * @params elements the elements to filter
     * @params tags the tags to use for filtering
     * @return those elements tagged with all of the supplied tags
     */
    fun <T : Element> filter(elements: Set<T>, tags: Set<String>): Set<T> {
        return elements.filter { element ->
            tags.all { tag -> element.hasTag(tag) }
        }.toSet()
    }

    fun tagLine(tags: Set<String>): String {
        return if (tags.isNotEmpty()) {
            tags.joinToString(", ", " and tags (", ")")
        } else {
            ""
        }
    }

    /**
     * Gets the value for the specified configuration key. The first non-null value
     * will be returned from <pre>System.getenv</pre>, <pre>System.getProperty</pre>
     * or <pre>Dotenv</pre> (checked in that order)
     *
     * @param key the key of the configuration property
     * @return the value or null if not found
     */
    fun getProperty(key: String): String? {
        val envValue = System.getenv(key)
        if (envValue != null) {
            return envValue
        }

        val systemPropertyValue = System.getProperty(key)
        if (systemPropertyValue != null) {
            return systemPropertyValue
        }

        val dotenv = Dotenv.load()
        return dotenv[key]
    }
}