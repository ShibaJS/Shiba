package moe.tlaster.shiba

/**
 * Created by SE on 3/5.
 */
enum class Gravity {
    Start,
    End,
    Top,
    Bottom
}

enum class Orientation {
    Vertical,
    Horizontal
}

data class Thickness(val top: Float, val left: Float, val right: Float, val bottom: Float)

open class View {
    var width = Float.NaN
    var height = Float.NaN
    var gravity = Gravity.Start
    var margin = Thickness(0f, 0f, 0f, 0f)
    var padding = Thickness(0f, 0f, 0f, 0f)
    var isEnabled = true
    var backgroundColor = ""
}

open class ViewGroup : View() {
    var children = arrayListOf<View>()
}

open class Text : View() {
    var content = ""
    var textColor = ""
}

open class Switch : View() {
    var checked = false
}

open class check : View() {
    var checked = false
}

open class StackPanel : ViewGroup() {
    var orientation = Orientation.Vertical
}

open class Grid : ViewGroup() {

}

open class Edit : View() {
    var content = ""
    var textColor = ""
}

open class Image : View() {
    var source = ""
}