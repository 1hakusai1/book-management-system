plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    id("org.springframework.boot") version "3.3.4"
    id("io.spring.dependency-management") version "1.1.6"
    id("org.jooq.jooq-codegen-gradle") version "3.19.11"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-jooq")
    jooqCodegen("org.postgresql:postgresql")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.flywaydb:flyway-core")
    implementation("org.flywaydb:flyway-database-postgresql")
    implementation("org.springframework:spring-jdbc")
    implementation("com.fasterxml.uuid:java-uuid-generator:5.1.0")
    runtimeOnly("org.postgresql:postgresql")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    testImplementation("org.testcontainers:postgresql:1.20.1")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

val test by testing.suites.existing(JvmTestSuite::class)
tasks.named<Test>("test") {
    useJUnitPlatform {
        excludeTags("integration")
    }
}

tasks.register<Test>("testIntegration") {
    testClassesDirs = files(test.map { it.sources.output.classesDirs })
    classpath = files(test.map { it.sources.runtimeClasspath })
    useJUnitPlatform {
        includeTags("integration")
    }
}

tasks.register<Test>("testAll") {
    testClassesDirs = files(test.map { it.sources.output.classesDirs })
    classpath = files(test.map { it.sources.runtimeClasspath })
    useJUnitPlatform()
}

jooq {
    configuration {
        jdbc {
            driver = "org.postgresql.Driver"
            url = "jdbc:postgresql://localhost:5432/dev"
            user = "root"
            password = "password"
        }
        generator {
            database {
                name = "org.jooq.meta.postgres.PostgresDatabase"
                // TODO 新しいテーブルを作るたびにincludesを増やすのは面倒なのでexcludesで管理したいが、
                // 想像以上に多くのテーブルが読み込まれ、excludesのルールをどのように設定すれば良いかわからなかったので、
                // 現状はincludesで管理している
                includes = "authors|books|authors_books|publication_status"
            }
            generate {}
            target {
                packageName = "com.example.bookmanagementsystem.jooq"
                directory = "${project.rootDir}/src/main/java"
            }
        }
    }
}
