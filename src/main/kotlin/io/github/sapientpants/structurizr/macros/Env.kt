package io.github.sapientpants.structurizr.macros

import io.github.cdimascio.dotenv.DotEnvException
import io.github.cdimascio.dotenv.Dotenv
import org.apache.log4j.LogManager

object Env {

    val LOGGER = LogManager.getLogger(Env.javaClass)

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

        return try {
            val dotenv = Dotenv.load()
            dotenv[key]
        } catch (e: DotEnvException) {
            LOGGER.warn(e.localizedMessage)
            null
        }
    }
}
