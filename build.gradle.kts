plugins {
    id("java")
    id("application")
}

group = "org.example"
version = "1.0"

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

application {
    mainClass.set("org.example.App")
    applicationName = "app"
}

dependencies {
    implementation("com.google.code.gson:gson:2.10.1")
}

