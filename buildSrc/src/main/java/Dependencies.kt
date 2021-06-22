object Releases {
    val versionCode = 10000
    val versionName = "1.0"
}

object Versions {
    val kotlin = "1.3.11"
    val gradle = "3.4.2"
    val googleServices = "4.3.3"

    val retrofit = "2.5.0"
    val okhttp = "3.12.1"
    val anko = "0.10.8"

    val appCompat = "1.0.0-rc01"
    val constraintLayout = "1.1.3"
    val legacySupport = "1.0.0"
    val ktx = "1.5.0"
    val dexter = "5.0.0"
    val lifecycle = "2.2.0-alpha01"
    var smartTabLayout = "2.0.0@aar"
    val lottie = "2.8.0"

    val androidAnnotations = "4.4.0"
    val cameraView = "2.6.2"
    val scrollingPagerIndicator = "1.0.6"

    val material = "1.3.0"
    val shimmer = "0.4.0"
    val cardIo = "5.5.0"

    val contacts = "1.1.7"

    val socketIo = "1.0.0"

    val acquireIo = "2.2.5"

    val acquireIoAppCompat = "27.1.0"
    val acquireIoDesign = "27.1.0"
    val acquireIotEmoji = "27.1.0"
    val acquireIoCustomTabs = "27.1.0"

    val networking = "1.0.2"

    val maskedEditText = "1.0.5"

    val glide = "4.11.0"

    val exifInterface = "1.0.0"
    val palette = "1.0.0"
    val recyclerView = "1.0.0-beta01"

    val brotli = "0.1.1"

    val playMaps = "16.1.0"
    val playLocation = "16.0.0"
    val playBase = "16.0.1"
    val playIdentity = "16.0.0"
    val playAuth = "16.0.1"
    val playAuthApiPhone = "16.0.0"

    val junit = "4.12"
    val testRunner = "1.2.0"
    val espresso = "3.2.0"
    val mockK = "1.9.2"
    val arch = "1.1.1"

    val dokka = "0.9.17"
    val pushWoosh = "5.+"
    val branchIO = "3.+"

    val fireBaseAnalytics = "18.0.0"
    val fireBaseMessaging = "20.0.0"
    val fireBaseCrashlytics = "17.2.2"

    val mixpanel = "5.8.2"
    val installreferrer = "1.1"


    val facebook = "5.+"

    val appMetrica = "3.13.1"

    val coroutines = "1.3.3"

    val zxing = "3.6.0"
    val biometrcisCompat = "1.1.0"

    val surveyMonkey = "2.0.0"

    val salesForceVersion = "4.1.0"

    val giphy = "1.2.0"

    val anyline = "25"

    val fairebase_ml_vision = "24.0.1"
    val fairebase_ml_vision_face = "19.0.0"

    val idNow = "4.1.6"

    val blurView = "1.6.3"

    val retrofitScalarConverter = "2.7.1"

    val koin = "2.2.2"
}

