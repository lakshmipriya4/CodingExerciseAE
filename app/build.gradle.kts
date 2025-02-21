plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.kotlin.android.ksp)
    kotlin("plugin.serialization") version "1.9.0"
}

android {
    namespace = "com.example.codingexerciseae"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.codingexerciseae"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        multiDexEnabled = true
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    viewBinding {
        enable = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    ksp(libs.dagger.compiler)
    // Networking
    implementation (libs.retrofit)
    implementation (libs.okhttp)
    implementation (libs.logging.interceptor)
    implementation (libs.converter.gson)
    //Coroutine
    implementation (libs.kotlinx.coroutines.android)
    implementation (libs.kotlinx.coroutines.core)
    implementation (libs.gson)
    implementation (libs.androidx.activity.ktx)
    //Glide
    implementation (libs.glide)
    ksp(libs.ksp)
    implementation(libs.multidex)

    //testingKotlin
    androidTestImplementation(libs.kotlinx.coroutines.test)
    testImplementation (libs.mockito.core)
    testImplementation (libs.mockito.kotlin)
    androidTestImplementation (libs.androidx.core.testing)
    testImplementation (libs.junit)
    testImplementation (libs.mockk)
    androidTestImplementation(libs.mockk.android)
    testImplementation(libs.mockk.agent)
    androidTestImplementation(libs.kotlinx.coroutines.android)
}