package com.stella.shooting.view.component

import com.stella.shooting.config.SwingComponentBehavior
import com.stella.shooting.model.Bullet
import javax.swing.JLabel

class BulletComponent(
    val bullet: Bullet,
    val bullets: MutableList<BulletComponent>
) : JLabel(), Runnable, SwingComponentBehavior {

    init {
        icon = bullet.image
        isVisible = true
    }

    override fun run() {
        while (bullet.isLife) {
            setComponent()
            bullet.move()
        }
        bullets.remove(this)
        //println("지워버리자")
    }

    override fun setComponent() {
        setLocation(bullet.x.toInt(), bullet.y.toInt()) // repaint()
        setSize(bullet.width, bullet.height)
    }


}