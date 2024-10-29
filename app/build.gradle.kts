plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.serialization)
    kotlin("plugin.parcelize")
}

android {
    namespace = "com.dapcoding.catsapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.dapcoding.catsapp"
        minSdk = 25
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {

        debug {
            isMinifyEnabled =  false
            isDebuggable =  true

            buildConfigField("String", "SERVER_URL", "\"https://api.thecatapi.com/v1/\"")
            buildConfigField("String", "IMAGES_URL", "\"https://cdn2.thecatapi.com/images/\"")
            buildConfigField("String", "SERVER_API", "\"live_GYM9idLuaxnIY8MttbEHu8qFuuNWv0l55FDgLLDEROu80votCoO3W4LrgnJrABRt\"")
        }
        release {
            isMinifyEnabled = false
            isDebuggable =  false

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "SERVER_URL", "\"https://api.thecatapi.com/v1/\"")
            buildConfigField("String", "IMAGES_URL", "\"https://cdn2.thecatapi.com/images/\"")
            buildConfigField("String", "SERVER_API", "\"live_GYM9idLuaxnIY8MttbEHu8qFuuNWv0l55FDgLLDEROu80votCoO3W4LrgnJrABRt\"")

        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    hilt {
        enableAggregatingTask = false
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.ui.text.google.fonts)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Compose
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.material.icons.extended)

    //Serialization
    implementation(libs.kotlinx.serialization.json)

    // Dagger - Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    ksp (libs.androidx.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

    //Testing
    testImplementation(libs.mockito.core)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.androidx.core.testing)

    // Room
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)

    // Kotlin Extensions and Coroutines support for Room
    implementation(libs.androidx.room.ktx)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.logging.interceptor)
    implementation(libs.converter.moshi)

    //Coil
    implementation(libs.coil.compose)
}