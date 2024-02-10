package com.stella.shooting.model

import com.stella.shooting.config.EnemyKind
import com.stella.shooting.config.toImageIcon
import com.stella.shooting.view.component.BulletComponent
import com.stella.shooting.view.component.PlayerLabel
import com.stella.shooting.view.container.GamePanel
import java.awt.Graphics
import java.awt.Image
import java.util.concurrent.Executors
import javax.swing.ImageIcon


class BossUnit(
    override val x: Int,
    override val y: Int,
    private val playerLabel: PlayerLabel,
    override var isLife: Boolean = true,
    val kind: EnemyKind = EnemyKind.BOSS,
    val gamePanel: GamePanel
) : Unit {



    override val life: Int = kind.life

    private val bulletWidth = 30
    private val bulletHeight = 30

    private val bulletImg =
        kind.bulletImg.toImageIcon(this::class, bulletWidth, bulletHeight)

    private var bCount = 0

    override val width: Int = kind.width
    override val height: Int = kind.height
    val icon =
        kind.icon.toImageIcon(this::class, width, height )// 기본 이미지

    val bullets = mutableListOf<BulletComponent>()
    private var virtualExecutor = Executors.newVirtualThreadPerTaskExecutor()

//    init {
//        val thread = Thread(this)
//        thread.start()
//    }

//    override fun run() {
//        while (this.isLife){
//            Thread.sleep(10)
//            createBullets(gamePanel)
//        }
//    }

    fun createBullets(gamePanel: GamePanel) {
        if ((bCount % 200) == 0) {
            for (j in 1..5) {
                createBullet(
                    (180 + (30 * j)).toDouble(),
                    x + 80.0, y + 500.0,
                    0.5, gamePanel
                )
            }
            for (j in 1..5) {
                createBullet(
                    (180 + (30 * j)).toDouble(),
                    x + 480.0, y + 500.0,
                    0.5, gamePanel
                )
            }
        }

        if (bCount % 600 == 0) {
            for (j in 1..7) {
                createBullet(
                    (180 + (25 * j)).toDouble(),
                    x + 280.0, y + 300.0,
                    1.0, gamePanel
                )
            }
        }

        bCount++
    }

    private fun createBullet(
        angel: Double,
        x: Double,
        y: Double,
        speed: Double,
        gamePanel: GamePanel
    ) {
        val bullet = EnemyBullet(
            x, y,
            angel, speed,
            bulletWidth, bulletHeight,
            bulletImg,
            playerLabel = playerLabel
        )
        val bulletComponent = BulletComponent(bullet, bullets)
        gamePanel.add(bulletComponent)
        virtualExecutor.submit(bulletComponent)
        bullets.add(bulletComponent)
    }


    fun customPaint(g: Graphics) {
        g.drawImage(icon.image, x, y, width, height, null)
    }




}