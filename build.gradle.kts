apply(from = "buildSrc/repos.gradle.kts")
buildscript {

    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:${Configuration.gradleVersion}")
        classpath("com.google.gms:google-services:${Configuration.googleServiceVersion}")
        classpath("com.google.firebase:firebase-crashlytics-gradle:${Configuration.analyticVersion}")
        classpath("com.google.dagger:dagger:${Configuration.dagger2Version}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Configuration.kotlinVersion}")
        classpath ("androidx.navigation:navigation-safe-args-gradle-plugin:${GlobalVersions.navigationVersion}")
        classpath("com.google.dagger:hilt-android-gradle-plugin:${GlobalVersions.hiltVersion}")
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts.kts.kts files
    }

}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
    }
}


tasks.register("clean").configure {
    delete("build")
}