package com.stella.shooting.view.container

import com.stella.shooting.config.toImageIcon
import com.stella.shooting.model.PlayerPlane
import com.stella.shooting.view.component.PlayerLabel
import java.awt.Graphics
import java.awt.Image
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import java.util.*
import javax.swing.ImageIcon
import javax.swing.JLabel
import javax.swing.JPanel

class GamePanel(
    private val gameFrame: GameFrame,
    private val player: PlayerLabel
) : JPanel(), Runnable {

    private val playerModel = player.player

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

    //var enemyUnits = Vector<EnemyUnit>() // 적 유닛을 모아놓을 배열

    init {
        this.add(player)

        val thread = Thread(player)
        thread.name = "player"
        thread.start()


        keyListener()
    }



    override fun run() {

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

            layout = null
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

            //lifeCounting()
            batchEnemy()
            //crushBorder()
            appear++

            repaint()
            Thread.sleep(10)
        }
    }

//    private fun lifeCounting() {
//        if (player.life == 2) {
//            lifeCountLabel3.isVisible = false
//        } else if (player.life === 1) {
//            lifeCountLabel2.isVisible = false
//        } else {
//            lifeCountLabel1.isVisible = false
//        }
//        repaint()
//    }


    fun batchEnemy() { // 적기 맵에 배치


    }

//    fun crushBorder() { // 벽에 충돌하는 조건함수 >> Map 스레드 안에 적용
//
//        if (player.x <= 0) {
//            player.x = 0
//        } else if (player.x >= 550) {
//            player.x = 550
//        }
//        if (player.y <= 0) {
//            player.y = 0
//
//        } else if (player.y >= 720) {
//            player.y = 720
//        }
//        repaint()
//    }


    override fun paintComponent(g: Graphics) {

        g.drawImage(stageImg, 0, stageY, null)
        g.drawImage(bossStageImg, 0, bossStageBY1, null)
        g.drawImage(bossStageImg, 0, bossStageBY2, null)

        //player.playerUpdate(g)

//        for (i in enemyUnits.indices) { // null이 아니면 그려라
//            if (enemyUnits.get(i) != null) {
//                enemyUnits.get(i).enemyUpdate(g)
//            }
//        }
//
//        if (player != null) {
//            player.playerUpdate(g)
//        }
//
//        if (boss != null) {
//            boss.bossUpdate(g)
//        }

        repaint()
    }


    private fun keyListener() {
        gameFrame.addKeyListener(object : KeyAdapter() {
            override fun keyPressed(e: KeyEvent) {
                when (e.keyCode) {
//                    KeyEvent.VK_1 -> player.setWepponLevelUp(true)
//                    KeyEvent.VK_ENTER -> change(PanelName.SELECTAPI)
//                    KeyEvent.VK_SPACE -> player.setAttack(true)
                    KeyEvent.VK_UP -> playerModel.isUp =true
                    KeyEvent.VK_DOWN -> playerModel.isDown = true
                    KeyEvent.VK_LEFT -> playerModel.isLeft = true
                    KeyEvent.VK_RIGHT -> playerModel.isRight = true
                }
            }

            override fun keyReleased(e: KeyEvent) {
                when (e.keyCode) {
//                    KeyEvent.VK_1 -> playerModel.setWepponLevelUp(false)
//                    KeyEvent.VK_SPACE -> playerModel.setAttack(false)
                    KeyEvent.VK_UP -> playerModel.isUp = false
                    KeyEvent.VK_DOWN -> playerModel.isDown = false
                    KeyEvent.VK_LEFT -> playerModel.isLeft = false
                    KeyEvent.VK_RIGHT -> playerModel.isRight = false
                }
            }
        })
    }





}