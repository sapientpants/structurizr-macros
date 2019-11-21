package io.github.sapientpants.structurizr.macros

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
