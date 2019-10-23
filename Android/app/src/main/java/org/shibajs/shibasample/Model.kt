package org.shibajs.shibasample

import org.shibajs.shiba.BaseNotifyObject
import org.shibajs.shiba.Binding

class Model(text: String) : BaseNotifyObject() {


    @get:Binding(name = "text")
    @set:Binding(name = "text")
    var text = text
        set(text) {
            field = text
            notifyPropertyChanged("text")
        }
}
