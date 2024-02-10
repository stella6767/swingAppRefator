package com.stella.shooting.view.component

import com.stella.shooting.model.PlayerBullet
import java.awt.Graphics
import javax.swing.JComponent
import javax.swing.JLabel
import kotlin.math.cos
import kotlin.math.sin

class BulletComponent(
    val bullet: PlayerBullet
) : JLabel(), Runnable {

    init {
        icon = bullet.image
        isVisible = true
        Thread(this).start()
    }

    override fun run() {

        while (bullet.isLife) {
            setLocation(bullet.x.toInt(), bullet.y.toInt()) // repaint()
            setSize(bullet.width, bullet.height)
            bullet.move()
            println(bullet.y)
        }
    }




}