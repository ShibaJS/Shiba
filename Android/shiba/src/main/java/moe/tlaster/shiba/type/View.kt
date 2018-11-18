package moe.tlaster.shiba.type


final class View(var viewName: ShibaToken) {
    val children: ArrayList<View> = ArrayList()
    val properties: ArrayList<Property> = ArrayList()
    var defaultValue: Any? = null
    internal set
    var parent: View? = null
    internal set
}