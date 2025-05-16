plugins {
    kotlin("jvm") version "1.9.0"
    id("org.jetbrains.intellij") version "1.17.3"
}

group = "com.moutaouakkil"
version = "1.0.0"

repositories {
    mavenCentral()
}

intellij {
    // Utilise la dernière version stable d’IntelliJ IDEA
    version.set("2024.2")  // Updated to latest version
    type.set("IC") // IC = IntelliJ Community Edition
}

tasks {
    patchPluginXml {
        version.set("1.0.0")
        sinceBuild.set("242") // pour IntelliJ 2024.2
        untilBuild.set("243.*")

        // Add change notes for the plugin
        changeNotes.set("""
            <ul>
                <li>1.0.0 - Initial release</li>
                <li>Support for IntelliJ IDEA 2024.2</li>
                <li>Visual color picker for easy project color selection</li>
                <li>Settings page for configuring project colors</li>
            </ul>
        """.trimIndent())
    }

    runIde {
        // Pas besoin de ideDirectory
    }

    // Configuration for publishing the plugin to the JetBrains Plugin Repository
    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN") ?: "")
        channels.set(listOf("stable"))

        // Optional: Specify the plugin ID if different from the project name
        // pluginId.set("com.moutaouakkil.projectcolor")
    }

    // Verify plugin before publishing
    verifyPlugin {
        // Enable verification before publishing
    }

    // Build searchable options for the plugin
    buildSearchableOptions {
        enabled = true
    }
}

dependencies {
    implementation(kotlin("stdlib"))
}
