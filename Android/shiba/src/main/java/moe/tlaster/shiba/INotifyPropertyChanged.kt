package moe.tlaster.shiba

interface INotifyPropertyChanged {
    var propertyChanged: Event<String>
}

abstract class BaseNotifyObject : INotifyPropertyChanged {
    override var propertyChanged: Event<String> = Event()

    fun notifyPropertyChanged(propertyName: String) {
        propertyChanged.invoke(this, propertyName)
    }
}

class Event<T> {
    private val listeners = ArrayList<(sender: Any, arg: T) -> Unit>()
    fun invoke(sender: Any, arg: T) {
        listeners.forEach { it.invoke(sender, arg) }
    }

    operator fun plusAssign(propertyChanged: (Any, T) -> Unit) {
        listeners.add(propertyChanged)
    }

    operator fun minusAssign(propertyChanged: (Any, T) -> Unit) {
        listeners.remove(propertyChanged)
    }

    fun clear() {
        listeners.clear()
    }
}

@Target(AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Binding(val name: String)