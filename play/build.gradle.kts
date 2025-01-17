plugins {
    id("java")
    id("org.jetbrains.intellij") version "1.13.3"
}

group = "com.intellij"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

intellij {
    version.set("2023.1.1")
    type.set("IU") // Target IDE Platform

    plugins.set(listOf("org.intellij.groovy", "com.intellij.persistence"))
}

java.sourceSets["main"].java {
    srcDir("src/main/gen")
}

tasks {
    // Set the JVM compatibility versions
    withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }

    patchPluginXml {
        sinceBuild.set("231")
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
