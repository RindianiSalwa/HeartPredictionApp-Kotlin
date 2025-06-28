plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.heartpredictionapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.heartpredictionapp"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

}

dependencies {


    implementation(libs.androidx.core.ktx)
    implementation("androidx.recyclerview:recyclerview:1.3.1")

    implementation(libs.androidx.lifecycle.runtime.ktx)

    implementation(libs.androidx.appcompat)

    implementation(libs.material)

    implementation(libs.androidx.constraintlayout.v214)

    implementation(libs.androidx.activity)

    implementation(libs.tensorflow.lite)


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)



}