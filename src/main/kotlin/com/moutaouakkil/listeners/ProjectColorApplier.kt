package com.moutaouakkil.listeners

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.WindowManager
import com.intellij.openapi.wm.ex.WindowManagerEx
import com.moutaouakkil.settings.ProjectColorSettings
import java.awt.Color
import com.intellij.openapi.application.ApplicationManager
import java.awt.Component
import java.awt.Container
import javax.swing.SwingUtilities

/**
 * Utility class for applying colors to project windows.
 */
class ProjectColorApplier {
    companion object {
        /**
         * Applies the saved color to the project window frame.
         * This can be called at any time to update the color.
         */
        fun applyColorToProject(project: Project) {
            // Use WindowManagerEx for better compatibility with newer IntelliJ versions
            val windowManager = WindowManagerEx.getInstanceEx()
            val frame = windowManager.getFrame(project)
            val colorHex = ProjectColorSettings.getInstance(project).state.colorHex ?: "#FFDD00"

            try {
                val color = Color.decode(colorHex)

                // Apply on UI thread to avoid threading issues
                ApplicationManager.getApplication().invokeLater {
                    // Apply color to all possible components to ensure visibility in both classic and new UI
                    frame?.background = color
                    frame?.rootPane?.background = color
                    frame?.contentPane?.background = color

                    // Try to color all components that might be part of the title bar or header
                    try {
                        // Recursively apply color to components that might be part of the title bar
                        applyColorToTitleBarComponents(frame, color)
                    } catch (e: Exception) {
                        // Ignore if components not found
                    }

                    // Try to find and color the title bar (classic UI approach)
                    try {
                        val titleBar = frame?.rootPane?.getLayeredPane()?.getComponent(1)
                        titleBar?.background = color
                    } catch (e: Exception) {
                        // Ignore if component not found
                    }

                    // Try to find and color the title bar components (new UI approach)
                    try {
                        frame?.rootPane?.components?.forEach { component ->
                            if (component.javaClass.name.contains("Decorator") || 
                                component.javaClass.name.contains("Header") ||
                                component.javaClass.name.contains("Title")) {
                                component.background = color
                            }
                        }
                    } catch (e: Exception) {
                        // Ignore if components not found
                    }
                }
            } catch (e: NumberFormatException) {
                // Handle invalid color format gracefully
                ApplicationManager.getApplication().invokeLater {
                    frame?.background = Color.decode("#FFDD00") // Default to yellow if invalid
                }
            }
        }

        /**
         * Recursively applies color to components that might be part of the title bar.
         * This helps ensure the color is applied to the correct components in both classic and new UI.
         */
        private fun applyColorToTitleBarComponents(component: Component?, color: Color) {
            if (component == null) return

            // Apply color to components that might be part of the title bar
            val name = component.javaClass.name.lowercase()
            if (name.contains("title") || name.contains("header") || name.contains("decorator") || 
                name.contains("toolbar") || name.contains("actionbar")) {
                component.background = color
            }

            // Recursively apply color to child components
            try {
                // Check if the component is a Container before trying to access its components
                if (component is Container) {
                    SwingUtilities.invokeLater {
                        component.components.forEach { child ->
                            applyColorToTitleBarComponents(child, color)
                        }
                    }
                }
            } catch (e: Exception) {
                // Ignore if components not found or can't be accessed
            }
        }
    }
}