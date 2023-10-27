import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.1.5"
	id("org.flywaydb.flyway") version "9.22.1"
	id("nu.studer.jooq") version "8.2.1"
	id("io.spring.dependency-management") version "1.1.3"
	kotlin("jvm") version "1.8.22"
	kotlin("plugin.spring") version "1.8.22"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	api("org.jooq:jooq:3.18.6")
	implementation("org.springframework.boot:spring-boot-starter-jooq")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.flywaydb:flyway-core")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	runtimeOnly("org.postgresql:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

val dbUrl: String = System.getenv("DB_URL") ?: "jdbc:postgresql://localhost:5432/crud"
val dbUser: String = System.getenv("DB_USER") ?: "postgres"
val dbPassword: String = System.getenv("DB_PASSWORD") ?: "root"

flyway {
	url = dbUrl
	user = dbUser
	password = dbPassword
	baselineOnMigrate = true
	schemas = arrayOf("crud")
	locations = arrayOf("filesystem:src/main/resources/db/migration")
	placeholderReplacement = false
	outOfOrder = true
}

jooq {
	version.set("3.18.5")
	edition.set(nu.studer.gradle.jooq.JooqEdition.OSS)

	configurations {
		create("orient") {
			generateSchemaSourceOnCompilation.set(true)

			jooqConfiguration.apply {
				logging = org.jooq.meta.jaxb.Logging.WARN
				jdbc.apply {
					driver = "org.postgresql.Driver"
					url = dbUrl
					user = dbUser
					password = dbPassword
				}

				generator.apply {
					name = "org.jooq.codegen.DefaultGenerator"
					database.apply {
						name = "org.jooq.meta.postgres.PostgresDatabase"
						schemata.addAll(
								listOf(
										org.jooq.meta.jaxb.SchemaMappingType().withInputSchema("orient"),
								)
						)
					}
					strategy.name = "org.jooq.codegen.example.JPrefixGeneratorStrategy"
					generate.apply {
						isRecords = true
						isImmutablePojos = true
						isFluentSetters = true
					}
					target.apply {
						packageName = "uz.orient.db.schema"
						directory = "$buildDir/generated-sources/jooq"
						encoding = "UTF-8"
					}
				}
			}
		}
	}
}
