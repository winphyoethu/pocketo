plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
    id("pocketo.android.hilt")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(libs.coroutine.core)
}