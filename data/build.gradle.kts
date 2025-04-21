plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
}

android {
    namespace = "com.example.data"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(project(":domain"))

    // Room runtime
    implementation ("androidx.room:room-runtime:2.7.0")
    // Room compiler (для генерации кода)
    kapt ("androidx.room:room-compiler:2.7.0")
    // Room с Coroutines (опционально)
    implementation ("androidx.room:room-ktx:2.7.0")

    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")

    //koin
    implementation ("io.insert-koin:koin-core:3.5.0")

    implementation(platform("com.google.firebase:firebase-bom:33.12.0"))
    implementation("com.google.firebase:firebase-analytics")
    implementation ("com.google.firebase:firebase-auth")


    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}