plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "7.1.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"
var lombok_version = "1.18.32"

repositories {
    mavenCentral()
}

dependencies {
    // lombok
    compileOnly("org.projectlombok:lombok:$lombok_version")
    annotationProcessor("org.projectlombok:lombok:$lombok_version")

    // https://mvnrepository.com/artifact/com.googlecode.libphonenumber/libphonenumber
    implementation("com.googlecode.libphonenumber:libphonenumber:8.13.38")

    // https://mvnrepository.com/artifact/commons-validator/commons-validator
    implementation("commons-validator:commons-validator:1.9.0")

    // https://mvnrepository.com/artifact/org.jline/jline
    implementation("org.jline:jline:3.26.1")
    // https://mvnrepository.com/artifact/org.jline/jline-terminal
    implementation("org.jline:jline-terminal:3.26.1")
    // https://mvnrepository.com/artifact/org.jline/jline-reader
    implementation("org.jline:jline-reader:3.26.1")

    // https://mvnrepository.com/artifact/de.siegmar/fastcsv
    implementation("de.siegmar:fastcsv:3.1.0")
}

tasks.register<JavaExec>("runSimpleDemo") {
    group = "application"
    description = "Run the simple CSV demo class"
    mainClass.set("org.example.SimpleCsvDemo")
    classpath = sourceSets["main"].runtimeClasspath
    standardInput = System.`in` // Set standard input to the console input
    standardOutput = System.`out` // Set standard input to the console input
}

/**
 * Simple task to run the CsvQuotes class.
 */
tasks.register<JavaExec>("runCsvQuotes") {
    group = "application"
    description = "Run the CSV REPL dmeo class"
    mainClass.set("org.example.CsvQuotes")
    classpath = sourceSets["main"].runtimeClasspath
    standardInput = System.`in` // Set standard input to the console input
    standardOutput = System.`out` // Set standard input to the console input
}

/**
 * Simple task to run the CsvReplDemo class in the IDE / Gradle in dumb mode.
 * It is ideal to generate the shadow JAR and execute the same, though.
 */
tasks.register<JavaExec>("runCsvRepl") {
    group = "application"
    description = "Run the CSV REPL dmeo class"
    mainClass.set("org.example.CsvReplDemo")
    classpath = sourceSets["main"].runtimeClasspath
    standardInput = System.`in` // Set standard input to the console input
    standardOutput = System.`out` // Set standard input to the console input
}

/**
 * Task to build a Shadow JAR for generating a JAR that could be
 * easily executed in a system terminal. Executing the shadow JAR
 * in a terminal helps in exhibiting the power of implementing an
 * REPL using JLine which includes but is not limited to navigation
 * while inputting text, history.
 */
tasks {
    shadowJar {
        manifest {
            attributes(mapOf("Main-Class" to "org.example.CsvReplDemo"))
        }
    }
}