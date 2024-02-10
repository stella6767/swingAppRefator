package com.stella.shooting.view.container

import com.stella.shooting.config.EnemyKind
import com.stella.shooting.config.PanelName
import com.stella.shooting.config.PlayerKind
import com.stella.shooting.config.toImageIcon
import com.stella.shooting.model.BossUnit
import com.stella.shooting.model.EnemyUnit
import com.stella.shooting.model.PlayerUnit
import com.stella.shooting.view.component.BossUnitLabel

import com.stella.shooting.view.component.EnemyUnitLabel
import com.stella.shooting.view.component.PlayerLabel
import java.awt.Graphics
import java.awt.Image
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import javax.swing.JLabel
import javax.swing.JPanel

class GamePanel(
    private val gameFrame: GameFrame,
    private val playerKind: PlayerKind,
) : JPanel(), Runnable {

    private val playerLabel = createPlayer(playerKind)
    private val player = playerLabel.player

    private var isPlaying: Boolean = true // 게임실행 여부
    private val stageImg: Image = "/images/Stage.png".toImageIcon(this::class.java).image // 배경 이미지
    private val bossStageImg: Image = "/images/vsBossStage.png".toImageIcon(this::class.java).image // 보스 스테이지 이미지
    private val titleImg: Image = "/images/GameTitle.gif".toImageIcon(this::class.java).image

    var stageY: Int = -(stageImg.getHeight(null) - bossStageImg.getHeight(null)) // 배경 이미지의 Y좌표
    var bossStageBY1: Int = -(stageImg.getHeight(null)) // 보스 스테이지 이미지 1의 Y좌표
    var bossStageBY2: Int = -(stageImg.getHeight(null) + bossStageImg.getHeight(null)) // 보스 스테이지 이미지 2의 Y좌표
    var appear: Int = 1 // 적 비행기 출현 위치를 정하기 위해 선언

    private val lifeCount = "/images/LifeCount.png".toImageIcon(this::class.java)
    private val lifeCountLabel1 = JLabel(lifeCount)
    private val lifeCountLabel2 = JLabel(lifeCount)
    private val lifeCountLabel3 = JLabel(lifeCount)


    private var boss: BossUnit? = null


    init {
        add(playerLabel)
        keyListener()
    }


    override fun run() {

        layout = null
        this.add(lifeCountLabel1)
        this.add(lifeCountLabel2)
        this.add(lifeCountLabel3)
        lifeCountLabel1.setBounds(0, 0, 50, 50)
        lifeCountLabel2.setBounds(50, 0, 50, 50)
        lifeCountLabel3.setBounds(100, 0, 50, 50)
        lifeCountLabel1.isVisible = true
        lifeCountLabel2.isVisible = true
        lifeCountLabel3.isVisible = true

        while (isPlaying) {

            stageY++
            bossStageBY1++
            bossStageBY2++

            if (stageY > bossStageImg.getHeight(null)) {
                stageY = bossStageImg.getHeight(null)
                if (bossStageBY1 > (bossStageImg.getHeight(null) - 1)) {
                    bossStageBY1 = -(bossStageImg.getHeight(null) - 1)
                }
                if (bossStageBY2 > (bossStageImg.getHeight(null) - 1)) {
                    bossStageBY2 = -(bossStageImg.getHeight(null) - 1)
                }
            }

            lifeCounting()
            batchEnemy()
            //checkCrush()

            appear++
            repaint()
            Thread.sleep(5)
        }
    }


    private fun lifeCounting() {
        when (player.life) {
            2 -> lifeCountLabel3.isVisible = false
            1 -> lifeCountLabel2.isVisible = false
            0 -> lifeCountLabel1.isVisible = false
        }
        repaint()
    }


    private fun batchEnemy() { // 적기 맵에 배치
//        if (appear == 500 || appear == 1500 || appear == 2500) {
//            EnemyUnitLabel(EnemyUnit(50, 0, EnemyKind.Enemy1), playerLabel, this)
//            EnemyUnitLabel(EnemyUnit(100, -50, EnemyKind.Enemy1), playerLabel, this)
//            EnemyUnitLabel(EnemyUnit(150, -100, EnemyKind.Enemy1), playerLabel, this)
//            EnemyUnitLabel(EnemyUnit(200, -150, EnemyKind.Enemy1), playerLabel, this)
//            EnemyUnitLabel(EnemyUnit(250, -200, EnemyKind.Enemy1), playerLabel, this)
//        }
//        if (appear == 5000) {
//            EnemyUnitLabel(EnemyUnit(-100, 300, EnemyKind.Enemy2), playerLabel, this)
//            EnemyUnitLabel(EnemyUnit(500, 300, EnemyKind.Enemy2), playerLabel, this)
//        }
//
        if (appear == 500 || appear == 1500 || appear == 3500 || appear == 5000 || appear == 6000) {
            EnemyUnitLabel(EnemyUnit(600, -200, EnemyKind.Enemy3), playerLabel, this)
            EnemyUnitLabel(EnemyUnit(0, 0, EnemyKind.Enemy4), playerLabel, this)
        }
//        if (appear == 6000) {
//            EnemyUnitLabel(EnemyUnit(300, -50, EnemyKind.Enemy5), playerLabel, this)
//            EnemyUnitLabel(EnemyUnit(500, -50, EnemyKind.Enemy5), playerLabel, this)
//        }
//
//        if (appear == 7000) {
//            EnemyUnitLabel(EnemyUnit(650, 400, EnemyKind.Enemy6), playerLabel, this)
//        }

//        if (appear == 10000) {
//            BossUnitLabel(BossUnit(0, -300, playerLabel), this)
//        }

        if (appear == 500) {
            //EnemyUnitLabel(EnemyUnit(0, 0, EnemyKind.BOSS), playerLabel, this)
            BossUnitLabel(BossUnit(0, -200, playerLabel, gamePanel = this), this)
            //boss = BossUnit(0, -100, playerLabel, gamePanel = this)
        }

    }


    override fun paintComponent(g: Graphics) {

        g.drawImage(stageImg, 0, stageY, null)
        g.drawImage(bossStageImg, 0, bossStageBY1, null)
        g.drawImage(bossStageImg, 0, bossStageBY2, null)

        //boss?.customPaint(g)
    }


    private fun keyListener() {
        gameFrame.addKeyListener(object : KeyAdapter() {
            override fun keyPressed(e: KeyEvent) {
                when (e.keyCode) {
//                    KeyEvent.VK_1 -> player.setWepponLevelUp(true)
                    KeyEvent.VK_ENTER -> gameFrame.change(PanelName.SELECTAPI)
                    KeyEvent.VK_SPACE -> player.isAttack = true
                    KeyEvent.VK_UP -> player.isUp = true
                    KeyEvent.VK_DOWN -> player.isDown = true
                    KeyEvent.VK_LEFT -> player.isLeft = true
                    KeyEvent.VK_RIGHT -> player.isRight = true
                }
            }

            override fun keyReleased(e: KeyEvent) {
                when (e.keyCode) {
//                    KeyEvent.VK_1 -> playerModel.setWepponLevelUp(false)
                    KeyEvent.VK_SPACE -> player.isAttack = false
                    KeyEvent.VK_UP -> player.isUp = false
                    KeyEvent.VK_DOWN -> player.isDown = false
                    KeyEvent.VK_LEFT -> player.isLeft = false
                    KeyEvent.VK_RIGHT -> player.isRight = false
                }
            }
        })
    }

    private fun createPlayer(playerKind: PlayerKind): PlayerLabel { // 비행기 선택 후 비행기 new add
        return PlayerLabel(PlayerUnit(playerKind), this)
    }

}