// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    extra.apply {
        set("room_version", "2.6.0")
    }
}
plugins {
//    alias(libs.plugins.androidApplication) apply false
//    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    id("com.android.application") version "8.1.4" apply false
    id("com.android.library") version "8.1.4" apply false
    id("org.jetbrains.kotlin.android") version "1.9.20" apply false
//
//    id("org.jetbrains.kotlin.jvm") version "2.0.0"
//    id("com.google.devtools.ksp") version "1.0.21"
}
tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}