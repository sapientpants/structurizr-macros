import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version Dependencies.kotlinVersion
    `maven-publish`
}

group = "com.sapientpants.structurizr.macros"
version = Version.FULL

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



publishing {
    publications {
        create<MavenPublication>("mavenJava") {
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
                        name.set("Marc Tremblau")
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
    }
}



tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}
