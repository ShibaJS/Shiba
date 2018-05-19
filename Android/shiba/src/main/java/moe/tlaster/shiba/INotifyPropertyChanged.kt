package moe.tlaster.shiba

interface INotifyPropertyChanged {
    var propertyChanged: PropertyChanged?
}

class PropertyChanged {
    private val listeners = ArrayList<(sender: Any, propertyName: String) -> Unit>()
    fun invoke(sender: Any, propertyName: String) {
        listeners.forEach { it.invoke(sender, propertyName) }
    }

    operator fun plusAssign(propertyChanged: (Any, String) -> Unit) {
        listeners.add(propertyChanged)
    }

    operator fun minusAssign(propertyChanged: (Any, String) -> Unit) {
        listeners.remove(propertyChanged)
    }
}

@Target(AnnotationTarget.PROPERTY, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Binding(val name: String)