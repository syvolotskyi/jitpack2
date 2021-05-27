import org.gradle.api.Plugin
import org.gradle.api.Project

class GradleConfigPlugin implements Plugin<Project> {

    @Override
    void apply(Project target) {
        // apply the default values from commons.gradle files which is located in buildSrc module
        target.apply([from: "$target.rootDir/buildSrc/commons.gradle"])
        
        // create an extension to configure the plugin from build.gradle files
        target.extensions.create('configPlugin', GradleConfigPluginExtension, target)
    }

}

