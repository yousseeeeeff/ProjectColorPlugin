plugins {
    kotlin("jvm") version "1.9.0"
    id("org.jetbrains.intellij") version "1.17.3"
}

group = "com.example"
version = "1.0.0"

repositories {
    mavenCentral()
}

intellij {
    // Utilise la dernière version stable d’IntelliJ IDEA
    version.set("2024.1")
    type.set("IC") // IC = IntelliJ Community Edition
}

tasks {
    patchPluginXml {
        version.set("1.0.0")
        sinceBuild.set("241") // pour IntelliJ 2024.1
        untilBuild.set("242.*")
    }

    runIde {
        // Pas besoin de ideDirectory
    }
}

dependencies {
    implementation(kotlin("stdlib"))
}
