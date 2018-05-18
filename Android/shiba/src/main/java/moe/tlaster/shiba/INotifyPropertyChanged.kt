package moe.tlaster.shiba

interface INotifyPropertyChanged {
    var propertyChanged: (propertyName: String) -> Unit
}

@Target(AnnotationTarget.PROPERTY, AnnotationTarget.PROPERTY_GETTER)
@Retention(AnnotationRetention.RUNTIME)
annotation class Binding(val name: String)