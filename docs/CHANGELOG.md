# Changelog

All notable changes to the Project Colorizer plugin will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

### Added
- Comprehensive documentation including user guide, developer guide, and API documentation

## [1.0.0] - 2025-05-16

### Added
- Initial release of the Project Colorizer plugin
- Visual color picker for selecting project colors
- Settings page under Appearance > Project Color
- Tools menu action for quick access to color picker
- Support for IntelliJ IDEA 2024.2
- Immediate color application without requiring project restart
- Color persistence across IDE restarts
- Default color (yellow) when no color is selected
- Support for both light and dark themes

### Technical
- Modern implementation using ProjectActivity instead of deprecated ProjectManagerListener
- Enhanced color application to multiple UI components for better visibility
- Project-specific color storage using PersistentStateComponent
- UI implementation using IntelliJ's UI DSL

## Future Plans

Features being considered for future releases:

### [1.1.0] - Planned
- Color presets for quick selection
- Color export/import functionality
- Real-time color preview in settings dialog
- Support for coloring specific parts of the UI (status bar, title bar, etc.)

### [1.2.0] - Planned
- Color templates for different project types
- Automatic color suggestions based on project name
- Color opacity/transparency settings
- Keyboard shortcuts for color actions

### [2.0.0] - Planned
- Complete UI redesign with more customization options
- Color themes with multiple colors per project
- Integration with other JetBrains IDEs
- Color synchronization across team members (optional)