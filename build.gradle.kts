plugins {
    id("java")
    `java-library`
    `maven-publish`
//    signing
}

group = "com.sipsuru"
version = "1.0.0"

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

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/sipsuru/raven-swing-toast")
            credentials {
                username = System.getenv("USERNAME")
                password = System.getenv("TOKEN")
            }
        }
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
