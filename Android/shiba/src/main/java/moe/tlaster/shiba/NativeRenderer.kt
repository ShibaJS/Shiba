package moe.tlaster.shiba

import android.content.Context
import android.view.View
import moe.tlaster.shiba.common.Singleton
import moe.tlaster.shiba.dataBinding.ShibaBinding
import moe.tlaster.shiba.parser.ShibaParserWrapper
import moe.tlaster.shiba.visitors.ShibaValueVisitor

interface IShibaContext {
    fun getContext() : Context
    val bindings: ArrayList<ShibaBinding>
}

internal object NativeRenderer {
    fun render(layout: String, shibaContext: IShibaContext): View {
        val viewTree = Singleton.get<ShibaParserWrapper>().parse(layout)
        return render(viewTree, shibaContext)
    }

    fun render(view: moe.tlaster.shiba.type.View?, shibaContext: IShibaContext): NativeView {
        if (view != null) {
            return ShibaValueVisitor.getValue(view, shibaContext) as NativeView
        }
        throw IllegalArgumentException("layout is not a valid view tree")
    }
}