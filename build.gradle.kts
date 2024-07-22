plugins {
    application
    id ("java-library")
}

application {
    mainClass = "com.thomasnolan.Main"
}

group = "com.thomasnolan"
version = "1.0-SNAPSHOT"

java {
    toolchain{
        languageVersion = JavaLanguageVersion.of(22)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.simplejavamail:simple-java-mail:8.11.2")

    implementation("com.sun.mail:jakarta.mail:2.0.1")
    implementation("com.sun.activation:jakarta.activation:2.0.1")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

tasks.getByName("run", JavaExec::class) {
    standardInput = System.`in`
}