import org.gradle.api.Project
class Configuration(project: Project) {
    val kotlinVersion = "1.3.31"
    val minSdkVersion = 21
    val targetSdkVersion = 28
    val compileSdkVersion = 28
    val versionCode = project.getConfiguration("versionCode", 1)
    val versionName = project.getConfiguration("versionName", "1.0")
    val dependencyVersion = DependencyVersion()
}
class DependencyVersion {
}

val Project.appConfig
    get() = Configuration(this)

inline fun <reified T: Any> Project.getConfiguration(name: String, defaultValue: T): T {
    return (if (project.hasProperty(name)) {
        val property = project.property(name)
        if (property == null) {
            defaultValue
        } else {
            when (defaultValue) {
                is String -> property
                is Boolean -> property.toString().toBoolean()
                is Int -> property.toString().toInt()
                is Double -> property.toString().toDouble()
                else -> property
            }
        }
    } else {
        defaultValue
    }) as T
}