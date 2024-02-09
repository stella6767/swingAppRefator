package com.stella.shooting.view.container

import com.stella.shooting.model.PlayerPlane
import java.awt.Graphics
import java.awt.Image
import java.util.*
import javax.swing.ImageIcon
import javax.swing.JLabel
import javax.swing.JPanel

class GamePanel(
    private val gameFrame: GameFrame,
    private val player: PlayerPlane
) : JPanel(), Runnable {

    private var isPlaying: Boolean = false // 게임실행 여부

    private val stageIcon = ImageIcon("images/Stage.png") // 배경 이미지 아이콘
    private val stageImg: Image = stageIcon.image // 배경 이미지

    private val bossStageIcon = ImageIcon("images/vsBossStage.png") // 보스 스테이지 배경 아이콘
    private val bossStageImg: Image = bossStageIcon.image // 보스 스테이지 이미지

    private val titleIcon = ImageIcon("images/GameTitle.gif")
    private val titleImg: Image = titleIcon.image

    var stageY: Int = -(stageImg.getHeight(null) - bossStageImg.getHeight(null)) // 배경 이미지의 Y좌표
    var bossStageBY1: Int = -(stageImg.getHeight(null)) // 보스 스테이지 이미지 1의 Y좌표
    var bossStageBY2: Int = -(stageImg.getHeight(null) + bossStageImg.getHeight(null)) // 보스 스테이지 이미지 2의 Y좌표
    var appear: Int = 1 // 적 비행기 출현 위치를 정하기 위해 선언

    private val lifeCount = ImageIcon("images/LifeCount.png")
    private val lifeCountLabel1 = JLabel(lifeCount)
    private val lifeCountLabel2 = JLabel(lifeCount)
    private val lifeCountLabel3 = JLabel(lifeCount)

    //var enemyUnits = Vector<EnemyUnit>() // 적 유닛을 모아놓을 배열


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

            lifeCounting()
            batchEnemy()
            crushBorder()
            appear++

            repaint()
            Thread.sleep(10)
        }
    }

    private fun lifeCounting() {
        if (player.life == 2) {
            lifeCountLabel3.isVisible = false
        } else if (player.life === 1) {
            lifeCountLabel2.isVisible = false
        } else {
            lifeCountLabel1.isVisible = false
        }
        repaint()
    }


    fun batchEnemy() { // 적기 맵에 배치


    }

    fun crushBorder() { // 벽에 충돌하는 조건함수 >> Map 스레드 안에 적용

        if (player.x <= 0) {
            player.x = 0
        } else if (player.x >= 550) {
            player.x = 550
        }
        if (player.y <= 0) {
            player.y = 0

        } else if (player.y >= 720) {
            player.y = 720
        }
        repaint()
    }


    override fun paintComponents(g: Graphics) {

        g.drawImage(stageImg, 0, stageY, null)
        g.drawImage(bossStageImg, 0, bossStageBY1, null)
        g.drawImage(bossStageImg, 0, bossStageBY2, null)

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


}