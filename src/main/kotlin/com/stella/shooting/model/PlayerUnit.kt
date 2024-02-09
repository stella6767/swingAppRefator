package com.stella.shooting.model

import com.stella.shooting.config.PlayerKind
import com.stella.shooting.config.SCREEN_HEIGHT
import com.stella.shooting.config.SCREEN_WIDTH
import com.stella.shooting.config.toImageIcon
import java.util.*

class PlayerUnit(
    val kind: PlayerKind
) : Unit {

    val playerIcon =
        "/images/Player${kind.name}.png".toImageIcon(this::class.java)// 기본 이미지
    val playerInvincibleIcon =
        "/images/${kind.name}무적.png".toImageIcon(this::class.java) //무적상태시 이미지

    private val isInvincible = false //무적상태
    override val width: Int = 70
    override val height: Int = 65
    override var x: Int = (SCREEN_WIDTH / 2) - (width / 2)
    override var y: Int = (SCREEN_HEIGHT - (height * 2))
    override val life: Int = 3

    var pCount = 0 // 총알 발사 속도


    var isLife = true //스레드 생명
    var isRight: Boolean = false
    var isLeft: Boolean = false
    var isUp: Boolean = false
    var isDown: Boolean = false // is 붙여라
    var isAttack: Boolean = false

    private var weaponLevel = 0
    private var isWeaponLevelUp: Boolean = false

    var bullets = Collections.synchronizedList(mutableListOf<Bullet>())

    fun gameOver() {
        if (life < 1) {
            isLife = false
        }
    }

    fun keyProcess() {
        move()
        if (isAttack) {
            val bullet = PlayerBullet(
                kind.bulletImg.toImageIcon(this::class.java).image,
                (x + 20).toDouble(), (y - 40).toDouble(), 90.0, 2.0
            )
            bullets.add(bullet)
        }
    }

    fun playerAttackProcess() {
        for (bullet in bullets) {
            bullet.fire()
        }
    }


    private fun move() {
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


    fun weaponLevelUp(isWeaponLevelUp: Boolean) {
        this.isWeaponLevelUp = isWeaponLevelUp
        if (isWeaponLevelUp && weaponLevel < 6) {
            weaponLevel += 1
            println("무기 레벨 : $weaponLevel")
            if (weaponLevel == 5) {
                weaponLevel = 0
            }
        }
    }


}