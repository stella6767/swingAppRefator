package com.stella.shooting.view.component

import com.stella.shooting.model.PlayerBullet
import java.awt.Graphics
import javax.swing.JLabel

class PlayerBulletLabel(
    private val bullet: PlayerBullet
) : JLabel(), Runnable {


    override fun run() {
        bullet.move()
    }

    fun fire() {
        Thread(this).start()
        //Thread.sleep(500)
    }


    override fun paintComponent(g: Graphics) {
        g.drawImage(bullet.image, bullet.x.toInt(), bullet.y.toInt(), bullet.width, bullet.height, null)
    }



}