// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.com.android.application) apply false
    alias(libs.plugins.org.jetbrains.kotlin.android) apply false
}
// Utilisation de Gradle 7.2
// Vérifiez la version la plus récente ici : https://gradle.org/releases/
task clean(type: Delete) {
    delete rootProject.buildDir
}

buildscript {
    ext {
        compose_version = '1.2.0-beta02'
        kotlin_version = '1.6.10'
        nav_version = '2.4.1'
    }

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:7.2.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")
        classpath("org.jetbrains.compose:compose-gradle-plugin:$compose_version")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}
