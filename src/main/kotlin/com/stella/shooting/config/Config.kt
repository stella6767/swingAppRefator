package com.stella.shooting.config

import com.stella.shooting.view.container.GameFrame
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.swing.JFrame
import javax.swing.JTable
import javax.swing.SwingUtilities


@Configuration
class Config {
    @Bean
    fun swingingSpring() : ApplicationRunner {
        return ApplicationRunner {
            it.run {
                SwingUtilities.invokeLater {
                    GameFrame()
                }
            }
        }

    }

}