package org.shibajs.shiba.type

import org.shibajs.shiba.ShibaView


class View(var viewName: String) {
    var parent: ShibaView? = null
    val children: ArrayList<View> = ArrayList()
    val properties: ArrayList<Property> = ArrayList()
    var defaultValue: Any? = null
        internal set
//    var parent: View? = null
//    internal set
}