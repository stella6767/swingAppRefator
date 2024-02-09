//package com.stella.shooting.view.container
//
//import com.stella.shooting.config.PlayerKind
//import com.stella.shooting.config.SCREEN_HEIGHT
//import com.stella.shooting.config.SCREEN_WIDTH
//import java.awt.Graphics
//import javax.swing.ImageIcon
//import javax.swing.JPanel
//
//class SelectPanel(
//    private val gameFrame: GameFrame
//) : JPanel() {
//
//    private val player1Icon = ImageIcon("images/PlayerPlane1.png")
//    private val player2Icon = ImageIcon("images/PlayerPlane2.png")
//    private val player3Icon = ImageIcon("images/PlayerPlane3.png")
//
//    private val bigPlayer1Icon = ImageIcon("images/BigPlane1.png")
//    private val bigPlayer2Icon = ImageIcon("images/BigPlane2.png")
//    private val bigPlayer3Icon = ImageIcon("images/BigPlane3.png")
//
//    private val planeDetailIcon1 = ImageIcon("images/PlaneDetailImg1.png")
//    private val planeDetailIcon2 = ImageIcon("images/PlaneDetailImg2.png")
//    private val planeDetailIcon3 = ImageIcon("images/PlaneDetailImg3.png")
//
//    private val selectPlaneIcon = ImageIcon("images/SelectPlane1.png")
//
//    init {
//        layout = null
//    }
//
//    override fun paintComponents(g: Graphics) {
//        g.drawImage(selectPlaneIcon.image, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, 0, 0, 196, 182, this)
//        repaint()
//    }
//
//
//    private fun batch(playerKind: PlayerKind) { // 비행기 선택 후 비행기 new add
//
////        when(playerKind){
////            PlayerKind.PLANE1 -> {
////
////            }
////            PlayerKind.PLANE2 -> {
////
////            }
////            PlayerKind.PLANE3 -> {
////
////            }
////        }
//
//        gameFrame.player = PlayerPlane(gameFrame, "PLANE1")
//        gameFrame.gamePanel.add(gameFrame.player)
//
//
//        if (playerKind == PlayerPlaneKind.PLAYERPLANE1) {
//            gameFrame.player = PlayerPlane(gameFrame, "PLANE1")
//            gameFrame.gamePanel.add(gameFrame.player)
//        } else if (playerKind == PlayerPlaneKind.PLAYERPLANE2) {
//            gameFrame.player = PlayerPlane(gameFrame, "PLANE2")
//            gameFrame.gamePanel.add(gameFrame.player)
//        } else if (playerKind == PlayerPlaneKind.PLAYERPLANE3) {
//            gameFrame.player = PlayerPlane(gameFrame, "PLANE3")
//            gameFrame.gamePanel.add(gameFrame.player)
//        }
//    }
//
//
//    private fun createPlayer(){
//
//
//
//    }
//
//
//}