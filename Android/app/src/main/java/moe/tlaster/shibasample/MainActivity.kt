package moe.tlaster.shibasample

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
import moe.tlaster.shiba.BaseNotifyObject
import moe.tlaster.shiba.Binding
import moe.tlaster.shiba.INotifyPropertyChanged

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

//        host.load("list { itemLayout = text -> \$bind items = \$bind androidList }", WithListModel())

        val layout = "<text text=\"{awesome(\$json hello.world { if(it === null) return null; return it + ' +1!'; })}\"/>"
        host.load(layout, dataContext.jsonNode)
        input.setText(layout)
        input.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                try {
                    host.load(s.toString(), dataContext)
                } catch (e: Exception) {

                } catch (e: Error) {

                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })
//        list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//        list.adapter = Adapter(this)
//        host.load("stack { text { text = \$bind text } input { text = \$bind text} }", Model())

        go_streaming.setOnClickListener {
            startActivity(Intent(this, LiveActivity::class.java))
        }
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

class Adapter(private val context: Context) : androidx.recyclerview.widget.RecyclerView.Adapter<ViewHolder>() {

    private val items = (1 until 1000).map { Model("Text $it") }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false)
        view.host.load("stack { padding = [ top = 8 ] width = match_parent text -> awesome(reverse(\$bind androidText)) text { text=\"Android!\" } input { text = \$bind androidText width=match_parent } input { text = \$bind androidText} }", null)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.text.text = items[position].text
        holder.itemView.host.dataContext = items[position]
    }

}

class ViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {

}
