object GlobalVersions {
    const val javaxInjectVersion = "1"
    const val retrofitVersion = "2.6.4"
    const val gsonVersion = "2.8.7"
    const val time4AVersion = "4.4.4-2019c"
    const val okhttpVersion = "4.9.1"
    const val appCompatVersion = "1.3.0"
    const val coroutinesVersion = "1.5.0"
    const val dagger2Version = "2.37"
    const val glideVersion = "4.12.0"
    const val lifecycleVersion = "2.4.0"
    const val archTestVersion = "2.1.0"
    const val fragmentVersion = "1.4.0"
    const val activityVersion = "1.4.0"
    const val navigationVersion = "2.3.5"
    const val hiltVersion = "2.38.1"
    const val materialVersion = "1.5.0-alpha02"
    const val mockitoVersion = "4.1.0"
    const val junitVersion = "4.13.2"
    const val coreTestingVersion = "1.4.0"
    const val coreArchTestingVersion = "2.1.0"
    const val coroutinesTestVersion = "1.6.0"
}

object GlobalDependencies {
    const val core = "androidx.core:core-ktx:${PresentationVersions.coreVersion}"
    const val fragment =
        "androidx.fragment:fragment-ktx:${GlobalVersions.fragmentVersion}"
    const val material =
        "com.google.android.material:material:${GlobalVersions.materialVersion}"
    const val activity =
        "androidx.activity:activity-ktx:${GlobalVersions.activityVersion}"
    const val lifecycleViewModel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${GlobalVersions.lifecycleVersion}"
    const val kotlinConfig =
        "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Configuration.kotlinVersion}"
    const val javaxInject = "javax.inject:javax.inject:${GlobalVersions.javaxInjectVersion}"
    const val navigationUi =
        "androidx.navigation:navigation-ui-ktx:${GlobalVersions.navigationVersion}"
    const val navigationFragment =
        "androidx.navigation:navigation-fragment-ktx:${GlobalVersions.navigationVersion}"
    const val time4A = "net.time4j:time4j-android:${GlobalVersions.time4AVersion}"
    private const val retrofitGroupId = "com.squareup.retrofit2"
    const val retrofitConverter =
        "$retrofitGroupId:converter-gson:${GlobalVersions.retrofitVersion}"
    const val retrofit = "$retrofitGroupId:retrofit:${GlobalVersions.retrofitVersion}"
    const val okHttp = "com.squareup.okhttp3:okhttp:${GlobalVersions.okhttpVersion}"
    const val logInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${CoreVersions.logInterceptorVersion}"
    const val gson =
        "com.google.code.gson:gson:${GlobalVersions.gsonVersion}"

    const val appCompat = "androidx.appcompat:appcompat:${GlobalVersions.appCompatVersion}"
    const val coroutines =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${GlobalVersions.coroutinesVersion}"

    const val hiltAndroid = "com.google.dagger:hilt-android:${GlobalVersions.hiltVersion}"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${GlobalVersions.hiltVersion}"

    const val glide = "com.github.bumptech.glide:glide:${GlobalVersions.glideVersion}"

    const val junit = "junit:junit:${GlobalVersions.junitVersion}"
    const val mockito = "org.mockito.kotlin:mockito-kotlin:${GlobalVersions.mockitoVersion}"
    const val coreTesting = "androidx.test:core:${GlobalVersions.coreTestingVersion}"
    const val coreTestingKtx = "androidx.test:core-ktx:${GlobalVersions.coreTestingVersion}"
    const val coreArchTesting = "androidx.arch.core:core-testing:${GlobalVersions.coreArchTestingVersion}"
    const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${GlobalVersions.coroutinesTestVersion}"

}