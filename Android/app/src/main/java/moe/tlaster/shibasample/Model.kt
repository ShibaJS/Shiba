package moe.tlaster.shibasample

import moe.tlaster.shiba.BaseNotifyObject
import moe.tlaster.shiba.Binding

class Model : BaseNotifyObject {


    constructor(text: String) {
        this.text = text
    }

    @get:Binding(name = "androidText")
    @set:Binding(name = "androidText")
    var text = "Android"
        set(text) {
            field = text
            notifyPropertyChanged("androidText")
        }
}
