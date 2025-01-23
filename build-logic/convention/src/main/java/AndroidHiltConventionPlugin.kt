import com.winphyoethu.pocketo.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidHiltConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.google.devtools.ksp")
            dependencies {
                add("ksp", libs.findLibrary("hilt-compiler").get())
            }

            pluginManager.withPlugin("org.jetbrains.kotlin.jvm") {
                dependencies.add("implementation", libs.findLibrary("hilt-core").get())
            }

            pluginManager.withPlugin("com.android.base") {
                // dagger.hilt.android.plugin is alias for com.google.dagger.hilt.android
                // can use either one
                pluginManager.apply("dagger.hilt.android.plugin")
                dependencies.add("implementation", libs.findLibrary("hilt-android").get())
            }
        }
    }

}