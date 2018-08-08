package moe.tlaster.shiba

import android.content.Context
import android.util.ArrayMap
import android.view.View
import moe.tlaster.shiba.mapper.ISubscription
import moe.tlaster.shiba.parser.ShibaParserWrapper
import moe.tlaster.shiba.visitors.ShibaValueVisitor

interface IShibaContext {
    fun getContext() : Context
    fun twowayToDataContext(path: String, it: Any?)
    val propertyChangedSubscription: ArrayMap<NativeView, ArrayList<ISubscription>>
}

internal object NativeRenderer {
    fun render(layout: String, shibaContext: IShibaContext): View {
        val viewTree = Singleton.get<ShibaParserWrapper>().parse(layout)
        if (viewTree != null){
            return ShibaValueVisitor.getValue(viewTree, shibaContext) as View
        }
        throw IllegalArgumentException("layout is not a valid view tree")
    }
}