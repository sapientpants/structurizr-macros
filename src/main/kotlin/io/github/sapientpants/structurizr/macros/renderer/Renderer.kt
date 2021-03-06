package io.github.sapientpants.structurizr.macros.renderer

internal object Renderer {
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
