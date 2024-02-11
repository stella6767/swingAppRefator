package com.stella.shooting.model

import com.stella.shooting.config.ApplicationContextProvider
import com.stella.shooting.config.EnemyKind
import com.stella.shooting.config.toImageIcon
import com.stella.shooting.view.component.BulletComponent
import com.stella.shooting.view.component.PlayerLabel
import com.stella.shooting.view.container.GameFrame
import com.stella.shooting.view.container.GamePanel
import java.util.concurrent.Executors

class EnemyUnit(
    override var x: Int,
    override var y: Int,
    val kind: EnemyKind,
    override var isLife: Boolean = true,
) : Unit {

    override val width: Int = kind.width
    override val height: Int = kind.height
    //var isCollision: Boolean = false
    override var life: Int = kind.life
    var image = kind.icon.toImageIcon(this::class, kind.width, kind.height)

    var bcount: Int = 0 // 총알 장전간격
    val bullets = mutableListOf<BulletComponent>()

    private var virtualExecutor = Executors.newVirtualThreadPerTaskExecutor()


    val gameFrame = ApplicationContextProvider.getBean("gameFrame") as GameFrame

    fun move() {
        kind.moveFunc(this)
    }




    fun createBulletsByKind(playerLabel: PlayerLabel){
        when(kind){
            EnemyKind.BOSS ->createBossBullets(playerLabel)
            else -> createBullets( playerLabel)
        }
    }


    private fun createBossBullets(playerLabel: PlayerLabel) {
        if ((bcount % 200) == 0) {
            for (j in 1..5) {
                createBossBullet(
                    (180 + (30 * j)).toDouble(),
                    x + 80.0, y + 400.0,
                    0.5, playerLabel
                )
            }
            for (j in 1..5) {
                createBossBullet(
                    (180 + (30 * j)).toDouble(),
                    x + 480.0, y + 400.0,
                    0.5,  playerLabel
                )
            }
        }

        if (bcount % 600 == 0) {
            for (j in 1..7) {
                createBossBullet(
                    (180 + (25 * j)).toDouble(),
                    x + 280.0, y + 200.0,
                    1.0,  playerLabel
                )
            }
        }

        bcount++
    }



    private fun createBullets(playerLabel: PlayerLabel) {

        if (kind.bulletImg != "" && ((bcount % kind.bulletInterval) == 0) ) {
            val bullet = EnemyBullet(
                x + 20.0, y + 40.0,
                300.0, 2.0,
                15, 20,
                kind.bulletImg.toImageIcon(this::class, 15, 20),
                playerLabel = playerLabel
            )
            val bulletComponent = BulletComponent(bullet, bullets)


            gameFrame.panel.add(bulletComponent)

            virtualExecutor.submit(bulletComponent)
            bullets.add(bulletComponent)
            if (bcount == 5000) bcount = 0
        }

        bcount ++
    }



    private fun createBossBullet(
        angel: Double,
        x: Double,
        y: Double,
        speed: Double,
        playerLabel: PlayerLabel
    ) {
        val bullet = EnemyBullet(
            x, y,
            angel, speed,
            30, 30,
            kind.bulletImg.toImageIcon(this::class, 30, 30),
            playerLabel = playerLabel
        )
        val bulletComponent = BulletComponent(bullet, bullets)
        gameFrame.panel.add(bulletComponent)
        virtualExecutor.submit(bulletComponent)
        bullets.add(bulletComponent)
    }


}