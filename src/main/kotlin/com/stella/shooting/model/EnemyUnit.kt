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
    var image = kind.icon.toImageIcon(this::class, kind.width, kind.height)

    var bcount: Int = 0 // 총알 장전간격
    val bullets = mutableListOf<BulletComponent>()

    private var virtualExecutor = Executors.newVirtualThreadPerTaskExecutor()


    fun move() {
        kind.moveFunc(this)
    }




    fun createBulletsByKind(gamePanel: GamePanel, playerLabel: PlayerLabel){

        when(kind){
            EnemyKind.BOSS ->createBossBullets(gamePanel, playerLabel)
            else -> createBullets(gamePanel, playerLabel)
        }
    }


    private fun createBossBullets(gamePanel: GamePanel, playerLabel: PlayerLabel) {
        if ((bcount % 200) == 0) {
            for (j in 1..5) {
                createBullet(
                    (180 + (30 * j)).toDouble(),
                    x + 80.0, y + 600.0,
                    0.5, gamePanel,playerLabel
                )
            }
            for (j in 1..5) {
                createBullet(
                    (180 + (30 * j)).toDouble(),
                    x + 480.0, y + 600.0,
                    0.5, gamePanel, playerLabel
                )
            }
        }

        if (bcount % 600 == 0) {
            for (j in 1..7) {
                createBullet(
                    (180 + (25 * j)).toDouble(),
                    x + 280.0, y + 400.0,
                    1.0, gamePanel, playerLabel
                )
            }
        }

        bcount++
    }



    private fun createBullets(gamePanel: GamePanel, playerLabel: PlayerLabel) {

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



    private fun createBullet(
        angel: Double,
        x: Double,
        y: Double,
        speed: Double,
        gamePanel: GamePanel,
        playerLabel: PlayerLabel
    ) {
        val bullet = EnemyBullet(
            x, y,
            angel, speed,
            10, 10,
            kind.bulletImg.toImageIcon(this::class, 10, 10),
            playerLabel = playerLabel
        )
        val bulletComponent = BulletComponent(bullet, bullets)
        gamePanel.add(bulletComponent)
        virtualExecutor.submit(bulletComponent)
        bullets.add(bulletComponent)
    }


}