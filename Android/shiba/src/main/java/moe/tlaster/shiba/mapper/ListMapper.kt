package moe.tlaster.shiba.mapper

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import moe.tlaster.shiba.*

class ListMapper : ViewMapper<RecyclerView>() {
    override fun getViewLayoutParams(): ViewGroup.LayoutParams {
        return ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }

    override fun createNativeView(context: IShibaContext): RecyclerView {
        return RecyclerView(context.getContext()).apply {
            layoutManager = LinearLayoutManager(context.getContext(), LinearLayoutManager.VERTICAL, false)
            adapter = ShibaRecyclerAdapter(context.getContext())
        }
    }

    override fun propertyMaps(): ArrayList<PropertyMap> {
        return super.propertyMaps().apply {
            add(PropertyMap("itemLayout", { view, value ->
                if (view is RecyclerView && value is View) {
                    val adapter = view.adapter as? ShibaRecyclerAdapter
                    adapter?.layout = value
                }
            }, View::class.java))
            add(PropertyMap("items", { view, value ->
                if (view is RecyclerView && value is List<*>) {
                    val adapter = view.adapter as? ShibaRecyclerAdapter
                    adapter?.items?.addAll(value)
                }
            }))
        }
    }
}

private class ShibaViewHolder(itemView: ShibaHost) : RecyclerView.ViewHolder(itemView)

private class ShibaRecyclerAdapter(val context: Context) : RecyclerView.Adapter<ShibaViewHolder>() {
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