package com.stella.shooting.view.component

import com.stella.shooting.model.PlayerPlane
import java.awt.Graphics
import javax.swing.JLabel

class PlayerLabel(
    private val player: PlayerPlane
) : JLabel() {
    init {
        icon = player.playerIcon

    }

//    override fun paintComponents(g: Graphics) {
//
//        g.drawImage(player.stageImg, 0, stageY, null)
//        g.drawImage(player.bossStageImg, 0, bossStageBY1, null)
//        g.drawImage(player.bossStageImg, 0, bossStageBY2, null)
//        repaint()
//    }



}