package com.square.buildsrc

object Versions {
    const val ktLint = "0.31.0"
    const val kotlin = "1.3.72"
}

object BuildPlugins {

    const val androidGradle = "com.android.tools.build:gradle:3.6.3"
    const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val kSerializationGradle = "org.jetbrains.kotlin:kotlin-serialization:${Versions.kotlin}"
    const val androidApplication = "com.android.application"
    const val androidLibrary = "com.android.library"
    const val androidDynamicFeature = "com.android.dynamic-feature"
    const val kotlin = "kotlin"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinAndroidExtensions = "kotlin-android-extensions"
    const val jetbrainsAndroidExtensions = "org.jetbrains.kotlin.android.extensions"
    const val kotlinKapt = "kotlin-kapt"
    const val kotlinxSerialization = "kotlinx-serialization"
    const val mavenPublishPluginGradle = "com.vanniktech:gradle-maven-publish-plugin:0.10.0"
    const val mavenPublishPlugin = "com.vanniktech.maven.publish"
}

object Libs {
    val junit = "junit:junit:4.12"
    val mockito = "org.mockito:mockito-core:2.23.0"
    val truth = "com.google.truth:truth:0.42"

    val mmkv = "com.tencent:mmkv:1.0.23"
    val mvRx = "com.airbnb.android:mvrx:1.3.0"
    val coordinators = "com.squareup.coordinators:coordinators:0.4"

    val threeTenBp = "org.threeten:threetenbp:1.4.2"
    val threeTenBpNoTzdb = "$threeTenBp:no-tzdb"
    val threeTenAbp = "com.jakewharton.threetenabp:threetenabp:1.2.3"

    val gravitySnapHelper = "com.github.rubensousa:gravitysnaphelper:2.0"

    val leakcanary = "com.squareup.leakcanary:leakcanary-android:2.0-beta-3"

    val javaxAnnotation = "javax.annotation:javax.annotation-api:1.3.2"
    val errorProne = "com.google.errorprone:error_prone_annotations:2.3.3"
    val jetAnnotation = "org.jetbrains:annotations:19.0.0"
    val injectAnnotation = "javax.inject:javax.inject:1"
    val findBugsJrs305 = "com.google.code.findbugs:jsr305:3.0.2"
    val reflection = "org.jooq:joor-java-6:0.9.12"

    val matisse = "com.zhihu.android:matisse:0.5.3-beta2"
    val lubanTurbo = "top.zibin:Luban-turbo:1.0.0"
    val paho = "org.eclipse.paho:org.eclipse.paho.client.mqttv3:1.2.1"
    val flexbox = "com.google.android:flexbox:1.1.0"

    object AndroidSdk {
        const val compileSdkVersion = 29
        const val buildToolsVersion = "29.0.3"

        const val minSdkVersion = 21
        const val targetSdkVersion = compileSdkVersion
        const val versionCode = 1
        const val versionName = "1.0"
    }

    object Google {
        val material = "com.google.android.material:material:1.2.0-alpha06"
        val firebaseCore = "com.google.firebase:firebase-core:16.0.8"
        val crashlytics = "com.crashlytics.sdk.android:crashlytics:2.9.9"
        val gmsGoogleServices = "com.google.gms:google-services:4.2.0"
        val fabric = "io.fabric.tools:gradle:1.28.1"
    }

