plugins {
    id("java")
    id("org.jetbrains.intellij") version "1.13.3"
}

group = "com.intellij"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

java.sourceSets["main"].java {
    srcDir("src/main/gen")
}

// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
    version.set("LATEST-EAP-SNAPSHOT")
    type.set("IU")

    plugins.set(listOf(
        "com.intellij.java",
        "com.intellij.javaee",
        "com.intellij.javaee.app.servers.integration"
    ))
}

tasks {
    // Set the JVM compatibility versions
    withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }

    patchPluginXml {
        sinceBuild.set("232")
        untilBuild.set("232.*")
    }

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }
}
