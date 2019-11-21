package io.github.sapientpants.structurizr.macros.views

object Views {
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
}
