plugins {
    id("pocketo.android.library")
    id("pocketo.android.hilt")
}

android {
    namespace = "com.winphyoethu.pocketo.data"

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    api(project(":core:common"))
    api(project(":core:model"))
    api(project(":core:datastore"))
    implementation(project(":core:database"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.bundles.paging)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}