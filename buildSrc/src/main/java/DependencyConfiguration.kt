

interface BaseDependencyConfiguration {
    val IMPLEMENTATION: String
    val IMPLEMENTATION_PLATFORM: String
    val API: String
    val TEST_API: String
    val ANNOTATION_PROCESSING_ANDROID_TEST: String
    val ANNOTATION_PROCESSING_TEST: String
    val ANNOTATION_PROCESSING: String
    val ANDROID_TEST_IMPLEMENTATION: String
    val TEST_IMPLEMENTATION: String
    val COMPILE_ONLY: String
}

object DependencyConfiguration : BaseDependencyConfiguration {
    override val IMPLEMENTATION = "implementation"
    const val DEBUG_IMPLEMENTATION = "debugImplementation"
    override val IMPLEMENTATION_PLATFORM = "implementationPlatform"
    override val API = "api"
    override val TEST_API = "testImplementation"
    override val ANNOTATION_PROCESSING_ANDROID_TEST = "kaptAndroidTest"
    override val ANNOTATION_PROCESSING_TEST = "kaptTest"
    override val ANNOTATION_PROCESSING = "kapt"
    override val ANDROID_TEST_IMPLEMENTATION = "androidTestImplementation"
    override val TEST_IMPLEMENTATION = "testImplementation"
    override val COMPILE_ONLY = "compileOnly"
}