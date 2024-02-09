package com.stella.shooting.view.container

import com.stella.shooting.config.*
import com.stella.shooting.model.PlayerUnit
import com.stella.shooting.view.component.PlayerLabel
import java.awt.Graphics
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JPanel

class SelectPanel(
    private val gameFrame: GameFrame
) : JPanel() {

    private val selectPlaneIcon = "/images/SelectPlane1.png".toImageIcon(this::class.java)

    private val btn1 = JButton("", PlayerKind.PLANE1.playIcon.toImageIcon(this::class.java))
    private val btn2 = JButton("", PlayerKind.PLANE2.playIcon.toImageIcon(this::class.java))
    private val btn3 = JButton("", PlayerKind.PLANE3.playIcon.toImageIcon(this::class.java))

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

        btn1.setBounds(100, 640, 70, 59)
        btn2.setBounds(250, 640, 70, 59)
        btn3.setBounds(400, 640, 70, 59)
        planeImg.setBounds(180, 250, 223, 318)

        this.add(planeImg)
        this.add(btn1)
        this.add(btn2)
        this.add(btn3)


        // 버튼 액션
        btn1.addMouseListener(mouseAdapter(PanelName.INGAME, PlayerKind.PLANE1, btn1))
        btn2.addMouseListener(mouseAdapter(PanelName.INGAME, PlayerKind.PLANE2, btn2))
        btn3.addMouseListener(mouseAdapter(PanelName.INGAME, PlayerKind.PLANE3, btn3))

    }


    /**
     * 마우스 리스너
     */
    private fun mouseAdapter(
        panelName: PanelName,
        playerKind: PlayerKind,
        btn: JButton
    ) = object : MouseAdapter() {

        override fun mousePressed(e: MouseEvent) {
            val player = createPlayer(playerKind)
            gameFrame.change(panelName, player)
        }

        override fun mouseEntered(e: MouseEvent) {
            planeImg.icon = playerKind.detailIcon.toImageIcon(this::class.java)
            btn.setSize(100, 89)
            btn.icon = playerKind.bigPlayIcon.toImageIcon(this::class.java)
        }

        override fun mouseExited(e: MouseEvent) {
            planeImg.icon = null
            btn.setSize(70, 59)
            btn.icon = playerKind.playIcon.toImageIcon(this::class.java)
        }
    }


    override fun paintComponent(g: Graphics) {
        g.drawImage(selectPlaneIcon.image, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, 0, 0, 196, 182, this)
        repaint()
    }

    private fun createPlayer(playerKind: PlayerKind): PlayerLabel { // 비행기 선택 후 비행기 new add
        return PlayerLabel(PlayerUnit(playerKind))
    }

}