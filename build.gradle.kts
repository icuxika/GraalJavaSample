plugins {
    application
    id("org.graalvm.buildtools.native") version "0.9.28"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

repositories {
    mavenLocal()
    maven {
        url = uri("https://repo.maven.apache.org/maven2/")
    }
}

dependencies {
    implementation("com.google.guava:guava:32.1.3-jre")
    testImplementation("junit:junit:3.8.1")
}

group = "com.icuxika"
version = "1.0-SNAPSHOT"
description = "GraalJavaSample"

application {
    mainClass = "com.icuxika.App"
}

tasks.register<Jar>("uberJar") {
    archiveClassifier = "uber"

    from(sourceSets.main.get().output)

    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
    })

    manifest {
        attributes(
            mapOf(
                "Main-Class" to application.mainClass
            )
        )
    }
}

graalvmNative {
    binaries {
        named("main") {
            imageName.set("GraalJavaSample")
            mainClass.set(application.mainClass)
        }
    }
    binaries.all {
        buildArgs.add("--verbose")
    }
}

tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
}
