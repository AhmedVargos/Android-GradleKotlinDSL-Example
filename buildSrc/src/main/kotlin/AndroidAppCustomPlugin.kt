import org.gradle.api.Plugin
import org.gradle.api.Project

open class AndroidAppCustomPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.configureDefaultPlugins(isApp = true)
        project.configureModuleConfigurations(isApp = true)
        project.configureDependencies()
    }
}
