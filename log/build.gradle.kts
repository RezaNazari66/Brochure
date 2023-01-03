plugins {
    id("java-library")
    id("kotlin")
    id("kotlin-kapt")
}

java {
    sourceCompatibility = Configuration.JavaVersionSupport
    targetCompatibility = Configuration.JavaVersionSupport
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    LogDependencies.dependencyNotation.forEach {
        this.add(it.first, it.second)
    }

}