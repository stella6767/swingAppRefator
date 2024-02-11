package com.stella.shooting.config

import com.stella.shooting.view.container.GameFrame
import org.springframework.boot.ApplicationRunner
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import javax.swing.JFrame
import javax.swing.JTable
import javax.swing.SwingUtilities


@Configuration
class Config {

    @Bean
    fun gameFrame(): GameFrame {
        return GameFrame()
    }

    @Bean
    fun swingingSpring() : ApplicationRunner {
        return ApplicationRunner {
            it.run {
                SwingUtilities.invokeLater {
                    gameFrame()
                }
            }
        }
    }



}

@Component
object ApplicationContextProvider : ApplicationContextAware {

    private var applicationContext: ApplicationContext? = null
    override fun setApplicationContext(context: ApplicationContext) {
        applicationContext = context
    }
    fun getBean(beanName: String): Any {
        return applicationContext!!.getBean(beanName)
    }
}