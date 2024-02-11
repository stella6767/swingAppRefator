package com.stella.shooting.model

import com.stella.shooting.config.*
import com.stella.shooting.view.component.BulletComponent
import com.stella.shooting.view.container.GameFrame
import com.stella.shooting.view.container.GamePanel
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.Executors


class PlayerUnit(
    val kind: PlayerKind
) : Unit {

    val icon =
        "/images/Player${kind.name}.png".toImageIcon(this::class.java)// 기본 이미지

    val playerInvincibleIcon =
        "/images/${kind.name}무적.png".toImageIcon(this::class.java) //무적상태시 이미지


    override val width: Int = 70
    override val height: Int = 65
    override var x: Int = (SCREEN_WIDTH / 2) - (width / 2)
    override var y: Int = (SCREEN_HEIGHT - (height * 2))
    override var life: Int = 3
    override var isLife = true //스레드 생명


    var pCount = 0 // 총알 발사 속도
    var isInvincible = false //무적상태
    var isRight: Boolean = false
    var isLeft: Boolean = false
    var isUp: Boolean = false
    var isDown: Boolean = false // is 붙여라
    var isAttack: Boolean = false
    var isCollision: Boolean = false // 플레이어와 적기몸체가 충돌시 체크


    private var weaponLevel = 0
    private var isWeaponLevelUp: Boolean = false

    var bullets =
        CopyOnWriteArrayList<BulletComponent>()

    var executor = Executors.newFixedThreadPool(500)
    private var virtualExecutor = Executors.newVirtualThreadPerTaskExecutor()



    fun keyProcess() {
        move()

    }

    fun fireListner() {

        if (!isInvincible && isAttack && pCount % 20 == 0) {
            //pCount로 이 함수에 약간의 텀을 줌.


            if (weaponLevel == 0) { // 총알 한줄만 발사
                createBullet( (x + 15), (y - 40), 90.0, 2.0)
            }
            if (weaponLevel == 1) { // 총알 2줄 발사
                createBullet( (x + 15), (y - 40), 90.0, 2.0)
                createBullet( (x + 25), (y - 40), 90.0, 2.0)
            }
            if (weaponLevel == 2) { // 총알 3줄 발사
                createBullet( (x + 15), (y - 40), 45.0, 2.0)
                createBullet( (x + 25), (y - 40), 130.0, 2.0)
                createBullet( (x + 35), (y - 40), 0.0, 2.0)
            }



            if (this.pCount == 3150) this.pCount = 0 //초기화
        }



        this.pCount++
    }

    private fun createBullet(
        x: Int, y: Int, angel: Double, speed: Double
    ) {
        val bullet = PlayerBullet(
            kind.bulletImg.toImageIcon(this::class, 20, 20),
            x.toDouble(), y.toDouble(), angel, speed
        )

        val bulletComponent = BulletComponent(bullet, bullets)
        val gameFrame = ApplicationContextProvider.getBean("gameFrame") as GameFrame
        gameFrame.panel.add(bulletComponent)
        virtualExecutor.submit(bulletComponent)
        bullets.add(bulletComponent)
    }


    private fun move() {
        if (isUp && y > 0) {
            y--
        }
        if (isDown && y < SCREEN_HEIGHT) {
            y++
        }
        if (isLeft && x > 0) {
            x--
        }
        if (isRight && x < SCREEN_WIDTH - 50) {
            x++
        }
    }


    fun weaponLevelUp(isWeaponLevelUp: Boolean) {
        this.isWeaponLevelUp = isWeaponLevelUp
        if (isWeaponLevelUp && weaponLevel < 3) {
            weaponLevel += 1
            println("무기 레벨 : $weaponLevel")
            if (weaponLevel == 3) {
                weaponLevel = 0
            }
        }
    }


    fun respon() {
        this.x = 200
        this.y = 600
        Thread.sleep(1000)

    }





}