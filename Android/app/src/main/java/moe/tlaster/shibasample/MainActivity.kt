package moe.tlaster.shibasample

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.LayoutInflaterCompat
import android.support.v7.widget.LinearLayoutCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        host.load("list { items = \$bind androidList itemLayout = stack { text -> \"hello!\" text -> \$bind } }", WithListModel())
        
//        list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//        list.adapter = Adapter(this)
//        host.load("stack { text { text = \$bind text } input { text = \$bind text} }", Model())
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


    @get:Binding(name = "androidList")
    @set:Binding(name = "androidList")
    var list = ArrayList<String>().apply {
        add("Haha1")
        add("Haha2")
        add("Haha3")
        add("Haha4")
        add("Haha5")
        add("Haha6")
        add("Haha7")
        add("Haha8")
        add("Haha9")
        add("Haha10")
        add("Haha11")
        add("Haha12")
        add("Haha13")
        add("Haha14")
        add("Haha15")
        add("Haha16")
        add("Haha17")
        add("Haha18")
        add("Haha19")
        add("Haha20")
        add("Haha21")
    }
}

class Adapter(private val context: Context) : RecyclerView.Adapter<ViewHolder>() {

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

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

}
