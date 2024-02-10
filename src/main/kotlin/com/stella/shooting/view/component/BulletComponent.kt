package com.stella.shooting.view.component

import com.stella.shooting.config.SwingComponentBehavior
import com.stella.shooting.model.Bullet
import com.stella.shooting.model.PlayerBullet
import java.awt.Graphics
import javax.swing.JComponent
import javax.swing.JLabel
import kotlin.math.cos
import kotlin.math.sin

class BulletComponent(
    val bullet: Bullet
) : JLabel(), Runnable, SwingComponentBehavior {

    init {
        icon = bullet.image
        isVisible = true
        Thread(this).start()
    }

    override fun run() {
        while (bullet.isLife) {
            setComponent()
            bullet.move()
        }
    }

    override fun setComponent() {
        setLocation(bullet.x.toInt(), bullet.y.toInt()) // repaint()
        setSize(bullet.width, bullet.height)
    }


}