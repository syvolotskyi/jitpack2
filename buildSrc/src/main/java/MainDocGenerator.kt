package space.doc.gradle

import org.gradle.api.Project
import java.io.File


object MainDocGenerator {

    private const val MODULE_TEMPLATE = "documentation/moduleMdTemplate.md"
    private const val MAIN_TEMPLATE = "documentation/mainMdTemplate.md"
    private const val MD_FILE_NAME = "README.md"


    
    @JvmStatic
    fun generateCombinedMainDoc(project: Project) {
        println("Going to generate combined Readme")
        val template = File(project.projectDir.absolutePath + "/" + MAIN_TEMPLATE)
        val combinedReadmeBuilder = StringBuilder()
        combinedReadmeBuilder.append(template.reader().readText())
        project.subprojects.forEach {
            val subProjectReadme = File("${it.projectDir}/$MD_FILE_NAME")
            if (!subProjectReadme.exists()) {
                project.logger.warn("${it.name} Module $MD_FILE_NAME not created, creating template")
                subProjectReadme.createNewFile()
                subProjectReadme.setWritable(true)
                subProjectReadme.writeBytes(getModuleReadmeTemplate(it).toByteArray())
            }
            val moduleShortDescriptionStringBuilder = StringBuilder()
            for (line in subProjectReadme.readLines()) {
                if (line.startsWith("###")) {
                    break
                } else {
                    moduleShortDescriptionStringBuilder.append(it)
                }
            }
            combinedReadmeBuilder.append("\n").append(getModuleShortDescription(it)).append("\n")
        }
        val out = File(project.projectDir.absolutePath + "/" + MD_FILE_NAME)
        if (out.exists())
            out.delete()
        out.createNewFile()
        out.writeBytes(combinedReadmeBuilder.toString().toByteArray())
        println("generating Readme done")
    }


    private fun getModuleReadmeTemplate(project: Project): String {
        val template = File("${project.rootProject.projectDir}/$MODULE_TEMPLATE")
        return template.reader().readText().replace("______", project.name)
    }

    private fun getModuleShortDescription(project: Project): String {
        val readme = File("${project.projectDir}/$MD_FILE_NAME")
        val sb = StringBuilder()
        sb.append("### ${project.name}\n")
        sb.append("[link](${project.name})").append(" \n")
        for (line in readme.readLines()) {
            if (line.startsWith("####")) {
                break
            }
            if (line.startsWith("###")) {
                continue
            }
            sb.append(line).append(" \n")
        }
        return sb.toString()
    }

}