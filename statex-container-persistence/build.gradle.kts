import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
    id("maven-publish")
    id("org.jetbrains.dokka")
    id("statex.multiplatform")
    id("statex.publish")
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":statex-container"))

                implementation(compose.runtime)
                implementation(compose.runtimeSaveable)

                implementation(KotlinX.coroutines.core)

                implementation(KotlinX.serialization.core)

                implementation(RussHWolf.multiplatformSettings.settings)
                implementation(RussHWolf.multiplatformSettings.serialization)
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(RussHWolf.multiplatformSettings.noArg)
            }
        }

        val wasmJsMain by getting {
            dependencies {
                implementation(RussHWolf.multiplatformSettings.noArg)
            }
        }

        val jsMain by getting {
            dependencies {
                implementation(RussHWolf.multiplatformSettings.noArg)
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation(RussHWolf.multiplatformSettings.noArg)
            }
        }

        val appleMain by getting {
            dependencies {
                implementation(RussHWolf.multiplatformSettings.noArg)
            }
        }

        val mingwMain by getting {
            dependencies {
                implementation(RussHWolf.multiplatformSettings.noArg)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

android {
    namespace = "com.kodetools.statex.persisted"
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
        }
    }
}

tasks.withType<Jar> { duplicatesStrategy = DuplicatesStrategy.INHERIT }
