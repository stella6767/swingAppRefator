package com.stella.shooting.model

import com.stella.shooting.config.EnemyKind
import com.stella.shooting.config.toImageIcon
import com.stella.shooting.view.component.BulletComponent
import com.stella.shooting.view.component.PlayerLabel
import com.stella.shooting.view.container.GamePanel
import java.util.concurrent.Executors
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
    var image: ImageIcon = kind.icon.toImageIcon(this::class, kind.width, kind.height)

    var bcount: Int = 0 // 총알 장전간격
    val bullets = mutableListOf<BulletComponent>()

    private var virtualExecutor = Executors.newVirtualThreadPerTaskExecutor()


    fun move() {
        kind.moveFunc(this)
    }



    fun createBullets(gamePanel: GamePanel, playerLabel: PlayerLabel) {

        if (kind.bulletImg != "" && ((bcount % kind.bulletInterval) == 0) ) {

            val bullet = EnemyBullet(
                x + 20.0, y + 40.0,
                300.0, 2.0,
                15, 20,
                kind.bulletImg.toImageIcon(this::class, kind.width, kind.height),
                playerLabel = playerLabel
            )

            val bulletComponent = BulletComponent(bullet, bullets)
            gamePanel.add(bulletComponent)
            virtualExecutor.submit(bulletComponent)
            bullets.add(bulletComponent)
            if (bcount == 5000) bcount = 0
        }

        bcount ++

    }


}