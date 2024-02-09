package com.stella.shooting

import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class ShootingApplication

fun main(args: Array<String>) {

    SpringApplicationBuilder()
        .headless(false)
        .sources(ShootingApplication::class.java)
        .run(*args)

}



@Bean
fun swingingSpring() : ApplicationRunner{

     return ApplicationRunner {
         it.run {
             println("cool start")
         }
     }

}