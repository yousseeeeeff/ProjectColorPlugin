# Project Colorizer - Developer Guide

## Table of Contents
1. [Architecture Overview](#architecture-overview)
2. [Key Components](#key-components)
3. [Data Flow](#data-flow)
4. [Building the Plugin](#building-the-plugin)
5. [Testing](#testing)
6. [Debugging](#debugging)
7. [Adding New Features](#adding-new-features)

## Architecture Overview

Project Colorizer follows a simple but effective architecture that integrates with IntelliJ IDEA's plugin system. The plugin consists of several key components:

### High-Level Architecture

```
┌─────────────────────────────────────┐
│            IntelliJ IDEA            │
├─────────────────────────────────────┤
│                                     │
│  ┌─────────────┐    ┌────────────┐  │
│  │   Actions   │    │  Settings  │  │
│  │             │◄───┤            │  │
│  │ (User Input)│    │ (Storage)  │  │
│  └──────┬──────┘    └─────┬──────┘  │
│         │                 │         │
│         ▼                 ▼         │
│  ┌─────────────────────────────┐    │
│  │      Color Application      │    │
│  │                             │    │
│  │ (Applies colors to UI)      │    │
│  └─────────────────────────────┘    │
│                                     │
└─────────────────────────────────────┘
```

The plugin uses IntelliJ's extension points and service architecture to:
1. Store user preferences per project
2. Provide UI for color selection
3. Apply colors to the project window
4. Ensure colors persist across IDE restarts

## Key Components

### 1. Settings Storage (`ProjectColorSettings.kt`)

This component is responsible for persisting the user's color choice for each project. It implements `PersistentStateComponent` to store data in the project's workspace file.

Key features:
- Uses IntelliJ's persistence framework
- Stores color as a hex string
- Project-specific storage

### 2. Color Application (`ProjectColorApplier.kt`)

This utility class handles the actual application of colors to the project window. It:
- Finds the correct frame for the project
- Applies the color to various components to ensure visibility
- Handles edge cases and different UI themes

### 3. Startup Activity (`ProjectColorStartupActivity.kt`)

Ensures the project color is applied when a project is opened. It implements `ProjectActivity` to hook into the project startup process.

### 4. Settings UI (`ProjectColorConfigurable.kt`)

Provides a settings page in the IDE's Settings dialog. It:
- Creates a color picker UI
- Handles user interactions
- Applies changes immediately
- Implements the `Configurable` interface

### 5. Action (`SetProjectColorAction.kt`)

Provides a menu action in the Tools menu for quick access to the color picker. It:
- Creates a dialog with a color picker
- Handles color selection
- Applies and saves the selected color
- Implements the `AnAction` interface

## Data Flow

1. **User Interaction**:
   - User selects a color via the Tools menu or Settings dialog

2. **Color Storage**:
   - Selected color is converted to hex format
   - Color is stored in `ProjectColorSettings`

3. **Color Application**:
   - `ProjectColorApplier` retrieves the color from settings
   - Color is applied to the project window frame
   - Multiple UI components are colored to ensure visibility

4. **Persistence**:
   - Color setting is automatically persisted by IntelliJ's framework
   - On project reopen, `ProjectColorStartupActivity` triggers color application

## Building the Plugin

### Prerequisites

- IntelliJ IDEA
- JDK 17 or higher
- Kotlin 1.9.0 or higher
- Gradle

### Build Process

1. **Clone the repository**:
   ```bash
   git clone https://github.com/youssefmoutaouakkil/project-colorizer.git
   cd project-colorizer
   ```

2. **Build the plugin**:
   ```bash
   ./gradlew buildPlugin
   ```
   This will create a plugin ZIP file in `build/distributions/`.

3. **Run in development mode**:
   ```bash
   ./gradlew runIde
   ```
   This will start a development instance of IntelliJ IDEA with the plugin installed.

### Gradle Tasks

- `buildPlugin`: Builds the plugin ZIP file
- `runIde`: Runs a development instance of IntelliJ IDEA
- `verifyPlugin`: Verifies the plugin against IntelliJ Plugin Verifier
- `publishPlugin`: Publishes the plugin to the JetBrains Plugin Repository

## Testing

Currently, the plugin doesn't have automated tests. If you're adding tests, consider:

1. **Unit Tests**:
   - Test individual components in isolation
   - Mock IntelliJ services and components

2. **Integration Tests**:
   - Test the interaction between components
   - Use IntelliJ's test framework

3. **UI Tests**:
   - Test the UI components
   - Use IntelliJ's UI test framework

## Debugging

To debug the plugin:

1. Run the plugin in development mode:
   ```bash
   ./gradlew runIde --debug-jvm
   ```

2. Connect your IDE to the running instance using Remote JVM Debug configuration.

3. Set breakpoints in your code to inspect the execution flow.

4. Use IntelliJ's internal logs (Help > Show Log in Explorer) to see plugin-related messages.

## Adding New Features

When adding new features to the plugin, consider the following:

1. **Extension Points**:
   - Check if IntelliJ provides extension points for your feature
   - Use existing extension points when possible

2. **Services**:
   - Use project-level or application-level services for shared functionality
   - Consider whether your service needs to be stateful

3. **UI Components**:
   - Use IntelliJ's UI components and follow their design guidelines
   - Consider using the UI DSL for creating dialogs and settings pages

4. **Compatibility**:
   - Ensure your feature works with different IntelliJ versions
   - Test with both light and dark themes

### Example: Adding a New Action

To add a new action to the plugin:

1. Create a new class that extends `AnAction`
2. Implement the `actionPerformed` method
3. Register the action in `plugin.xml` under the `<actions>` tag
4. Add it to an existing action group or create a new one

```kotlin
class MyNewAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        // Your action code here
    }
}
```

```xml
<actions>
    <action id="MyNewAction"
            class="com.example.actions.MyNewAction"
            text="My New Action">
        <add-to-group group-id="ToolsMenu" anchor="last"/>
    </action>
</actions>
```