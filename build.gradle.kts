import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    jacoco
    kotlin("jvm") version Dependencies.kotlinVersion
    `maven-publish`
    signing
}

group = "io.github.sapientpants"
version = Version.FULL
extra["isReleaseVersion"] = !version.toString().endsWith("SNAPSHOT")

tasks.register<Jar>("sourcesJar") {
    from(sourceSets.main.get().allSource)
    archiveClassifier.set("sources")
}

tasks.register<Jar>("javadocJar") {
    from(tasks.javadoc)
    archiveClassifier.set("javadoc")
}

repositories {
    mavenCentral()
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

jacoco {
    toolVersion = "0.8.4"
}

tasks.jacocoTestReport {
    reports {
        xml.isEnabled = true
        xml.destination = file("$buildDir/reports/jacoco/report.xml")
    }
}

//tasks.jacocoTestCoverageVerification {
//    violationRules {
//        rule {
//            limit {
//                minimum = "0.5".toBigDecimal()
//            }
//        }
//
//        rule {
//            enabled = false
//            element = "CLASS"
//            includes = listOf("io.github.sapientpants.*")
//
//            limit {
//                counter = "LINE"
//                value = "TOTALCOUNT"
//                maximum = "0.3".toBigDecimal()
//            }
//        }
//    }
//}

tasks.test {
    useJUnitPlatform()

}
println(components.asMap)
if (extra.has("maven.publish.username") && extra.has("maven.publish.password")) {
    publishing {
        publications {
            create<MavenPublication>("mavenKotlin") {
                from(components["kotlin"])
                artifact(tasks["sourcesJar"])
                artifact(tasks["javadocJar"])
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
