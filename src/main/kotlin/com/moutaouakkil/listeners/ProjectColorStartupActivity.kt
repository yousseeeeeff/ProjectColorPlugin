package com.moutaouakkil.listeners

import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.ProjectActivity

/**
 * Startup activity that applies the project color when a project is opened.
 */
class ProjectColorStartupActivity : ProjectActivity {
    override suspend fun execute(project: Project) {
        ProjectColorApplier.applyColorToProject(project)
    }
}