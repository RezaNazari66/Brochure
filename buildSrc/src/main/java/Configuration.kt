import org.gradle.api.JavaVersion

private object VersionConfiguration {
    const val versionMajor = 1
    const val versionMinor = 0
    const val versionPatch = 1
    const val buildNumber = 1
}

object Configuration {
    const val buildToolsVersion = "31.0.0"
    const val minSdkVersion = 21
    const val targetSdkVersion = 33
    const val compileSdkVersion = 33
    const val dagger2Version = GlobalVersions.dagger2Version
    const val kotlinVersion = "1.5.31"
    const val gradleVersion = "7.0.3"
    const val googleServiceVersion = "4.3.3"
    const val analyticVersion = "2.2.1"
    const val versionCode = VersionConfiguration.buildNumber
    const val versionName = "${VersionConfiguration.versionMajor}.${VersionConfiguration.versionMinor}.${VersionConfiguration.versionPatch}"
    const val projectName = "Brochures"
    private const val appIdPrefix = "com.bonial"
    const val applicationId = "$appIdPrefix.brochures"
    val JavaVersionSupport = JavaVersion.VERSION_1_8
    const val jvmTarget = "1.8"
}