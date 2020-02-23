import org.gradle.api.Plugin
import org.gradle.api.Project

open class AndroidLibraryCustomPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.configureDefaultPlugins(isApp = false)
        project.configureModuleConfigurations(isApp = false)
        project.configureDependencies()
    }
}