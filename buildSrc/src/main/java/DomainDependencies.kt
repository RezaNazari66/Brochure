
object DomainDependencies {
    val dependencyNotation = arrayOf(
        Pair(DependencyConfiguration.IMPLEMENTATION, GlobalDependencies.kotlinConfig),
        Pair(DependencyConfiguration.IMPLEMENTATION, GlobalDependencies.javaxInject),
        Pair(DependencyConfiguration.IMPLEMENTATION, GlobalDependencies.coroutines),
        Pair(DependencyConfiguration.IMPLEMENTATION, GlobalDependencies.pagingCommon),
        Pair(DependencyConfiguration.IMPLEMENTATION, GlobalDependencies.junit),
//        Pair(DependencyConfiguration.IMPLEMENTATION, GlobalDependencies.coreTesting),
        Pair(DependencyConfiguration.IMPLEMENTATION, GlobalDependencies.mockito),
    )
}