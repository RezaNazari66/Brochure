plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = Configuration.compileSdkVersion
    buildToolsVersion = Configuration.buildToolsVersion
    defaultConfig {
        applicationId = "com.bonial.${Configuration.projectName}"
        minSdk = Configuration.minSdkVersion
        targetSdk = Configuration.targetSdkVersion
        versionCode = Configuration.versionCode
        versionName = Configuration.versionName
        multiDexEnabled = true
    }

    buildTypes {
        getByName(BuildTypes.Debug.name) {
            this.isMinifyEnabled = BuildTypes.Debug.isMinifyEnabled
            this.isShrinkResources = BuildTypes.Debug.isShrinkResources
            this.isDebuggable = BuildTypes.Debug.isDebuggable
        }
        getByName(BuildTypes.Release.name) {
            this.isMinifyEnabled = BuildTypes.Release.isMinifyEnabled
            this.isShrinkResources = BuildTypes.Release.isShrinkResources
            this.isDebuggable = BuildTypes.Release.isDebuggable
            this.proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                *BuildTypes.Release.proguardFiles
            )
        }
    }

    buildFeatures {
        this.viewBinding = true
    }

    compileOptions {
        sourceCompatibility = Configuration.JavaVersionSupport
        targetCompatibility = Configuration.JavaVersionSupport
    }
    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }

    kapt {
        correctErrorTypes = true
    }

}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = Configuration.jvmTarget
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(project(":log"))
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":features:brochureList"))

    CoreDependencies.dependencyNotation.forEach {
        this.add(it.first, it.second)
    }
}