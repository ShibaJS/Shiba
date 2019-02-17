package moe.tlaster.shiba.mapper

import android.content.Context
import android.view.ViewGroup
import moe.tlaster.shiba.*
import moe.tlaster.shiba.type.CollectionChangedEventArg
import moe.tlaster.shiba.type.ShibaArray
import moe.tlaster.shiba.type.View

class ListMapper : ViewMapper<androidx.recyclerview.widget.RecyclerView>() {
    override fun getViewLayoutParams(): ViewGroup.LayoutParams {
        return ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }

    override fun createNativeView(context: IShibaContext): androidx.recyclerview.widget.RecyclerView {
        return androidx.recyclerview.widget.RecyclerView(context.getContext()).apply {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context.getContext(), androidx.recyclerview.widget.RecyclerView.VERTICAL, false)
            adapter = ShibaRecyclerAdapter(context.getContext())
        }
    }

    override fun propertyMaps(): ArrayList<PropertyMap> {
        return super.propertyMaps().apply {
            add(PropertyMap("itemLayout", { view, value ->
                if (view is androidx.recyclerview.widget.RecyclerView && value is View) {
                    val adapter = view.adapter as? ShibaRecyclerAdapter
                    adapter?.layout = value
                }
            }, View::class.java))
            add(PropertyMap("items", { view, value ->
                if (view is androidx.recyclerview.widget.RecyclerView && value is List<*>) {
                    val adapter = view.adapter as? ShibaRecyclerAdapter
                    adapter?.items?.addAll(value)
                }
            }))
        }
    }
}

private class ShibaViewHolder(itemView: ShibaHost) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView)

private class ShibaRecyclerAdapter(val context: Context) : androidx.recyclerview.widget.RecyclerView.Adapter<ShibaViewHolder>() {
    var layout: View? = null
    set(value) {
        field = value
        notifyDataSetChanged()//relayout
    }

    private val onItemsChanged: (Any, CollectionChangedEventArg) -> Unit = { _, _ ->
        notifyDataSetChanged()
    }
    var items = ShibaArray().apply {
        collectionChanged += this@ShibaRecyclerAdapter.onItemsChanged
    }
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ShibaViewHolder {
        return ShibaViewHolder(ShibaHost(context).apply {
            load(layout, null)
        })
    }

    override fun getItemCount() = items.count()

    override fun onBindViewHolder(viewHolder: ShibaViewHolder, position: Int) {
        (viewHolder.itemView as? ShibaHost)?.dataContext = items[position]
    }
}