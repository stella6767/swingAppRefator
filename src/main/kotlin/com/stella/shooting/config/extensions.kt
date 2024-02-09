package com.stella.shooting.config

import com.stella.shooting.view.container.SelectPanel
import javax.swing.ImageIcon


fun String.toImageIcon(java: Class<*>): ImageIcon {
    return ImageIcon(java.getResource(this))
}