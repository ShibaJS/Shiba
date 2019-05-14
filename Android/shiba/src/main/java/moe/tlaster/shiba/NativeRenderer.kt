package moe.tlaster.shiba

import android.content.Context
import android.view.View
import moe.tlaster.shiba.common.Singleton
import moe.tlaster.shiba.dataBinding.ShibaBinding
import moe.tlaster.shiba.visitors.ValueVisitor

interface IShibaContext {
    fun getContext() : Context
    var dataContext: Any?
    val bindings: ArrayList<ShibaBinding>
}

internal object NativeRenderer {

    fun render(view: ShibaView?, context: IShibaContext): NativeView {
        return ValueVisitor.visit(view, context) as NativeView
    }
}