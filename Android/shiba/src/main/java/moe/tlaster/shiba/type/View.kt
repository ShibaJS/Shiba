package moe.tlaster.shiba.type

import moe.tlaster.shiba.ShibaView


class View(var viewName: String) {
    var parent: ShibaView? = null
    val children: ArrayList<View> = ArrayList()
    val properties: ArrayList<Property> = ArrayList()
    var defaultValue: Any? = null
        internal set
//    var parent: View? = null
//    internal set
}