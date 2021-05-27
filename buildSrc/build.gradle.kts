import org.gradle.kotlin.dsl.`kotlin-dsl`

plugins {
    `kotlin-dsl`
}

// Required since Gradle 4.10+.
repositories {
    google()
    jcenter()
}

gradlePlugin {
    plugins {
        create("GradleConfigPlugin") {
            id = "GradleConfigPlugin"
            implementationClass = "GradleConfigPlugin"
        }
    }
}

