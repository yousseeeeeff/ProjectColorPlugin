# Project Colorizer - API Documentation

## Table of Contents
1. [Overview](#overview)
2. [Package Structure](#package-structure)
3. [Core Components](#core-components)
4. [Extension Points](#extension-points)
5. [Utility Classes](#utility-classes)

## Overview

This document provides detailed API documentation for the Project Colorizer plugin. It covers the main classes, their methods, and how they interact with each other and the IntelliJ Platform API.

## Package Structure

The plugin is organized into the following packages:

- `com.example.settings`: Contains classes related to storing and managing plugin settings
- `com.example.actions`: Contains action classes that provide user interface entry points
- `com.example.listeners`: Contains listeners and utility classes for applying colors

## Core Components

### ProjectColorSettings

**Package**: `com.example.settings`

**Description**: Manages the persistence of project color settings.

**Class Definition**:
```kotlin
@State(
    name = "ProjectColorSettings",
    storages = [Storage(StoragePathMacros.WORKSPACE_FILE)]
)
class ProjectColorSettings : PersistentStateComponent<ProjectColorSettings.State>
```

**Inner Classes**:
- `State`: Data class that holds the color hex value

**Methods**:
- `getState(): State`: Returns the current state object
- `loadState(state: State)`: Loads the state from the persisted data
- `getInstance(project: Project): ProjectColorSettings`: Static method to get the service instance for a project

**Usage Example**:
```kotlin
// Get the settings instance for the current project
val settings = ProjectColorSettings.getInstance(project)

// Get the current color
val colorHex = settings.state.colorHex

// Set a new color
settings.state.colorHex = "#FF0000"
```

### ProjectColorConfigurable

**Package**: `com.example.settings`

**Description**: Provides a settings page in the IDE's Settings dialog.

**Class Definition**:
```kotlin
class ProjectColorConfigurable(private val project: Project) : Configurable
```

**Methods**:
- `getDisplayName(): String`: Returns the display name for the settings page
- `createComponent(): JComponent`: Creates the UI component for the settings page
- `isModified(): Boolean`: Checks if the settings have been modified
- `apply()`: Applies the changes to the settings
- `reset()`: Resets the UI to match the current settings
- `disposeUIResources()`: Cleans up resources when the settings page is closed

**Private Methods**:
- `applyColor()`: Applies the selected color to the project window

**Usage Example**:
The class is instantiated by the IntelliJ Platform when the user navigates to Settings > Appearance > Project Color.

### SetProjectColorAction

**Package**: `com.example.actions`

**Description**: Provides a menu action in the Tools menu for quick access to the color picker.

**Class Definition**:
```kotlin
class SetProjectColorAction : AnAction()
```

**Methods**:
- `actionPerformed(e: AnActionEvent)`: Called when the action is performed

**Usage Example**:
The action is registered in `plugin.xml` and appears in the Tools menu.

### ProjectColorApplier

**Package**: `com.example.listeners`

**Description**: Utility class for applying colors to project windows.

**Class Definition**:
```kotlin
class ProjectColorApplier
```

**Static Methods**:
- `applyColorToProject(project: Project)`: Applies the saved color to the project window frame
- `applyColorToTitleBarComponents(component: Component?, color: Color)`: Recursively applies color to components that might be part of the title bar

**Usage Example**:
```kotlin
// Apply the current color to the project window
ProjectColorApplier.applyColorToProject(project)
```

### ProjectColorStartupActivity

**Package**: `com.example.listeners`

**Description**: Ensures the project color is applied when a project is opened.

**Class Definition**:
```kotlin
class ProjectColorStartupActivity : ProjectActivity
```

**Methods**:
- `execute(project: Project)`: Called when a project is opened

**Usage Example**:
The class is registered in `plugin.xml` as a `postStartupActivity` and is automatically called by the IntelliJ Platform when a project is opened.

## Extension Points

The plugin uses the following IntelliJ Platform extension points:

### projectService

**Description**: Registers a service that is scoped to a project.

**Usage in plugin.xml**:
```xml
<extensions defaultExtensionNs="com.intellij">
    <projectService serviceImplementation="com.example.settings.ProjectColorSettings"/>
</extensions>
```

### projectConfigurable

**Description**: Registers a configurable component that appears in the Settings dialog.

**Usage in plugin.xml**:
```xml
<extensions defaultExtensionNs="com.intellij">
    <projectConfigurable
            instance="com.example.settings.ProjectColorConfigurable"
            id="com.example.settings.ProjectColorConfigurable"
            displayName="Project Color"
            groupId="appearance"/>
</extensions>
```

### postStartupActivity

**Description**: Registers an activity that is executed after a project is opened.

**Usage in plugin.xml**:
```xml
<extensions defaultExtensionNs="com.intellij">
    <postStartupActivity implementation="com.example.listeners.ProjectColorStartupActivity"/>
</extensions>
```

### action

**Description**: Registers an action that appears in the IDE's menus.

**Usage in plugin.xml**:
```xml
<actions>
    <action id="SetProjectColor"
            class="com.example.actions.SetProjectColorAction"
            text="Set a custom color for this project">
        <add-to-group group-id="ToolsMenu" anchor="last"/>
    </action>
</actions>
```

## Utility Classes

### ColorPanel (IntelliJ Platform API)

**Package**: `com.intellij.ui`

**Description**: A UI component that provides a color picker.

**Usage Example**:
```kotlin
val colorPanel = ColorPanel()
colorPanel.selectedColor = Color.RED
val selectedColor = colorPanel.selectedColor
```

### DialogWrapper (IntelliJ Platform API)

**Package**: `com.intellij.openapi.ui`

**Description**: A base class for creating dialogs.

**Usage Example**:
```kotlin
val dialog = object : DialogWrapper(project) {
    init {
        title = "My Dialog"
        init()
    }

    override fun createCenterPanel(): JComponent {
        // Create and return the dialog content
    }
}

if (dialog.showAndGet()) {
    // Dialog was confirmed
}
```

### UI DSL (IntelliJ Platform API)

**Package**: `com.intellij.ui.dsl.builder`

**Description**: A DSL for creating UI components.

**Usage Example**:
```kotlin
panel {
    row {
        label("Select a color:")
    }
    row {
        cell(colorPanel)
            .comment("Choose a color to apply")
    }
}
```