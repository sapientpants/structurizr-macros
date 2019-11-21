package io.github.sapientpants.structurizr.macros

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
}
