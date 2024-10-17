plugins {
    application
    id("org.openjfx.javafxplugin") version "0.1.0"
}

javafx {
    version = "22.0.1"
    modules = listOf("javafx.controls", "javafx.graphics", "javafx.fxml", "javafx.media", "javafx.swing")
}

repositories {
    mavenCentral()
}

dependencies {
    // Use JUnit test framework.
    testImplementation(libs.junit)

    // This dependency is used by the application.
    implementation(libs.guava)
    implementation("org.controlsfx:controlsfx:11.2.1")
    implementation("org.kordamp.ikonli:ikonli-javafx:12.3.1")
    implementation("org.kordamp.ikonli:ikonli-antdesignicons-pack:12.3.1")
    implementation("org.kordamp.ikonli:ikonli-bootstrapicons-pack:12.3.1")
    implementation ("org.kordamp.ikonli:ikonli-fileicons-pack:12.3.1")
    implementation("com.google.code.gson:gson:2.11.0")
    implementation("jakarta.xml.bind:jakarta.xml.bind-api:3.0.1")
    implementation("com.sun.xml.bind:jaxb-impl:3.0.2")
    implementation ("xalan:xalan:2.7.2")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

application {
    mainClass = "ies.pedro.App"
}
