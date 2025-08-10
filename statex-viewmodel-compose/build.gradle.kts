import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("com.android.library")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
    id("maven-publish")
    id("org.jetbrains.dokka")
    id("statex.publish")
}

kotlin {
    applyDefaultHierarchyTemplate()

    js {
        browser {
            testTask {
                enabled = false
            }
        }

        binaries.executable()
    }

    @OptIn(org.jetbrains.kotlin.gradle.ExperimentalWasmDsl::class)
    wasmJs {
        browser {
            testTask {
                enabled = false
            }
        }

        binaries.executable()
    }

    iosArm64()
    iosX64()
    iosSimulatorArm64()

    androidTarget {
        publishAllLibraryVariants()
    }

    jvm()

    explicitApi()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":statex-viewmodel"))

                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.kotlinx.serialization.core)

                implementation(compose.runtime)

                api(libs.androidx.lifecycle.viewmodel.compose)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }
    }
}

android {
    namespace = "com.kodetools.statex.viewmodel.compose"
    compileSdk = LibraryConstants.Android.compileSdkVersion

    defaultConfig {
        minSdk = LibraryConstants.Android.minSdkVersion
        targetSdk = LibraryConstants.Android.targetSdkVersion
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    tasks.withType<KotlinCompile> {
        compilerOptions {
            jvmTarget = JvmTarget.JVM_11
            freeCompilerArgs.add("-Xexpect-actual-classes")
            freeCompilerArgs.add("-Xopt-in=kotlin.RequiresOptIn")
            freeCompilerArgs.add("-XXLanguage:+ExplicitBackingFields")
            freeCompilerArgs.add("-Xcontext-parameters")
        }
    }
}

tasks.withType<Jar> { duplicatesStrategy = DuplicatesStrategy.INHERIT }
