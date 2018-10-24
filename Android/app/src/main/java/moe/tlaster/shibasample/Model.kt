package moe.tlaster.shibasample

import moe.tlaster.shiba.BaseNotifyObject
import moe.tlaster.shiba.Binding

class Model(text: String) : BaseNotifyObject() {


    @get:Binding(name = "text")
    @set:Binding(name = "text")
    var text = text
        set(text) {
            field = text
            notifyPropertyChanged("text")
        }
}
