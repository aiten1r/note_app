// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false
    id("androidx.navigation.safeargs") version "2.8.5" apply false // Добавьте эту строку
    id("com.google.gms.google-services") version "4.4.2" apply false

}