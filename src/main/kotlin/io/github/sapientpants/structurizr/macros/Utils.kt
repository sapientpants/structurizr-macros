package io.github.sapientpants.structurizr.macros

import com.structurizr.model.Element
import io.github.cdimascio.dotenv.Dotenv

/**
 * Utility functions used throughout the macros.
 */
object Utils {
    /**
     * Sanitizes the input string for use as a filename.
     *
     * @params s the string to sanitize
     * @params ext the extension to add
     * @returns the sanitized filename with extension
     */
    fun filenamize(s: String, ext: String = ""): String {
        return if (s.isBlank()) {
            ""
        } else {
            val extText = if (ext.isBlank()) {
                ""
            } else {
                ".$ext"
            }
            s.replace(Regex("[-\\s:/\\\\]"), "_")
                .replace(Regex("_+"), "_") + extText
        }
    }

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

    /**
     * Concatenates a series of tags together as the suffix of a diagram title.
     *
     * @params tags the tags to be concatenated
     * @return the concatenated string of tags
     */
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
