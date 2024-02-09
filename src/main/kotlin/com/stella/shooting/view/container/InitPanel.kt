package com.stella.shooting.view.container

import com.stella.shooting.config.SCREEN_HEIGHT
import com.stella.shooting.config.SCREEN_WIDTH
import org.springframework.core.io.ClassPathResource
import java.awt.Graphics
import javax.swing.ImageIcon
import javax.swing.JLabel
import javax.swing.JPanel

class InitPanel(
) : JPanel() {


    val resource =
        this::class.java.getResource("/images/GameTitle.gif")

    private val titleIcon = ImageIcon(resource)

    init {
        layout = null
    }

    override fun paintComponent(g: Graphics) {
        g.drawImage(titleIcon.image, 0, 0, SCREEN_WIDTH - 15, SCREEN_HEIGHT, 0, 0, 338, 594, this )
    }




}