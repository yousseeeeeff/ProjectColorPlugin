package com.example.listeners

import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManagerListener
import com.intellij.openapi.wm.WindowManager
import com.example.settings.ProjectColorSettings
import java.awt.Color

class ProjectColorApplier : ProjectManagerListener {
    override fun projectOpened(project: Project) {
        val frame = WindowManager.getInstance().getFrame(project)
        val colorHex = ProjectColorSettings.getInstance(project).state.colorHex ?: "#FFDD00"
        frame?.background = Color.decode(colorHex)
    }
}
