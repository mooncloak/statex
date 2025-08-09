plugins {
    // Kotlin plugins
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.serialization) apply false

    // Android plugins
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.android.application) apply false

    // Compose plugins
    alias(libs.plugins.jetbrains.compose) apply false
    alias(libs.plugins.kotlin.compose) apply false

    // Other plugins
    alias(libs.plugins.dokka)
    alias(libs.plugins.binary.compatibility.validator)

    // Custom plugins
    id("statex.variables")
}

tasks.named<org.jetbrains.dokka.gradle.DokkaMultiModuleTask>("dokkaGfmMultiModule").configure {
    outputDirectory.set(file("${projectDir.path}/docs"))
}

group = buildVariables.group
version = buildVariables.version
