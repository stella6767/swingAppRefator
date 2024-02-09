package com.stella.shooting.view.container

import com.stella.shooting.config.PanelName
import com.stella.shooting.config.PlayerKind
import com.stella.shooting.config.SCREEN_HEIGHT
import com.stella.shooting.config.SCREEN_WIDTH
import com.stella.shooting.model.PlayerPlane
import com.stella.shooting.view.component.PlayerLabel
import java.awt.Graphics
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.ImageIcon
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JPanel

class SelectPanel(
    private val gameFrame: GameFrame
) : JPanel() {

    private val selectPlaneIcon = ImageIcon("images/SelectPlane1.png")

    private val btn1 = JButton("", PlayerKind.PLANE1.playIcon)
    private val btn2 = JButton("", PlayerKind.PLANE2.playIcon)
    private val btn3 = JButton("", PlayerKind.PLANE3.playIcon)
    private val planeImg = JLabel("")

    init {
        layout = null

        // 버튼 테두리 없음
        btn1.isBorderPainted = false
        btn2.isBorderPainted = false
        btn3.isBorderPainted = false

        // 버튼 채우기 없음
        btn1.isContentAreaFilled = false
        btn2.isContentAreaFilled = false
        btn3.isContentAreaFilled = false

        // 버튼 투명
        btn1.isOpaque = false
        btn2.isOpaque = false
        btn3.isOpaque = false

        // 버튼 액션
        btn1.addMouseListener(mouseAdapter(PanelName.INGAME, PlayerKind.PLANE1))
        btn2.addMouseListener(mouseAdapter(PanelName.INGAME, PlayerKind.PLANE2))
        btn3.addMouseListener(mouseAdapter(PanelName.INGAME, PlayerKind.PLANE3))

    }


    /**
     * 마우스 리스너
     */
    private fun mouseAdapter(
        panelName: PanelName,
        playerKind: PlayerKind
    ) = object : MouseAdapter() {

        override fun mousePressed(e: MouseEvent) {
            gameFrame.change(panelName)
            createPlayer(playerKind)
        }

        override fun mouseEntered(e: MouseEvent) {
            planeImg.icon = playerKind.detailIcon
            btn1.setSize(100, 89)
            btn1.icon = playerKind.bigPlayIcon
        }

        override fun mouseExited(e: MouseEvent) {
            planeImg.icon = null
            btn1.setSize(70, 59)
            btn1.icon = playerKind.playIcon
        }
    }


    override fun paintComponents(g: Graphics) {
        g.drawImage(selectPlaneIcon.image, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, 0, 0, 196, 182, this)
        repaint()
    }


    private fun createPlayer(playerKind: PlayerKind) { // 비행기 선택 후 비행기 new add

        val playerPlane = PlayerPlane(
            playerIcon = ImageIcon("images/Player${playerKind.name}.png"),
            playerInvincibleIcon = ImageIcon("images/" + playerKind.name + "무적.png")
        )
        val playerLabel = PlayerLabel(playerPlane)
        this.add(playerLabel)
    }


}