plugins {
	java
	id("org.springframework.boot") version "3.5.4"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.rt"
version = "0.0.1-SNAPSHOT"
description = "Prototype for the research tracker application"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("org.postgresql:postgresql") 
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	// Lombok
    compileOnly("org.projectlombok:lombok:1.18.30") 
    annotationProcessor("org.projectlombok:lombok:1.18.30")
	// Testing

    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    testImplementation("net.jqwik:jqwik:1.8.2")
	testImplementation("com.h2database:h2")

}

tasks.withType<Test> {
	useJUnitPlatform()
}
