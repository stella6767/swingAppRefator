package com.stella.shooting.model

import com.stella.shooting.config.SCREEN_HEIGHT
import com.stella.shooting.config.SCREEN_WIDTH
import com.stella.shooting.config.UnitProperty
import javax.swing.ImageIcon

class PlayerPlane(
    val playerIcon: ImageIcon, // 기본 이미지
    val playerInvincibleIcon: ImageIcon, //무적상태시 이미지
) : UnitProperty, Runnable {

    private val isInvincible = false //무적상태
    override val width: Int = 70
    override val height: Int = 65
    override var x: Int = (SCREEN_WIDTH / 2) - (width / 2)
    override var y: Int = (SCREEN_HEIGHT - (height * 2))
    override val life: Int = 3

    private var pCount = 0 // 총알 발사 속도
    private val weaponLevel = 0

    private var islife = true //스레드 생명
    var isRight: Boolean = false
    var isLeft: Boolean = false
    var isUp: Boolean = false
    var isDown: Boolean = false // is 붙여라
    var isAttack: Boolean = false
    var isWepponLevelUp: Boolean = false


    override fun run() {
        while (islife) {
            Thread.sleep(10)
            gameOver()
            keyProcess()
//            playerAttackProcess()
//            PlayerBullet()
//            setLocation(x, y) // repaint()
//            setSize(width, height)
            pCount++ // 1씩 늘어난다
        }
    }


    private fun gameOver() {
        if (life < 1) {
            islife = false
        }
    }

    private fun keyProcess() {
        if (!isInvincible && isAttack && pCount % 30 == 0) { // 총알의 발사 속도를 조절
            if (isUp) {
                y--
            }
            if (isDown) {
                y++
            }
            if (isLeft) {
                x--
            }
            if (isRight) {
                x++
            }
        }

    }



}