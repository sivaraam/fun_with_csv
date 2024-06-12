plugins {
    id("java")
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
}

tasks.register<JavaExec>("runDemo") {
    group = "application"
    description = "Run the CSV Demo Java class"
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
