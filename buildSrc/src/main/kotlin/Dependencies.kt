import Versions.androidxEspressoVersion
import Versions.androidxTestRunnerVersion
import Versions.constraintLayoutVersion
import Versions.coroutinesVersion
import Versions.daggerVersion
import Versions.jUnitVersion
import Versions.kotlinVersion
import Versions.ktxVersion
import Versions.lifecycleExtensionVersion
import Versions.materialComponentVersion
import Versions.gsonVersion
import Versions.navVersion
import Versions.okhttpVersion
import Versions.pagingVersion
import Versions.retrofitCoroutinesAdapterVersion
import Versions.retrofitVersion
import Versions.roomVersion
import Versions.stethoVersion
import Versions.supportVersion

object CoreLibraries {
  const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion"
  const val appcompat = "androidx.appcompat:appcompat:$supportVersion"
  const val materialComponent = "com.google.android.material:material:$materialComponentVersion"
  const val constraintLayout = "androidx.constraintlayout:constraintlayout:$constraintLayoutVersion"
  const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion"
  const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion"
  const val androidKTX = "androidx.core:core-ktx:$ktxVersion"
  const val lifeCycleExtensions = "androidx.lifecycle:lifecycle-extensions:$lifecycleExtensionVersion"
  const val lifeCycleAnnotationProcessor = "androidx.lifecycle:lifecycle-compiler:$lifecycleExtensionVersion"
  const val roomCore = "androidx.room:room-runtime:$roomVersion"
  const val roomAnnotationProcessor =  "androidx.room:room-compiler:$roomVersion"
  const val roomCoroutinesSupport =  "androidx.room:room-ktx:$roomVersion"
  const val paging = "androidx.paging:paging-runtime-ktx:$pagingVersion"
  const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:$navVersion"
  const val navigationUI = "androidx.navigation:navigation-ui-ktx:$navVersion"
}

object ExternalLibraries {
  const val daggerCore = "com.google.dagger:dagger-android:$daggerVersion"
  const val daggerSupport = "com.google.dagger:dagger-android-support:$daggerVersion"
  const val daggerAnnotationProcessor = "com.google.dagger:dagger-android-processor:$daggerVersion"
  const val daggerCompiler ="com.google.dagger:dagger-compiler:$daggerVersion"
  const val retrofitCore = "com.squareup.retrofit2:retrofit:$retrofitVersion"
  const val retrofitCoroutinesAdapter = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:$retrofitCoroutinesAdapterVersion"
  const val retrofitGson = "com.squareup.retrofit2:converter-gson:$retrofitVersion"
  const val gson = "com.google.code.gson:gson:$gsonVersion"
  const val okHttp = "com.squareup.okhttp3:okhttp:$okhttpVersion"
  const val okHttpInterceptor = "com.squareup.okhttp3:logging-interceptor:$okhttpVersion"
  const val stetho = "com.facebook.stetho:stetho:$stethoVersion"
}

object TestLibraries {
  const val junit = "junit:junit:$jUnitVersion"
  const val testRunner = "androidx.test:runner:$androidxTestRunnerVersion"
  const val espresso = "androidx.test.espresso:espresso-core:$androidxEspressoVersion"
}
