package com.moutaouakkil.actions

import com.moutaouakkil.listeners.ProjectColorApplier
import com.moutaouakkil.settings.ProjectColorSettings
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.openapi.ui.Messages
import com.intellij.ui.ColorPanel
import com.intellij.ui.components.JBLabel
import com.intellij.ui.dsl.builder.panel
import java.awt.Color
import javax.swing.JComponent

class SetProjectColorAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return

        // Show color picker dialog instead of text input
        val dialog = object : DialogWrapper(project) {
            private val colorPanel = ColorPanel()

            init {
                title = "Set Project Color"

                // Initialize with current color or default
                val currentColorHex = ProjectColorSettings.getInstance(project).state.colorHex
                if (currentColorHex != null) {
                    try {
                        colorPanel.selectedColor = Color.decode(currentColorHex)
                    } catch (e: NumberFormatException) {
                        colorPanel.selectedColor = Color.decode("#FFDD00") // Default color
                    }
                } else {
                    colorPanel.selectedColor = Color.decode("#FFDD00") // Default color
                }

                init()
            }

            override fun createCenterPanel(): JComponent {
                return panel {
                    row {
                        label("Select a color for this project:")
                    }
                    row {
                        cell(colorPanel)
                            .comment("Choose a color to apply to the project window frame")
                    }
                }
            }

            fun getSelectedColor(): Color? = colorPanel.selectedColor
        }

        if (dialog.showAndGet()) {
            val selectedColor = dialog.getSelectedColor() ?: return

            // Convert color to hex format
            val colorHex = String.format("#%02x%02x%02x", selectedColor.red, selectedColor.green, selectedColor.blue)

            // Save the color
            ProjectColorSettings.getInstance(project).state.colorHex = colorHex

            // Apply the color immediately
            ProjectColorApplier.applyColorToProject(project)

            Messages.showInfoMessage("Color applied successfully!", "Success")
        }
    }
}