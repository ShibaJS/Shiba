package moe.tlaster.shibasample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import kotlinx.android.synthetic.main.activity_main.*
import moe.tlaster.shiba.Binding
import moe.tlaster.shiba.INotifyPropertyChanged

//class Model : INotifyPropertyChanged {
//    override var propertyChanged: ((sender: Any, propertyName: String) -> Unit)? = null
//    @Binding("text")
//    var text = "hello world!"
//}

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        host.load("stack { text { text = \$bind text } input { text = \$bind text} }", Model())
    }
}
