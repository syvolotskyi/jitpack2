import org.gradle.api.Action
import org.gradle.api.Project

class GradleConfigPluginExtension {

    private Project project

    private PluginConfig config = new PluginConfig()

    GradleConfigPluginExtension(Project project) {
        this.project = project
    }

    void config(Action<PluginConfig> configAction) {
        // apply the provided configuration to config
        configAction.execute(config)

        // manipulate gradle build based on the provided configurations
        manipulate()
    }

    private void manipulate() {
        populateBuildTypes(config.buildTypes)
        config.flavors.each { populateFlavors(it) }
    }

    void populateBuildTypes(Set<String> types) {
        project.android {
            buildTypes {
                types.each { type ->
                    // create (if exists) the provided build type
                    def createdType = maybeCreate(type)

                    // specify fallback to _production in case some build type (e.x. _debug) does not exist in child modules
                    if (createdType.name != BuildTypes.PRODUCTION)
                        createdType.matchingFallbacks = [BuildTypes.PRODUCTION]
                }
            }
        }
    }

    void populateFlavors(FlavorConfig flavorConfig) {
        project.android {
            // add provided flavor to the dimension list
            flavorDimensionList.add(flavorConfig.dimension)
            productFlavors {
                flavorConfig.getNames().each { name ->
                    // create each provided flavor per dimension
                    create(name) {
                        dimension flavorConfig.dimension
                    }
                }
            }
        }
    }

}
