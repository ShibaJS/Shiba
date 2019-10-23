plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
}

android {
    compileSdkVersion(appConfig.compileSdkVersion)
    defaultConfig {
        applicationId = "moe.tlaster.shibasample"
        minSdkVersion(appConfig.minSdkVersion)
        targetSdkVersion(appConfig.targetSdkVersion)
        versionCode = appConfig.versionCode
        versionName = appConfig.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(kotlin("stdlib-jdk8", appConfig.kotlinVersion))
    implementation("androidx.appcompat:appcompat:1.0.2")
    implementation("androidx.constraintlayout:constraintlayout:2.0.0-beta1")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.9.9")

    implementation("com.squareup.okhttp3:okhttp:3.13.1")

    testImplementation("junit:junit:4.12")
    androidTestImplementation("androidx.test:runner:1.2.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")
    implementation(project(":shiba"))
    implementation("androidx.recyclerview:recyclerview:1.0.0")
    debugImplementation("com.squareup.leakcanary:leakcanary-android:1.6.3")
    releaseImplementation("com.squareup.leakcanary:leakcanary-android-no-op:1.6.3")
}
