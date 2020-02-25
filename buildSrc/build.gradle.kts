import org.gradle.kotlin.dsl.`kotlin-dsl`

plugins{
    `kotlin-dsl`
}

gradlePlugin {
    plugins {
        register("app-custom-plugin") {
            id = "app-custom-plugin"
            implementationClass = "AndroidAppCustomPlugin"
        }
        register("library-custom-plugin") {
            id = "library-custom-plugin"
            implementationClass = "AndroidLibraryCustomPlugin"
        }
    }
}

// Required since Gradle 4.10+.
repositories {
    mavenCentral()
    google()
    jcenter()
}

dependencies{
    /* Depend on the android gradle plugin, since we want to access it in our plugin */
    implementation("com.android.tools.build:gradle:3.5.3")
    /* Depend on the kotlin plugin, since we want to access it in our plugin */
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.61")
}