# Project Colorizer

An IntelliJ IDEA plugin that adds custom colors to project windows, helping you visually distinguish between different projects.

## Features

- Set a custom color for each project
- Visual color picker for easy selection
- Color is applied immediately without requiring project restart
- Configure colors through Settings > Appearance > Project Color

## Installation

You can install this plugin directly from the JetBrains Plugin Repository:

1. In IntelliJ IDEA, go to Settings/Preferences > Plugins
2. Click on "Marketplace"
3. Search for "Project Colorizer"
4. Click "Install"

## Usage

There are two ways to set a color for your project:

1. **Via Tools Menu**: Go to Tools > Set a custom color for this project
2. **Via Settings**: Go to Settings > Appearance > Project Color

Both options will open a color picker where you can select your desired color.

For more detailed usage instructions, see the [User Guide](docs/USER_GUIDE.md).

## Documentation

Comprehensive documentation is available in the `docs` directory:

- [User Guide](docs/USER_GUIDE.md) - Detailed instructions for using the plugin
- [Developer Guide](docs/DEVELOPER_GUIDE.md) - Technical details about the plugin's architecture and implementation
- [API Documentation](docs/API_DOCUMENTATION.md) - Documentation of the plugin's classes and methods
- [Contributing Guide](docs/CONTRIBUTING.md) - Guidelines for contributing to the project
- [Changelog](docs/CHANGELOG.md) - History of changes in each version

## Development

### Prerequisites

- IntelliJ IDEA
- Java 17 or higher
- Kotlin 1.9.0 or higher

### Building the Plugin

To build the plugin, run:

```bash
./gradlew buildPlugin
```

The plugin will be built in `build/distributions/`.

### Running the Plugin in a Development Instance

To run the plugin in a development instance of IntelliJ IDEA, run:

```bash
./gradlew runIde
```

## Publishing the Plugin

To publish the plugin to the JetBrains Plugin Repository:

1. Create an account on the [JetBrains Plugin Repository](https://plugins.jetbrains.com/)
2. Generate a permanent token in your account settings
3. Set the token as an environment variable:
   ```bash
   export PUBLISH_TOKEN=your_token_here
   ```
   or on Windows:
   ```cmd
   set PUBLISH_TOKEN=your_token_here
   ```
4. Run the publish task:
   ```bash
   ./gradlew publishPlugin
   ```

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Author

Youssef Moutaouakkil - [youssef260399@gmail.com](mailto:youssef260399@gmail.com)
