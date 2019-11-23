import java.net.URL
import org.jetbrains.dokka.DokkaConfiguration
import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    jacoco
    kotlin("jvm") version Dependencies.kotlinVersion
    `maven-publish`
    id("org.jetbrains.dokka") version Dependencies.dokkaVersion
    id("org.jlleitschuh.gradle.ktlint") version Dependencies.ktlintVersion
    id("org.jlleitschuh.gradle.ktlint-idea") version Dependencies.ktlintVersion
    signing
}

group = "io.github.sapientpants"
version = Version.FULL
extra["isReleaseVersion"] = !version.toString().endsWith("SNAPSHOT")

val dokka by tasks.getting(DokkaTask::class) {
    outputFormat = "javadoc"
    outputDirectory = "$buildDir/javadoc"

    jdkVersion = 8

    includes = listOf("src/main/kotlin/packages.md")

    externalDocumentationLink(delegateClosureOf<DokkaConfiguration.ExternalDocumentationLink.Builder> {
        url = URL("https://docs.spring.io/spring-framework/docs/4.2.9.RELEASE/javadoc-api/")
    })
}

val dokkaJar by tasks.creating(Jar::class) {
    group = JavaBasePlugin.DOCUMENTATION_GROUP
    description = "Assembles Kotlin docs with Dokka"
    classifier = "javadoc"
    from(dokka)
}
artifacts.add("archives", dokkaJar)

ktlint {
    verbose.set(true)
    outputToConsole.set(true)
}

val sourcesJar by tasks.creating(Jar::class) {
    group = JavaBasePlugin.DOCUMENTATION_GROUP
    description = "Assembles sources JAR"
    classifier = "sources"
    from(sourceSets.getByName("main").allSource)
}
artifacts.add("archives", sourcesJar)

val jar by tasks.getting(Jar::class) {
    manifest.attributes.apply {
        put("Implementation-Title", "Structurizr Macros")
        put("Implementation-Version", project.version)
        put("Built-By", System.getProperty("user.name"))
        put("Built-JDK", System.getProperty("java.version"))
        put("Built-Gradle", project.gradle.gradleVersion)
    }
}

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    api(Dependencies.dotEnv)
    api(Dependencies.plantUml)
    api(Dependencies.slf4jLog4j)
    api(Dependencies.structurizrAdrTools)
    api(Dependencies.structurizrAnnotations)
    api(Dependencies.structurizrClient)
    api(Dependencies.structurizrCore)
    api(Dependencies.structurizrPlantUml)
    api(Dependencies.structurizrSpring)

    testImplementation(Dependencies.junitApi)
    testImplementation(Dependencies.junitEngine)
}

tasks.test {
    useJUnitPlatform()
}

jacoco {
    toolVersion = "0.8.4"
}

tasks.jacocoTestReport {
    reports {
        xml.isEnabled = true
        xml.destination = file("$buildDir/reports/jacoco/report.xml")
    }
}

tasks.jacocoTestCoverageVerification {
    violationRules {
        rule {
            limit {
                minimum = "0.5".toBigDecimal()
            }
        }

        rule {
            element = "CLASS"
            includes = listOf("io.github.sapientpants.*")

            limit {
                counter = "LINE"
                value = "TOTALCOUNT"
                maximum = "100".toBigDecimal()
            }
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenKotlin") {
            from(components["kotlin"])
            artifact(tasks["dokkaJar"])
            artifact(tasks["sourcesJar"])
            pom {
                name.set("Structurizr Macros")
                description.set("A collection of macros for Structurizr")
                url.set("https://github.com/sapientpants/structurizr-macros")
                licenses {
                    license {
                        name.set("The MIT License")
                        url.set("http://www.opensource.org/licenses/MIT")
                    }
                }
                developers {
                    developer {
                        id.set("sapientpants")
                        name.set("Marc Tremblay")
                        email.set("marc.tremblay@gmail.com")
                    }
                }
                scm {
                    connection.set("scm:git:git@github.com:sapientpants/architecture-documentation-macros.git")
                    developerConnection.set("scm:git:git@github.com:sapientpants/architecture-documentation-macros.git")
                    url.set("https://github.com/sapientpants/structurizr-macros")
                }
            }
        }

        repositories {
            maven {
                val releasesRepoUrl = "https://oss.sonatype.org/service/local/staging/deploy/maven2/"
                val snapshotsRepoUrl = "https://oss.sonatype.org/content/repositories/snapshots/"
                url = uri(if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl)
                credentials {
                    username = project.findProperty("maven.publish.username") as String?
                    password = project.findProperty("maven.publish.password") as String?
                }
            }
        }
    }
}

if (project.hasProperty("signing.gnupg.keyName") &&
        project.hasProperty("signing.gnupg.passphrase")) {
    signing {
        useGpgCmd()
        setRequired({
            (project.properties["isReleaseVersion"] as Boolean) && gradle.taskGraph.hasTask("uploadArchives")
        })
        sign(publishing.publications["mavenKotlin"])
        sign(configurations.archives.get())
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}
