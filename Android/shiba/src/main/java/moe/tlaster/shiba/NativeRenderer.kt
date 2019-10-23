package moe.tlaster.shiba

import android.content.Context
import moe.tlaster.shiba.dataBinding.ShibaBinding
import moe.tlaster.shiba.scripting.visitors.JSViewVisitor
import moe.tlaster.shiba.visitors.ValueVisitor
import org.liquidplayer.javascript.*

interface IShibaContext {
    fun getContext() : Context
    var dataContext: Any?
    val bindings: ArrayList<ShibaBinding>
    fun eventCallback(name: String)
}

internal object NativeRenderer {

    fun renderFromFunction(name: String, context: IShibaContext): NativeView {
        val creatorResult = Shiba.configuration.scriptRuntime.callFunction(name)
        if (creatorResult is JSValue) {
            val shibaView = JSViewVisitor.visit(creatorResult)
            if (shibaView is ShibaView) {
                return render(shibaView, context)
            }
        }
        return NativeView(context.getContext())
    }

    fun render(view: ShibaView?, context: IShibaContext): NativeView {
        return ValueVisitor.visit(view, context) as NativeView
    }
}