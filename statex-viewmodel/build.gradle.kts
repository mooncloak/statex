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
    id("statex.multiplatform")
    id("statex.publish")
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)

                implementation(KotlinX.coroutines.core)

                implementation(KotlinX.serialization.core)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        val androidMain by getting {
            dependencies {
                api(AndroidX.lifecycle.viewModelKtx)
            }
        }

        val iosMain by getting {
            dependencies {
                // Multiplatform ViewModel
                // https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-viewmodel.html#using-viewmodel-in-common-code
                // Note: This currently doesn't support all multiplatform targets we support (ex: macos, linux, tvos, windows, etc.).
                api("org.jetbrains.androidx.lifecycle:lifecycle-viewmodel-compose:_")
            }
        }

        val jvmMain by getting {
            dependencies {
                // Multiplatform ViewModel
                // https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-viewmodel.html#using-viewmodel-in-common-code
                // Note: This currently doesn't support all multiplatform targets we support (ex: macos, linux, tvos, windows, etc.).
                api("org.jetbrains.androidx.lifecycle:lifecycle-viewmodel-compose:_")
            }
        }

        val jsMain by getting {
            dependencies {
                // Multiplatform ViewModel
                // https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-viewmodel.html#using-viewmodel-in-common-code
                // Note: This currently doesn't support all multiplatform targets we support (ex: macos, linux, tvos, windows, etc.).
                api("org.jetbrains.androidx.lifecycle:lifecycle-viewmodel-compose:_")
            }
        }

        val wasmJsMain by getting {
            dependencies {
                // Multiplatform ViewModel
                // https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-viewmodel.html#using-viewmodel-in-common-code
                // Note: This currently doesn't support all multiplatform targets we support (ex: macos, linux, tvos, windows, etc.).
                api("org.jetbrains.androidx.lifecycle:lifecycle-viewmodel-compose:_")
            }
        }
    }
}

android {
    namespace = "com.mooncloak.kodetools.statex"
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
