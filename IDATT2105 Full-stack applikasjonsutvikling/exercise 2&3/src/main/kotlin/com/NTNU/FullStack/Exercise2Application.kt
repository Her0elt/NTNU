package com.NTNU.FullStack

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.DriverManagerDataSource
import java.util.*
import javax.sql.DataSource


@SpringBootApplication
class Exercise2Application

fun main(args: Array<String>) {
	SpringApplicationBuilder(Exercise2Application::class.java)
			.properties(props())
			.build()
			.run(*args)
}

private fun props(): Properties {
	val properties = Properties()
	try {
		properties.setProperty("spring.datasource.username", System.getenv("DB_USERNAME"))
		properties.setProperty("spring.datasource.password", System.getenv("DB_PASSWORD"))
		properties.setProperty("spring.datasource.url", System.getenv("DB_URL"))
	} catch (e: Exception) {
		println("\n RIP \n")
	}
	return properties
}

