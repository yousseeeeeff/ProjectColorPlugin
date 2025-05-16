package com.moutaouakkil.settings

import com.intellij.openapi.options.Configurable
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.ColorPanel
import com.intellij.ui.components.JBLabel
import com.intellij.ui.dsl.builder.panel
import com.intellij.ui.dsl.builder.Row
import com.intellij.ui.dsl.builder.bindSelected
import com.intellij.ui.dsl.builder.bindText
import com.intellij.openapi.wm.WindowManager
import com.intellij.openapi.wm.ex.WindowManagerEx
import java.awt.Color
import javax.swing.JComponent
import javax.swing.JPanel

class ProjectColorConfigurable(private val project: Project) : Configurable {
    private var colorPanel: ColorPanel? = null
    private var mainPanel: DialogPanel? = null
    private var settings: ProjectColorSettings = ProjectColorSettings.getInstance(project)

    override fun getDisplayName(): String = "Project Color"

    override fun createComponent(): JComponent {
        colorPanel = ColorPanel()

        // Initialize with current color or default
        val currentColorHex = settings.state.colorHex
        if (currentColorHex != null) {
            try {
                colorPanel?.selectedColor = Color.decode(currentColorHex)
            } catch (e: NumberFormatException) {
                colorPanel?.selectedColor = Color.decode("#FFDD00") // Default color
            }
        } else {
            colorPanel?.selectedColor = Color.decode("#FFDD00") // Default color
        }

        mainPanel = panel {
            row {
                label("Select a color for this project:")
            }
            row {
                cell(colorPanel!!)
                    .comment("Choose a color to apply to the project window frame")
            }
            row {
                button("Apply") {
                    applyColor()
                }
                button("Reset to Default") {
                    colorPanel?.selectedColor = Color.decode("#FFDD00")
                    applyColor()
                }
            }
        }

        return mainPanel!!
    }

    private fun applyColor() {
        val color = colorPanel?.selectedColor ?: return
        val colorHex = String.format("#%02x%02x%02x", color.red, color.green, color.blue)
        settings.state.colorHex = colorHex

        // Apply color immediately using the ProjectColorApplier
        com.moutaouakkil.listeners.ProjectColorApplier.applyColorToProject(project)
    }

    override fun isModified(): Boolean {
        val currentColorHex = settings.state.colorHex
        val selectedColor = colorPanel?.selectedColor

        if (currentColorHex == null && selectedColor == null) return false
        if (currentColorHex == null) return true
        if (selectedColor == null) return true

        val currentColor = try {
            Color.decode(currentColorHex)
        } catch (e: NumberFormatException) {
            return true
        }

        return currentColor.rgb != selectedColor.rgb
    }

    override fun apply() {
        val color = colorPanel?.selectedColor ?: return
        val colorHex = String.format("#%02x%02x%02x", color.red, color.green, color.blue)
        settings.state.colorHex = colorHex

        // Apply color immediately using the ProjectColorApplier
        com.moutaouakkil.listeners.ProjectColorApplier.applyColorToProject(project)
    }

    override fun reset() {
        val currentColorHex = settings.state.colorHex
        if (currentColorHex != null) {
            try {
                colorPanel?.selectedColor = Color.decode(currentColorHex)
            } catch (e: NumberFormatException) {
                colorPanel?.selectedColor = Color.decode("#FFDD00") // Default color
            }
        } else {
            colorPanel?.selectedColor = Color.decode("#FFDD00") // Default color
        }
    }

    override fun disposeUIResources() {
        colorPanel = null
        mainPanel = null
    }
}