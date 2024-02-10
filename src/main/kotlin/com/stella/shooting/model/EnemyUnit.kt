package com.stella.shooting.model

import com.stella.shooting.config.EnemyKind
import com.stella.shooting.config.toImageIcon
import com.stella.shooting.view.component.BulletComponent
import com.stella.shooting.view.container.GamePanel
import javax.swing.ImageIcon

class EnemyUnit(
    override var x: Int,
    override var y: Int,
    val kind: EnemyKind,
    override var isLife: Boolean = true,
) : Unit {

    override val width: Int = kind.width
    override val height: Int = kind.height
    var isCollision: Boolean = false
    override var life: Int = kind.life
    var image: ImageIcon = kind.icon.toImageIcon(this::class.java)

    var bcount: Int = 0 // 총알 장전간격
    val bullets = mutableListOf<EnemyBullet>()




    fun move() {
        kind.moveFunc(this)
    }

    fun explosion(image: ImageIcon) {
        if (this.isCollision || this.life < 1) {
            this.image = image
            this.isLife = false
            Thread.sleep(500)
            this.y = 1000 // 맵 바깥으로 적 던짐
        }
    }


    fun createBullets(gamePanel: GamePanel) {

        if (kind.bulletImg != "" && ((bcount % kind.bulletInterval) == 0) ) {
            val bullet = EnemyBullet(
                x + 20.0, y + 40.0,
                300.0, 2.0,
                15, 20,
                kind.bulletImg.toImageIcon(this::class.java)
            )
            val bulletComponent = BulletComponent(bullet)
            gamePanel.add(bulletComponent)
            bullets.add(bullet)
            if (bcount == 5000) bcount = 0
        }

        bcount ++

    }


}