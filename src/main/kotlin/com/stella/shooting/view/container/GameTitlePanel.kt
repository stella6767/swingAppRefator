package com.stella.shooting.view.container

import com.stella.shooting.config.SCREEN_HEIGHT
import com.stella.shooting.config.SCREEN_WIDTH
import java.awt.Graphics
import javax.swing.ImageIcon
import javax.swing.JPanel

class GameTitlePanel(
    private val frame: GameFrame,
) : JPanel() {

    private val titleIcon = ImageIcon("images/GameTitle.gif")

    init {
        layout = null
    }

    override fun paintComponents(g: Graphics) {
        //super.paintComponents(g)
        g.drawImage(titleIcon.image, 0, 0, SCREEN_WIDTH - 15, SCREEN_HEIGHT, 0, 0, 338, 594, this )
    }




}