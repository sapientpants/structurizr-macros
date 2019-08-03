import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version Dependencies.kotlinVersion
    `maven-publish`
    signing
}

group = "io.github.sapientpants"
version = Version.FULL
extra["isReleaseVersion"] = !version.toString().endsWith("SNAPSHOT")

val sourcesJar = tasks.register<Jar>("sourcesJar") {
    from(sourceSets.main.get().allSource)
    archiveClassifier.set("sources")
}

var javadocJar = tasks.register<Jar>("javadocJar") {
    from(tasks.javadoc)
    archiveClassifier.set("javadoc")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(Dependencies.dotEnv)
    implementation(Dependencies.plantUml)
    implementation(Dependencies.slf4jLog4j)
    implementation(Dependencies.structurizrAdrTools)
    implementation(Dependencies.structurizrAnnotations)
    implementation(Dependencies.structurizrClient)
    implementation(Dependencies.structurizrCore)
    implementation(Dependencies.structurizrPlantUml)
    implementation(Dependencies.structurizrSpring)
}

if (extra.has("maven.publish.username") && extra.has("maven.publish.password")) {
    publishing {
        publications {
            create<MavenPublication>("mavenJava") {
                from(components["java"])
                artifact(sourcesJar.get())
                artifact(javadocJar.get())
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
                        username = extra["maven.publish.username"] as String
                        password = extra["maven.publish.password"] as String
                    }
                }
            }
        }
    }

    signing {
        useGpgCmd()
        setRequired({
            (project.extra["isReleaseVersion"] as Boolean) && gradle.taskGraph.hasTask("uploadArchives")
        })
        sign(configurations.archives.get())
    }
}


tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}