object Libraries {
    val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    val kotlinReflectLib = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}"

    val annotations = "org.androidannotations:androidannotations-api:${Versions.androidAnnotations}"
    val cameraView = "com.otaliastudios:cameraview:${Versions.cameraView}"

    val shimmer = "com.facebook.shimmer:shimmer:${Versions.shimmer}"
    val cardIo = "io.card:android-sdk:${Versions.cardIo}"

    val contacts = "com.github.tamir7.contacts:contacts:${Versions.contacts}"
    val socketIo = "io.socket:socket.io-client:${Versions.socketIo}"

    val acquireIoCore = "com.acquireio:core:${Versions.acquireIo}"

    val acquireIoAppCompat = "com.android.support:appcompat-v7:${Versions.acquireIoAppCompat}"
    val acquireIoDesign = "com.android.support:design:${Versions.acquireIoDesign}"
    val acquireIoEmoji = "com.android.support:support-emoji-appcompat:${Versions.acquireIotEmoji}"
    val acquireIoCustomTabs = "com.android.support:customtabs:${Versions.acquireIoCustomTabs}"
    val networking = "com.amitshekhar.android:android-networking:${Versions.networking}"

    val lottie = "com.airbnb.android:lottie:${Versions.lottie}"

    val maskedEditText = "ru.egslava:MaskedEditText:${Versions.maskedEditText}"

    val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"

    val brotli = "org.brotli:dec:${Versions.brotli}"
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    val retrofitScalarConverter = "com.squareup.retrofit2:converter-scalars:${Versions.retrofitScalarConverter}"
    val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
    val anko = "org.jetbrains.anko:anko:${Versions.anko}"

    val dexter = "com.karumi:dexter:${Versions.dexter}"
    val exifInterface = "androidx.exifinterface:exifinterface:${Versions.exifInterface}"

    val ktxCore = "androidx.core:core-ktx:${Versions.ktx}"
    val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"

    val smartTabLayout = "com.ogaclejapan.smarttablayout:library:${Versions.smartTabLayout}"
    var pushWoosh = "com.pushwoosh:pushwoosh:${Versions.pushWoosh}"

    val branchIO = "io.branch.sdk.android:library:${Versions.branchIO}"
    val scrollingPagerIndicator = "ru.tinkoff.scrollingpagerindicator:scrollingpagerindicator:${Versions.scrollingPagerIndicator}"

    val zxing = "com.journeyapps:zxing-android-embedded:${Versions.zxing}"

    val surveyMonkey = "com.surveymonkey:surveymonkey-android-sdk:${Versions.surveyMonkey}"

    val salesForceChat = "com.salesforce.service:chat-ui:${Versions.salesForceVersion}"
    val giphy = "com.giphy.sdk:ui:${Versions.giphy}"

    val anyline = "io.anyline:anylinesdk:${Versions.anyline}"

    val idNow = "de.idnow.sdk:idnow-android-sdk:${Versions.idNow}"
    val blurView = "com.eightbitlab:blurview:${Versions.blurView}"
}

object FireBase {
    val fireBaseAnalytics = "com.google.firebase:firebase-analytics:${Versions.fireBaseAnalytics}"
    val fireBaseMessaging = "com.google.firebase:firebase-messaging:${Versions.fireBaseMessaging}"

    val fireBaseCrashlytics = "com.google.firebase:firebase-crashlytics:${Versions.fireBaseCrashlytics}"
}

object Analytics {
    val mixpanel = "com.mixpanel.android:mixpanel-android:${Versions.mixpanel}"
    val installreferrer = "com.android.installreferrer:installreferrer:${Versions.installreferrer}"

    val facebook = "com.facebook.android:facebook-android-sdk:${Versions.facebook}"

    val appMetrica = "com.yandex.android:mobmetricalib:${Versions.appMetrica}"
}

object SupportLibraries {
    val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    val material = "com.google.android.material:material:${Versions.material}"
    val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    val legacy = "androidx.legacy:legacy-support-v4:${Versions.legacySupport}"
    val palette = "androidx.palette:palette:${Versions.palette}"
    val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
    val biometrics = "androidx.biometric:biometric:${Versions.biometrcisCompat}"
}

object GoogleLibraries {
    val playMaps = "com.google.android.gms:play-services-maps:${Versions.playMaps}"
    val playLocation = "com.google.android.gms:play-services-location:${Versions.playLocation}"
    val playBase = "com.google.android.gms:play-services-base:${Versions.playBase}"
    val playIdentity = "com.google.android.gms:play-services-identity:${Versions.playIdentity}"
    val playAuth = "com.google.android.gms:play-services-auth:${Versions.playAuth}"
    val playAuthApiPhone = "com.google.android.gms:play-services-auth-api-phone:${Versions.playAuthApiPhone}"
}

object FirebaseLibraries {
    val ml_vision = "com.google.firebase:firebase-ml-vision:${Versions.fairebase_ml_vision}"
    val ml_vision_face = "com.google.firebase:firebase-ml-vision-face-model:${Versions.fairebase_ml_vision_face}"
}

object TestLibraries {
    val junit = "junit:junit:${Versions.junit}"
    val testRunner = "androidx.test:runner:${Versions.testRunner}"
    val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    val mockK = "io.mockk:mockk:${Versions.mockK}"
    val androidMockK = "io.mockk:mockk-android:${Versions.mockK}"
    val arch = "android.arch.core:core-testing:${Versions.arch}"
}

object Koin {
    val koinLib = "org.koin:koin-android:${Versions.koin}"
    val koinViewModelLib = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
    val koinFragmentLib = "org.koin:koin-androidx-fragment:${Versions.koin}"
}

object Coroutines {
    val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
}