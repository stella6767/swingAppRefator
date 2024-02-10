package com.stella.shooting.view.component

import com.stella.shooting.config.SwingComponentBehavior
import com.stella.shooting.config.toImageIcon
import com.stella.shooting.model.BossUnit
import com.stella.shooting.view.container.GamePanel
import java.awt.Graphics
import javax.swing.JLabel

class BossUnitLabel(
    private val boss: BossUnit,
    //private val playerLabel: PlayerLabel,
    val gamePanel: GamePanel,
) : JLabel(), Runnable, SwingComponentBehavior {

    private val explosionIcon = "/images/explosion.gif".toImageIcon(this::class.java)

    init {
        gamePanel.add(this)
        setIcon(boss.icon)
        //isVisible = true
        val thread = Thread(this)
        thread.name = boss.kind.name + this.hashCode()
        thread.start()
    }

    override fun run() {
        while (boss.isLife){
            Thread.sleep(10)
            setComponent()
            boss.createBullets(gamePanel)
        }
    }

    override fun setComponent() {
        setLocation(boss.x, boss.y)
        setSize(boss.width, boss.height)
    }


}