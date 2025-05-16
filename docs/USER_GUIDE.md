# Project Colorizer - User Guide

## Table of Contents
1. [Introduction](#introduction)
2. [Installation](#installation)
3. [Basic Usage](#basic-usage)
4. [Advanced Configuration](#advanced-configuration)
5. [Troubleshooting](#troubleshooting)
6. [FAQ](#faq)

## Introduction

Project Colorizer is an IntelliJ IDEA plugin that helps you visually distinguish between different projects by adding custom colors to project windows. This is particularly useful when working with multiple projects simultaneously.

### Key Features

- **Visual Distinction**: Easily identify which project you're working on at a glance
- **Custom Colors**: Choose any color that suits your preferences
- **Immediate Application**: Colors are applied instantly without requiring a restart
- **Persistent Settings**: Your color choices are saved per project

## Installation

### From JetBrains Marketplace

1. Open IntelliJ IDEA
2. Go to **Settings/Preferences** > **Plugins**
3. Select the **Marketplace** tab
4. Search for "Project Colorizer"
5. Click **Install**
6. Restart IntelliJ IDEA when prompted

### Manual Installation

If you prefer to install the plugin manually:

1. Download the latest release (.zip file) from the [GitHub releases page](https://github.com/youssefmoutaouakkil/project-colorizer/releases)
2. In IntelliJ IDEA, go to **Settings/Preferences** > **Plugins**
3. Click the gear icon and select **Install Plugin from Disk...**
4. Navigate to the downloaded .zip file and select it
5. Click **OK** and restart IntelliJ IDEA when prompted

## Basic Usage

### Setting a Project Color

There are two ways to set a color for your project:

#### Method 1: Using the Tools Menu

1. Open your project in IntelliJ IDEA
2. Go to **Tools** > **Set a custom color for this project**
3. A color picker dialog will appear
4. Select your desired color
5. Click **OK**
6. The color will be applied immediately to your project window

#### Method 2: Using the Settings Dialog

1. Open your project in IntelliJ IDEA
2. Go to **Settings/Preferences** > **Appearance** > **Project Color**
3. Use the color picker to select your desired color
4. Click **Apply** to see the changes immediately
5. Click **OK** to save the settings

### Changing a Project Color

To change the color of a project, simply follow either of the methods above and select a new color.

### Resetting to Default

If you want to remove the custom color:

1. Go to **Settings/Preferences** > **Appearance** > **Project Color**
2. Click the **Reset to Default** button
3. Click **OK** to save the settings

## Advanced Configuration

### Color Persistence

Project colors are stored in the project's workspace file, which means:

- The color setting is specific to each project
- The color setting is not shared with other team members (it's stored in your local workspace file)
- The color setting persists across IDE restarts

### IDE Theme Compatibility

Project Colorizer works with all IntelliJ IDEA themes, including dark and light themes. However, the visibility of the color may vary depending on the theme and the color you choose.

Tips for choosing colors:

- For dark themes, choose brighter or more saturated colors
- For light themes, choose darker or more saturated colors
- Avoid colors that are too similar to the default IDE colors

## Troubleshooting

### Color Not Visible

If you've set a color but can't see it:

1. Try choosing a more contrasting color
2. Restart IntelliJ IDEA
3. Check if you have other plugins that might be modifying the IDE's appearance

### Color Applied to Wrong Project

If the color is applied to a different project than intended:

1. Make sure you're setting the color in the correct project window
2. Close all project windows and reopen only the project you want to modify

### Plugin Not Working After IDE Update

If the plugin stops working after updating IntelliJ IDEA:

1. Check if the plugin is compatible with your version of IntelliJ IDEA
2. Update the plugin to the latest version
3. If the issue persists, reinstall the plugin

## FAQ

### Q: Can I use the same color for multiple projects?
A: Yes, you can set the same color for as many projects as you like.

### Q: Will other team members see my project colors?
A: No, project colors are stored in your local workspace file and are not shared with other team members.

### Q: Does this plugin work with other JetBrains IDEs?
A: The plugin is designed for IntelliJ IDEA, but it may work with other JetBrains IDEs that share the same platform.

### Q: Can I export/import my color settings?
A: Currently, there's no built-in functionality to export or import color settings.

### Q: Does the plugin affect performance?
A: No, the plugin has minimal impact on IDE performance as it only modifies the UI appearance.