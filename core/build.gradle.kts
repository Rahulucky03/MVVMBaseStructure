plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinAndroidExtensions)
    id(Plugins.kotlinKapt)
    id(Plugins.safeArgs)
}

android {
    compileSdkVersion(Versions.compileSdkVersion)
    defaultConfig {
        minSdkVersion(Versions.minSdkVersion)
        targetSdkVersion(Versions.targetSdkVersion)
        versionCode = Release.versionCode
        versionName = Release.versionName
        testInstrumentationRunner = Config.testInstrumentationRunner
    }
}

/*androidExtensions {
    configure(delegateClosureOf<AndroidExtensionsExtension> {
        isExperimental = true
    })
}*/

dependencies {
    api(CoreLibraries.kotlin)
    api(CoreLibraries.appcompat)
    api(CoreLibraries.materialComponent)
    /**
     * ConstraintLayout
     */
    api(CoreLibraries.constraintLayout)
    /**
     * Coroutines
     */
    api(CoreLibraries.coroutinesCore)
    api(CoreLibraries.coroutinesAndroid)
    /**
     * AndroidKTX
     */
    api(CoreLibraries.androidKTX)
    /**
     * LiveData, ViewModel, LifeCycle
     */
    api(CoreLibraries.lifeCycleExtensions)
    kapt(CoreLibraries.lifeCycleAnnotationProcessor)
    /**
     * Navigation
     */
    api(CoreLibraries.navigationFragment)
    api(CoreLibraries.navigationUI)
    /**
     * Dagger2
     */
    api(ExternalLibraries.daggerCore)
    api(ExternalLibraries.daggerSupport)
    kapt(ExternalLibraries.daggerAnnotationProcessor)
    kapt(ExternalLibraries.daggerCompiler)
    /**
     * Retrofit
     */
    api(ExternalLibraries.retrofitCore)
    api(ExternalLibraries.retrofitCoroutinesAdapter)
    api(ExternalLibraries.retrofitGson)
    api(ExternalLibraries.gson)
    api(ExternalLibraries.okHttp)
    api(ExternalLibraries.okHttpInterceptor)
    /**
     * Room
     */
    api(CoreLibraries.roomCore)
    kapt(CoreLibraries.roomAnnotationProcessor)
    api(CoreLibraries.roomCoroutinesSupport)
    /**
     * Test
     */
    androidTestImplementation(TestLibraries.junit)
    androidTestImplementation(TestLibraries.testRunner)
    androidTestImplementation(TestLibraries.espresso)
}
