package org.shibajs.shibasample

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.LayoutInflaterCompat
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.fasterxml.jackson.databind.ObjectMapper
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_list.view.*
import org.shibajs.shiba.*


//class Model : INotifyPropertyChanged {
//    override var propertyChanged: ((sender: Any, propertyName: String) -> Unit)? = null
//    @Binding("text")
//    var text = "hello world!"
//}

class MainActivity : AppCompatActivity() {

    val dataContext = WithListModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val contents = String(resources.openRawResource(R.raw.bundle).use {
            ByteArray(it.available()).apply {
                it.read(this)
            }
        })
        Shiba.configuration.scriptRuntime.execute(contents)
        host.dataContext = object : BaseNotifyObject() {
            @get:Binding(name = "text")
            @set:Binding(name = "text")
            var text = "Android"
                set(text) {
                    field = text
                    notifyPropertyChanged("text")
                }

        }
        host.component = "index"
    }
}

class WithListModel : BaseNotifyObject() {

    @get:Binding(name = "androidText")
    @set:Binding(name = "androidText")
    var text = "Android"
        set(text) {
            field = text
            notifyPropertyChanged("androidText")
        }


    @get:Binding(name = "jsonNode")
    val jsonNode = ObjectMapper().readTree("{\"hello\": {\"world\" : \"hello world\" }}")

    @get:Binding(name = "jsonTree")
    val jsonList = ObjectMapper().readTree("[{\"hello\": {\"world\" : \"hello world\" }}, {\"hello\": {\"world\" : \"hello world\" }},{\"hello\": {\"world\" : \"hello world\" }}]")


    @get:Binding(name = "androidList")
    @set:Binding(name = "androidList")
    var androidList = (1 until 1000).map { "Text $it" }

    @get:Binding(name = "list")
    @set:Binding(name = "list")
    var list = (1 until 1000).map { Model("Text $it") }
}

