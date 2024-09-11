plugins {
    id("java")
    `java-library`
    `maven-publish`
//    signing
}

group = "com.github.sipsuru"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {

    implementation("com.formdev:flatlaf:3.5.1")
    implementation("com.github.weisj:jsvg:1.6.1")
    implementation("com.formdev:flatlaf-extras:3.5.1")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

java {
    withJavadocJar()
    withSourcesJar()
}

publishing {
    publications {
        create<MavenPublication>("RavenSwingToasts") {
            artifactId = "raven-swing-toast"
            from(components["java"])
            versionMapping {
                usage("java-api") {
                    fromResolutionOf("runtimeClasspath")
                }
                usage("java-runtime") {
                    fromResolutionResult()
                }
            }
            pom {
                name = "Raven Swing Toasts"
                description = "Swing Toasts by DJ-Raven"
                url = "https://github.com/sipsuru/raven-swing-toast/"
                licenses {
                    license {
                        name = "The Apache License, Version 2.0"
                        url = "http://www.apache.org/licenses/LICENSE-2.0.txt"
                    }
                }
                developers {
                    developer {
                        id = "raven"
                        name = "Raven Laing"
                    }

                    developer {
                        id = "dragon"
                        name = "Dragon"
                        email = "dragon@dragon.edu.lk"
                    }
                }
                scm {
                    connection = "scm:git:git://github.com/sipsuru/raven-swing-toast.git"
                    url = "https://github.com/sipsuru/raven-swing-toast/"
                }
            }
        }
    }

    repositories {
        maven {
            name = "GitHub"
            url = uri("https://maven.pkg.github.com/sipsuru/raven-swing-toast")
            credentials {
                username = project.findProperty("gpr.user") as String? ?: System.getenv("USERNAME")
                password = project.findProperty("gpr.key") as String? ?: System.getenv("TOKEN")
            }
        }
    }
    publications {
        register<MavenPublication>("gpr") {
            from(components["java"])
        }
    }
}

//signing {
//    useGpgCmd()
//    sign(publishing.publications["RavenSwingToasts"])
//}

tasks.javadoc {
    if (JavaVersion.current().isJava9Compatible) {
        (options as StandardJavadocDocletOptions).addBooleanOption("html5", true)
    }
}

tasks.withType<JavaCompile> {
    // Configure the Java compiler to use -Xlint:unchecked
    options.compilerArgs.add("-Xlint:unchecked")
}
//
tasks.test {
    useJUnitPlatform()
}
