plugins {
//    alias(libs.plugins.androidApplication)
//    alias(libs.plugins.jetbrainsKotlinAndroid)

//    kotlin("jvm")
//    id("com.google.devtools.ksp")

    //
//    id("com.google.devtools.ksp") version "2.0.0-1.0.21"
//    kotlin("jvm")
//    /* tambahkan plugin dibawah ini */
//    id ("com.google.dagger.hilt.android")
//    id ("kotlin-kapt")
//    id("com.google.dagger.hilt.android")
//    kotlin("jvm") bru
//    id("com.google.devtools.ksp") bru
//    id ("kotlin-parcelize")

    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp") version "1.9.20-1.0.14"
}
//repositories {
//    google()
//    mavenCentral()
//}

android {
    namespace = "com.example.financialtracker"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.financialtracker"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    lint {
        checkReleaseBuilds = false
        abortOnError = false
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
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
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.legacy.support.v4)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.room.common)
    implementation(libs.androidx.room.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Coroutine Lifecycle Scopes
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.0")

    // Splashscreen
    implementation("androidx.core:core-splashscreen:1.0.0")

    //fragment
    // Java language implementation
    implementation("androidx.fragment:fragment:1.7.1")
    // Kotlin language implementation
    implementation("androidx.fragment:fragment-ktx:1.7.1")

    //viewmodel
    implementation ("androidx.fragment:fragment-ktx:1.3.6")
    implementation ("com.google.android.material:material:1.4.0")

    //recycleview
    implementation ("androidx.recyclerview:recyclerview:1.2.1")
    //cardview
    implementation ("androidx.cardview:cardview:1.0.0")
    //glide
    implementation ("com.github.bumptech.glide:glide:4.12.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.12.0")

    //livedata
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.4.0")

    //KSP
//    implementation(kotlin("stdlib-jdk8"))
//    implementation("com.google.dagger:dagger-compiler:2.51.1")
//    ksp("com.google.dagger:dagger-compiler:2.51.1")
//    implementation(kotlin("stdlib-jdk8"))
//    implementation("com.google.dagger:dagger:2.51.1")
//    ksp("com.google.dagger:dagger-compiler:2.51.1")
//
//    /* tambahkan library dibawah ini untuk implementasi dependency injection hilt*/
//    implementation 'com.google.dagger:hilt-android:2.44'
//    kapt 'com.google.dagger:hilt-compiler:2.44'
//    /* tambahkan library dibawah ini untuk implementasi room database */
//    implementation 'androidx.room:room-ktx:2.5.1'
//    implementation 'androidx.appcompat:appcompat:1.6.1'
//    kapt 'androidx.room:room-compiler:2.5.1'

//    val room_version = "2.6.1"
//
//    implementation("androidx.room:room-runtime:$room_version")
//    annotationProcessor("androidx.room:room-compiler:$room_version")
//
//    //Kapt
//    val roomVersion = "2.6.1"
//    implementation("androidx.room:room-ktx:$roomVersion")
//    kapt("androidx.room:room-compiler:$roomVersion")

    //terbaru
//    implementation(kotlin("stdlib-jdk8"))
//    implementation("com.google.dagger:dagger:2.51.1")
//    ksp("com.google.dagger:dagger-compiler:2.51.1")

    //Room
    implementation("androidx.room:room-runtime:${rootProject.extra["room_version"]}")
    ksp("androidx.room:room-compiler:${rootProject.extra["room_version"]}")
    implementation("androidx.room:room-ktx:${rootProject.extra["room_version"]}")

    implementation ("com.jaredrummler:material-spinner:1.3.1")
}