import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

private fun Project.configureAndroidLib() =
    this.extensions.getByType(LibraryExtension::class.java).run {
        compileSdkVersion(AndroidSdk.compile)
        buildToolsVersion = AndroidSdk.usedBuildToolsVersion
        flavorDimensions("default")

        defaultConfig {
            minSdkVersion(AndroidSdk.min)
            targetSdkVersion(AndroidSdk.target)
            versionCode = AndroidSdk.versionCode
            versionName = AndroidSdk.versionName
            testInstrumentationRunner = AndroidSdk.testRunnerClass
        }

        buildTypes {
            getByName("debug") {
                isTestCoverageEnabled = true
            }
            getByName("release") {
                isMinifyEnabled = false
                proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            }
        }

        productFlavors {
            this.create("prod")
            this.create("staging")
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }
    }

private fun Project.configureAndroidApp() =
    this.extensions.getByType(BaseAppModuleExtension::class.java).run {
        compileSdkVersion(AndroidSdk.compile)
        buildToolsVersion = AndroidSdk.usedBuildToolsVersion
        flavorDimensions("default")

        defaultConfig {
            applicationId = AndroidSdk.applicationId
            minSdkVersion(AndroidSdk.min)
            targetSdkVersion(AndroidSdk.target)
            versionCode = AndroidSdk.versionCode
            versionName = AndroidSdk.versionName
            testInstrumentationRunner = AndroidSdk.testRunnerClass
        }

        buildTypes {
            getByName("debug") {
                isTestCoverageEnabled = true
            }
            getByName("release") {
                isMinifyEnabled = false
                proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            }
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }
        productFlavors {
            this.create("prod")
            this.create("staging")
        }

    }


internal fun Project.configureDependencies() = dependencies {
    add("implementation", Libraries.kotlinStdLib)
    add("implementation", Libraries.appCompat)
    add("implementation", Libraries.constraintLayout)
    add("implementation", Libraries.ktxCore)
    add("testImplementation", TestLibraries.junit4)
    add("androidTestImplementation", TestLibraries.espresso)
    add("androidTestImplementation", TestLibraries.testRunner)
}

internal fun Project.configureDefaultPlugins(isApp: Boolean = true) {
    if (isApp)
        plugins.apply("com.android.application")
    else
        plugins.apply("com.android.library")

    plugins.apply("kotlin-android")
    plugins.apply("kotlin-android-extensions")
}

internal fun Project.configureModuleConfigurations(isApp: Boolean = true) {
   if(isApp)
       configureAndroidApp()
    else
       configureAndroidLib()
}