package moe.tlaster.shiba

import android.content.Context
import android.view.View
import android.view.ViewGroup
import moe.tlaster.shiba.parser.ShibaParserWrapper
import java.lang.reflect.Method
import java.util.concurrent.ConcurrentHashMap

//data class RenderResult(val view: View, val bindings: Map<String, Method>? = null, val callbacks: List<(String) -> Unit>? = null)

interface IViewRenderer {
    fun renderer(view: moe.tlaster.shiba.View, dataContext: Any?, context: Context): View
}

internal object NativeRenderer {
    private val parser = ShibaParserWrapper()
    //TODO: Dictionary means it does not support multiple renderr for one view (like Xamarin.Forms custom renderer)
    private val renderer = ConcurrentHashMap<String, IViewRenderer>()

    fun render(layout: String, dataContext: Any?, context: Context): View {
        val viewTree = parser.parse(layout)
        return render(viewTree, dataContext, context)
    }

    private fun render(layout: moe.tlaster.shiba.View, dataContext: Any?, context: Context): View {
        val renderer = Shiba.viewMapping.firstOrNull { it.name == layout.viewName } ?: throw IllegalStateException()
        val target = renderer.renderer.renderer(layout, dataContext, context)
        if (layout.children.any() && target is ViewGroup) {
            layout.children.forEach { target.addView(render(it, dataContext, context)) }
        }
        return target
    }
}