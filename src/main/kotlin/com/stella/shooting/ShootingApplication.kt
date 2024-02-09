package com.stella.shooting

import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import javax.swing.JFrame
import javax.swing.JTable
import javax.swing.SwingUtilities

@SpringBootApplication
class ShootingApplication

fun main(args: Array<String>) {

    SpringApplicationBuilder()
        .headless(false)
        .sources(ShootingApplication::class.java)
        .run(*args)

}