    object Kotlin {
        val stdlibJdk8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
        val stdlibJdk7 = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
        val reflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}"
        val test = "org.jetbrains.kotlin:kotlin-test:${Versions.kotlin}"
        val testJunit = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}"
        val serializationRuntime = "org.jetbrains.kotlinx:kotlinx-serialization-runtime:0.11.1"
    }

    object Coroutines {
        private const val version = "1.3.5"
        val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        val rxJava = "org.jetbrains.kotlinx:kotlinx-coroutines-rx2:$version"
        val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
    }

    object RxJava {
        val rxJava = "io.reactivex.rxjava2:rxjava:2.2.19"
        val rxAndroid = "io.reactivex.rxjava2:rxandroid:2.1.1"
        val stream = "org.reactivestreams:reactive-streams:1.0.3"
    }

    object Dagger {
        private const val version = "2.27"
        val dagger = "com.google.dagger:dagger:$version"
        val androidSupport = "com.google.dagger:dagger-android-support:$version"
        val compiler = "com.google.dagger:dagger-compiler:$version"
        val androidProcessor = "com.google.dagger:dagger-android-processor:$version"
    }

    object AutoValue {
        const val version = "1.7"
        val autoValue = "com.google.auto.value:auto-value-annotations:$version"
        val compiler = "com.google.auto.value:auto-value:$version"
    }

    object Glide {
        private const val version = "4.11.0"
        val glide = "com.github.bumptech.glide:glide:$version"
        val compiler = "com.github.bumptech.glide:compiler:$version"
        val okhttp3Integration = "com.github.bumptech.glide:okhttp3-integration:$version"
    }

    object Retrofit {
        private const val version = "2.8.1"
        val retrofit = "com.squareup.retrofit2:retrofit:$version"
        val rxJavaAdapter = "com.squareup.retrofit2:adapter-rxjava2:$version"
        val gsonConverter = "com.squareup.retrofit2:converter-gson:$version"
        val moshiConverter = "com.squareup.retrofit2:converter-moshi:$version"
        val simpleXmlConverter = "com.squareup.retrofit2:converter-simplexml:$version"
        val protobufConverter = "com.squareup.retrofit2:converter-protobuf:$version"
        val wireConverter = "com.squareup.retrofit2:converter-wire:$version"
        val jaxbConverter = "com.squareup.retrofit2:converter-jaxb:$version"
        val scalarsConverter = "com.squareup.retrofit2:converter-scalars:$version"
    }

    object OkHttp {
        private const val version = "4.4.1"
        val okHttp = "com.squareup.okhttp3:okhttp:$version"
        val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$version"
    }

    object Moshi {
        private const val version = "1.9.2"
        val moshi = "com.squareup.moshi:moshi:$version"
        val kotlin = "com.squareup.moshi:moshi-kotlin:$version"
        val codegen = "com.squareup.moshi:moshi-kotlin-codegen:$version"
    }

    val okio = "com.squareup.okio:okio:2.5.0"

    object Epoxy {
        private const val version = "3.8.0"
        val epoxy = "com.airbnb.android:epoxy:$version"
        val paging = "com.airbnb.android:epoxy-paging:$version"
        val dataBinding = "com.airbnb.android:epoxy-databinding:$version"
        val processor = "com.airbnb.android:epoxy-processor:$version"
    }

    object AssistedInject {
        private const val version = "0.5.0"
        val annotationDagger2 = "com.squareup.inject:assisted-inject-annotations-dagger2:$version"
        val processorDagger2 = "com.squareup.inject:assisted-inject-processor-dagger2:$version"
    }

    val timber = "com.jakewharton.timber:timber:4.7.1"

    object AndroidX {

        object Activity {
            private const val version = "1.2.0-alpha04"
            val activity = "androidx.activity:activity:$version"
            val ktx = "androidx.activity:activity-ktx:$version"
        }

        val annotation = "androidx.annotation:annotation:1.1.0"
        val annotationExperimental = "androidx.annotation:annotation-experimental:1.0.0"


        object Appcompat {
            private const val version = "1.2.0-beta01"
            val appcompat = "androidx.appcompat:appcompat:$version"
            val resource = "androidx.appcompat:appcompat-resource:$version"
        }

        object Arch {
            private const val version = "2.1.0"
            val common = "androidx.arch.core:core-common:$version"
            val runtime = "androidx.arch.core:core-runtime:$version"
            val testing = "androidx.arch.core:core-testing:$version"
        }

        val asynclayoutinflater = "androidx.asynclayoutinflater:asynclayoutinflater:1.0.0"

        val browser = "androidx.browser:browser:1.0.0"

        val biometric = "androidx.biometric:biometric:1.0.0-rc01"
        val autofill = "androidx.autofill:autofill:1.0.0-beta01"

        object Camera {
            private const val version = "1.0.0-beta03"
            val camera2 = "androidx.camera:camera-camera2:$version"
            val core = "androidx.camera:camera-core:$version"
            val lifecycle = "androidx.camera:camera-lifecycle:$version"
            val extensions = "androidx.camera:camera-extensions:1.0.0-alpha10"
            val view = "androidx.camera:camera-view:1.0.0-alpha10"
        }

        val cardview = "androidx.cardview:cardview:1.0.0"

        object Collection {
            private const val version = "1.1.0"
            val collection = "androidx.collection:collection:$version"
            val ktx = "androidx.collection:collection-ktx:$version"
        }

        object Concurrent {
            private const val version = "1.0.0-beta01"
            val future = "androidx.concurrent:concurrent-listenablefuture:$version"
            val callback = "androidx.concurrent:concurrent-listenablefuture-callback:$version"
        }

        object Constraintlayout {
            private const val version = "2.0.0-beta4"
            val constraintlayout = "androidx.constraintlayout:constraintlayout:$version"
            val constraintlayoutSolver =
                "androidx.constraintlayout:constraintlayout-solver:$version"
        }

        val contentpager = "androidx.contentpager:contentpager:1.0.0"

        val coordinatorlayout = "androidx.coordinatorlayout:coordinatorlayout:1.1.0"

        object Core {
            private const val version = "1.3.0-rc01"
            val core = "androidx.core:core:$version"
            val animation = "androidx.core:core-animation:1.0.0-alpha01"
            val role = "androidx.core:core-role:1.0.0-rc01"
            val ktx = "androidx.core:core-ktx:$version"
        }

        val cursoradapter = "androidx.cursoradapter:cursoradapter:1.0.0"

        val customview = "androidx.customview:customview:1.1.0-beta01"

        val drawerlayout = "androidx.drawerlayout:drawerlayout:1.1.0-beta01"
        val exifinterface = "androidx.exifinterface:exifinterface:1.2.0"

        object Dynamicanimation {
            private const val version = "1.1.0-alpha03"
            val dynamicanimation = "androidx.dynamicanimation:dynamicanimation:$version"
            val ktx = "androidx.dynamicanimation:dynamicanimation-ktx:1.0.0-alpha03"
        }

        object Emoji {
            private const val version = "1.1.0-rc01"
            val emoji = "androidx.emoji:emoji:$version"
            val appcompat = "androidx.emoji:emoji-appcompat:$version"
            val bundled = "androidx.emoji:emoji-bundled:$version"
        }

        object Fragment {
            const val version = "1.3.0-alpha04"
            val fragment = "androidx.fragment:fragment:$version"
            val ktx = "androidx.fragment:fragment-ktx:$version"
            val testing = "androidx.fragment:fragment-testing:$version"
        }

        val gridlayout = "androidx.gridlayout:gridlayout:1.0.0"

        val interpolator = "androidx.interpolator:interpolator:1.0.0"

        object Lifecycle {
            const val version = "2.3.0-alpha02"
            val common = "androidx.lifecycle:lifecycle-common:$version"
            val commonJava8 = "androidx.lifecycle:lifecycle-common-java8:$version"
            val compiler = "androidx.lifecycle:lifecycle-compiler:$version"
            val extensions = "androidx.lifecycle:lifecycle-extensions:2.2.0"
            val livedata = "androidx.lifecycle:lifecycle-livedata:$version"
            val livedataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:$version"
            val livedataCore = "androidx.lifecycle:lifecycle-livedata-core:$version"
            val livedataCoreKtx = "androidx.lifecycle:lifecycle-livedata-core-ktx:$version"
            val livedataProcess = "androidx.lifecycle:lifecycle-process:$version"
            val reactive = "androidx.lifecycle:lifecycle-reactivestreams:$version"
            val reactiveKtx = "androidx.lifecycle:lifecycle-reactivestreams-ktx:$version"
            val runtime = "androidx.lifecycle:lifecycle-runtime:$version"
            val runtimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:$version"
            val service = "androidx.lifecycle:lifecycle-service:$version"
            val viewmodel = "androidx.lifecycle:lifecycle-viewmodel:$version"
            val viewmodelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
            val viewmodelSavedstate = "androidx.lifecycle:lifecycle-viewmodel-savedstate:$version"
        }

        val loader = "androidx.loader:loader:1.1.0"

        object Multidex {
            private const val version = "2.0.1"
            val multidex = "androidx.multidex:multidex:$version"
            val instrumentation = "androidx.multidex:multidex-instrumentation:2.0.0"
        }

        object Navigation {
            private const val version = "2.3.0-alpha06"
            val common = "androidx.navigation:navigation-common:$version"
            val commonKtx = "androidx.navigation:navigation-common-ktx:$version"
            val fragment = "androidx.navigation:navigation-fragment:$version"
            val fragmentKtx = "androidx.navigation:navigation-fragment-ktx:$version"
            val runtime = "androidx.navigation:navigation-runtime:$version"
            val runtimeKtx = "androidx.navigation:navigation-runtime-ktx:$version"
            val ui = "androidx.navigation:navigation-ui:$version"
            val uiKtx = "androidx.navigation:navigation-ui-ktx:$version"
            val safeArgsGradle = "androidx.navigation:navigation-safe-args-gradle-plugin:$version"
            val safeArgsGenerator = "androidx.navigation:navigation-safe-args-generator:$version"
        }

        object Paging {
            private const val version = "2.1.2"
            val runtime = "androidx.paging:paging-runtime:$version"
            val runtimeKtx = "androidx.paging:paging-runtime-ktx:$version"
            val common = "androidx.paging:paging-common:$version"
            val commonKtx = "androidx.paging:paging-common-ktx:$version"
            val rxJava = "androidx.paging:paging-rxjava2:$version"
            val rxJavaKtx = "androidx.paging:paging-rxjava2-ktx:$version"
        }

        object Palette {
            private const val version = "1.0.0"
            val palette = "androidx.palette:palette:$version"
            val ktx = "androidx.palette:palette-ktx:$version"
        }

        object Preference {
            private const val version = "1.1.0"
            val preference = "androidx.preference:preference:$version"
            val ktx = "androidx.preference:preference-ktx:$version"
        }

        object Recyclerview {
            private const val version = "1.2.0-alpha03"
            val recyclerview = "androidx.recyclerview:recyclerview:$version"
            val selection = "androidx.recyclerview:recyclerview-selection:1.1.0-rc01"
        }

        object Remotecallback {
            private const val version = "1.0.0-alpha02"
            val remotecallback = "androidx.remotecallback:remotecallback:$version"
            val processor = "androidx.remotecallback:remotecallback-processor:$version"
        }

        object Room {
            const val version = "2.2.5"
            val common = "androidx.room:room-common:$version"
            val compiler = "androidx.room:room-compiler:$version"
            val coroutines = "androidx.room:room-coroutines:2.1.0-alpha04"
            val ktx = "androidx.room:room-ktx:$version"
            val migration = "androidx.room:room-migration:$version"
            val rxJava = "androidx.room:room-rxjava2:$version"
            val runtime = "androidx.room:room-runtime:$version"
            val testing = "androidx.room:room-testing:$version"
        }

        object Benchmark {
            const val version = ":1.0.0"
            val common = "androidx.benchmark:benchmark-common:$version"
            val junit4 = "androidx.benchmark:benchmark-junit4:$version"
        }

        object Savedstate {
            private const val version = "1.0.0"
            val savedstate = "androidx.savedstate:savedstate:$version"
            val bundle = "androidx.savedstate:savedstate-bundle:1.0.0-alpha01"
            val common = "androidx.savedstate:savedstate-common:1.0.0-alpha01"
        }

        object Slice {
            private const val version = "1.0.0"
            val core = "androidx.slice:slice-core:$version"
            val view = "androidx.slice:slice-view:$version"
            val builder = "androidx.slice:slice-builder:$version"
            val builderKtx = "androidx.slice:slice-builder-ktx:$version"
        }

        val slidingpanelayout = "androidx.slidingpanelayout:slidingpanelayout:1.1.0-beta01"
        val sharetarget = "androidx.sharetarget:sharetarget:1.0.0-beta01"

        object Sqlite {
            private const val version = "2.1.0"
            val sqlite = "androidx.sqlite:sqlite:$version"
            val framework = "androidx.sqlite:sqlite-framework:$version"
            val ktx = "androidx.sqlite:sqlite-ktx:$version"
        }

        val swiperefreshlayout = "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0-rc01"
        val transition = "androidx.transition:transition:1.3.0-rc01"

        object Vectordrawable {
            private const val version = "1.1.0"
            val vectordrawable = "androidx.vectordrawable:vectordrawable:$version"
            val animated = "androidx.vectordrawable:vectordrawable-animated:$version"
            val seekable = "androidx.vectordrawable:vectordrawable-seekable-1.0.0-alpha01"
        }

        val viewpager = "androidx.viewpager:viewpager:1.0.0"
        val viewpager2 = "androidx.viewpager2:viewpager2:1.1.0-alpha01"
        val webkit = "androidx.webkit:webkit:1.3.0-alpha02"
        val versionedparcelable = "androidx.versionedparcelable:versionedparcelable:1.1.1"

        object Test {
            private const val version = "1.3.0-beta01"
            val core = "androidx.test:core:$version"
            val coreKtx = "androidx.test:core-ktx:$version"
            val monitor = "androidx.test:monitor:$version"
            val orchestrator = "androidx.test:orchestrator$version"
            val runner = "androidx.test:runner:$version"
            val rules = "androidx.test:rules:$version"
        }

        object Espresso {
            private const val version = "3.3.0-beta01"
            val accessibility = "androidx.test.espresso:espresso-accessibility:$version"
            val contrib = "androidx.test.espresso:espresso-contrib:$version"
            val core = "androidx.test.espresso:espresso-core:$version"
            val idlingResource = "androidx.test.espresso:espresso-idling-resource:$version"
            val intents = "androidx.test.espresso:espresso-intents:$version"
            val remote = "androidx.test.espresso:espresso-remote:$version"
            val web = "androidx.test.espresso:espresso-web:$version"
        }

        object EspressoIdling {
            private const val version = "3.3.0-beta01"
            val concurrent = "androidx.test.espresso.idling:idling-concurrent:$version"
            val net = "androidx.test.espresso.idling:idling-net:$version"
        }

        object TestExt {
            private const val version = "1.1.2-beta01"
            val junit = "androidx.test.ext:junit:$version"
            val junitKtx = "androidx.test.ext:junit-ktx:$version"
            val truth = "androidx.test.ext:truth:1.3.0-beta01"
        }

        object TestService {
            private const val version = "1.3.0-beta01"
            val junit = "androidx.test.ext:junit:$version"
            val junitKtx = "androidx.test.ext:junit-ktx:$version"
            val truth = "androidx.test.ext:truth:$version"
        }

        val testServices = "androidx.test.services:test-services:1.2.0"

        val textclassifier = "androidx.textclassifier:textclassifier:1.0.0-alpha02"

        object Janktesthelper {
            private const val version = "1.0.1"
            val janktesthelper = "androidx.test.janktesthelper:janktesthelper:$version"
            val v23 = "androidx.test.janktesthelper:janktesthelper-v23:1.0.1-alpha1"
        }

        object Uiautomator {
            private const val version = "2.2.0"
            val uiautomator = "androidx.test.uiautomator:uiautomator:$version"
            val v18 = "androidx.test.uiautomator:uiautomator-v18:$version"
        }

        object Work {
            private const val version = "2.4.0-alpha03"
            val runtime = "androidx.work:work-runtime:$version"
            val runtimeKtx = "androidx.work:work-runtime-ktx:$version"
            val rxJava = "androidx.work:work-rxjava2:$version"
            val testing = "androidx.work:work-testing:$version"
        }
    }

    object Pacific {
        private const val version = "1.0.0"
        val guava = "com.github.thepacific:guava:$version"
        val guavaAndroid = "com.github.thepacific:guava-android:$version"
        val core = "com.github.thepacific:core:$version"
    }
}