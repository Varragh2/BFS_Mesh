plugins {
    application
    id ("java-library")
}

application {
    mainClass = "com.thomasnolan.Main"
}

group = "com.thomasnolan"
version = "1.0-SNAPSHOT"

// Use JDK_21
java {
    toolchain{
        languageVersion = JavaLanguageVersion.of(22)
    }
}

// Use Maven repo
repositories {
    mavenCentral()
}

// Set project dependencies
dependencies {
    // Send Email via SSL
    implementation("com.sun.mail:jakarta.mail:2.0.1")
    implementation("com.sun.activation:jakarta.activation:2.0.1")

    // Tests
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()

    //TEMP: `gradle test --tests {testName}` syntax not working, exclude SendEmailSSLTest
    filter {
        excludeTestsMatching("*SendEmail*")
    }    
}

// Workaround to handle user input when running with `gradle run`
tasks.getByName("run", JavaExec::class) {
    standardInput = System.`in`
}