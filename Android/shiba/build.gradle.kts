plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("digital.wup.android-maven-publish").version("3.6.2")
}


android {
    compileSdkVersion(appConfig.compileSdkVersion)

    lintOptions {
        isAbortOnError = false
    }

    defaultConfig {
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

}

dependencies {
    implementation(kotlin("stdlib-jdk8", appConfig.kotlinVersion))
    implementation(kotlin("reflect", appConfig.kotlinVersion))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.+")
    implementation("androidx.appcompat:appcompat:1.+")
    implementation("androidx.gridlayout:gridlayout:1.+")
    implementation("androidx.recyclerview:recyclerview:1.+")
    implementation("androidx.constraintlayout:constraintlayout:2.0.0-beta2")
    implementation("com.github.LiquidPlayer:LiquidCore:0.6.+")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.9.+")
    implementation("com.github.kittinunf.fuel:fuel:1.+")
    implementation("com.github.kittinunf.fuel:fuel-android:1.+")
    implementation("com.github.kittinunf.fuel:fuel-coroutines:1.+")
    implementation("com.github.kittinunf.fuel:fuel-jackson:1.+")
    testImplementation("junit:junit:4.12")
    androidTestImplementation("androidx.test.ext:junit:1.1.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")
}


publishing {
    repositories {
        maven {
            name = "Github"
            url = uri(getConfiguration("source", ""))
            credentials {
                username = getConfiguration("user", "")
                password = getConfiguration("token", "")
            }
        }
    }
    publications {
        create<MavenPublication>("mavenAar") {
            groupId = "org.shibajs"
            artifactId = "shiba"
            version = appConfig.versionName
            from(components["android"])
        }
    }
}
// apply(from= "publish.gradle")