plugins {
    id("java")
//    application
}

group = "com.dsf"
version = "1.0-SNAPSHOT"

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

tasks.withType<JavaCompile> {
    // Configure the Java compiler to use -Xlint:unchecked
    options.compilerArgs.add("-Xlint:unchecked")
}

tasks.test {
    useJUnitPlatform()
}