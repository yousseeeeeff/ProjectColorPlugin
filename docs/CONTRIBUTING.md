# Contributing to Project Colorizer

Thank you for your interest in contributing to Project Colorizer! This document provides guidelines and instructions for contributing to the project.

## Table of Contents
1. [Code of Conduct](#code-of-conduct)
2. [Getting Started](#getting-started)
3. [Development Environment](#development-environment)
4. [Coding Standards](#coding-standards)
5. [Commit Guidelines](#commit-guidelines)
6. [Pull Request Process](#pull-request-process)
7. [Testing](#testing)
8. [Documentation](#documentation)
9. [Issue Reporting](#issue-reporting)

## Code of Conduct

By participating in this project, you agree to maintain a respectful and inclusive environment for everyone. Please be considerate of others and their perspectives.

## Getting Started

1. **Fork the repository** on GitHub
2. **Clone your fork** to your local machine
3. **Set up the development environment** as described below
4. **Create a new branch** for your feature or bug fix
5. **Make your changes** and commit them
6. **Push your changes** to your fork
7. **Submit a pull request** to the main repository

## Development Environment

### Prerequisites

- IntelliJ IDEA (Community or Ultimate edition)
- JDK 17 or higher
- Kotlin 1.9.0 or higher
- Git

### Setup

1. Clone your fork of the repository:
   ```bash
   git clone https://github.com/your-username/project-colorizer.git
   cd project-colorizer
   ```

2. Open the project in IntelliJ IDEA:
   - Launch IntelliJ IDEA
   - Select "Open" and navigate to the project directory
   - Wait for the project to be imported and indexed

3. Build the project:
   ```bash
   ./gradlew buildPlugin
   ```

4. Run the plugin in a development instance:
   ```bash
   ./gradlew runIde
   ```

## Coding Standards

### Kotlin Style Guide

We follow the official [Kotlin Coding Conventions](https://kotlinlang.org/docs/coding-conventions.html). Some key points:

- Use 4 spaces for indentation
- Maximum line length is 100 characters
- Use camelCase for variables and functions
- Use PascalCase for classes and interfaces
- Use UPPER_SNAKE_CASE for constants

### Plugin-Specific Guidelines

- Follow IntelliJ Platform SDK guidelines
- Use IntelliJ's UI components and follow their design patterns
- Avoid direct manipulation of Swing components when possible
- Use extension points and services as intended

### Code Quality

- Write clean, readable, and maintainable code
- Add comments for complex logic
- Keep methods small and focused on a single responsibility
- Avoid duplicated code

## Commit Guidelines

- Use clear and descriptive commit messages
- Start with a verb in the present tense (e.g., "Add feature" not "Added feature")
- Reference issue numbers when applicable (e.g., "Fix #42: Resolve color picker issue")
- Keep commits focused on a single change
- Squash multiple commits if they represent a single change

Example of a good commit message:
```
Add color preview in settings dialog

- Add real-time preview of selected color
- Improve layout of color picker
- Update documentation

Fixes #123
```

## Pull Request Process

1. **Create a new branch** for your feature or bug fix:
   ```bash
   git checkout -b feature/your-feature-name
   ```
   or
   ```bash
   git checkout -b fix/issue-description
   ```

2. **Make your changes** and commit them following the commit guidelines

3. **Push your branch** to your fork:
   ```bash
   git push origin feature/your-feature-name
   ```

4. **Submit a pull request** to the main repository:
   - Go to the original repository on GitHub
   - Click "New Pull Request"
   - Select your fork and branch
   - Fill in the PR template with details about your changes

5. **Address review comments** if any are provided

6. **Update your PR** if needed by pushing additional commits to your branch

## Testing

- Test your changes thoroughly before submitting a PR
- Add automated tests for new features when possible
- Ensure existing tests pass with your changes
- Test with different IntelliJ IDEA versions if your change might affect compatibility

## Documentation

- Update documentation to reflect your changes
- Document new features, configuration options, or behavior changes
- Update the README.md if necessary
- Add code comments for complex logic

## Issue Reporting

If you find a bug or have a feature request:

1. Check if the issue already exists in the [GitHub Issues](https://github.com/youssefmoutaouakkil/project-colorizer/issues)
2. If not, create a new issue with:
   - A clear title and description
   - Steps to reproduce (for bugs)
   - Expected and actual behavior (for bugs)
   - Screenshots if applicable
   - Version information (IntelliJ IDEA version, plugin version, OS)

## Thank You!

Your contributions help make Project Colorizer better for everyone. We appreciate your time and effort!