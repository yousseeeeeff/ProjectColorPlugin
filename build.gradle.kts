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
    // Utilise une version stable d'IntelliJ IDEA compatible avec la plage définie
    version.set("2023.3")  // Version intermédiaire pour assurer la compatibilité
    type.set("IC") // IC = IntelliJ Community Edition
}

tasks {
    patchPluginXml {
        version.set("1.0.0")
        sinceBuild.set("223") // pour IntelliJ 2022.3 et plus récent
        untilBuild.set("253.*") // jusqu'à IntelliJ 2025.3

        // Add change notes for the plugin
        changeNotes.set("""
            <ul>
                <li>1.0.0 - Initial release</li>
                <li>Support for IntelliJ IDEA 2022.3 through 2025.3</li>
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
