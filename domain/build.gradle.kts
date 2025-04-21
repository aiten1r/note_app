plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
}

dependencies {
    // Библиотека Kotlin (уже должна быть подключена в проекте)
    implementation ("org.jetbrains.kotlin:kotlin-stdlib:2.1.0")

    // Поддержка Coroutines для использования Flow (если используете Flow)
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
}
