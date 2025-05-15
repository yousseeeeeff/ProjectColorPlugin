package com.example.actions

import com.example.settings.ProjectColorSettings
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ui.Messages

class SetProjectColorAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        val input = Messages.showInputDialog(
            project,
            "Enter a hex color code (e.g. #FF0000):",
            "Set Project Color",
            Messages.getQuestionIcon()
        ) ?: return

        if (input.matches(Regex("^#([A-Fa-f0-9]{6})$"))) {
            ProjectColorSettings.getInstance(project).state.colorHex = input
            Messages.showInfoMessage("Color saved! Please reopen the project to apply.", "Success")
        } else {
            Messages.showErrorDialog("Invalid color format. Please use #RRGGBB.", "Error")
        }
    }
}
