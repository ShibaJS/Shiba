package moe.tlaster.shiba

interface INotifyPropertyChanged {
    var propertyChanged: PropertyChangedHandler
}

abstract class BaseNotifyObject : INotifyPropertyChanged {
    override var propertyChanged: PropertyChangedHandler = PropertyChangedHandler()

    fun notifyPropertyChanged(propertyName: String) {
        propertyChanged.invoke(this, propertyName)
    }
}

class PropertyChangedHandler {
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

    fun clear() {
        listeners.clear()
    }
}

@Target(AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Binding(val name: String)