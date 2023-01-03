
object DomainDependencies {
    val dependencyNotation = arrayOf(
        Pair(DependencyConfiguration.IMPLEMENTATION, GlobalDependencies.kotlinConfig),
        Pair(DependencyConfiguration.IMPLEMENTATION, GlobalDependencies.javaxInject),
        Pair(DependencyConfiguration.IMPLEMENTATION, GlobalDependencies.coroutines),
        Pair(DependencyConfiguration.TEST_IMPLEMENTATION, GlobalDependencies.junit),
        Pair(DependencyConfiguration.TEST_IMPLEMENTATION, GlobalDependencies.coreTestingKtx),
        Pair(DependencyConfiguration.TEST_IMPLEMENTATION, GlobalDependencies.coreTesting),
        Pair(DependencyConfiguration.TEST_IMPLEMENTATION, GlobalDependencies.mockito),
        Pair(DependencyConfiguration.TEST_IMPLEMENTATION, GlobalDependencies.coreArchTesting),
        )
}