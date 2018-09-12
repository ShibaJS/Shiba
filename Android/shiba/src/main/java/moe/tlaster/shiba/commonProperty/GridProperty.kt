package moe.tlaster.shiba.commonProperty

import android.support.v7.widget.GridLayout
import android.view.ViewGroup
import moe.tlaster.shiba.NativeView
import moe.tlaster.shiba.ShibaMap

class GridProperty(override val name: String = "grid") : AbsCommonProperty<ShibaMap>() {
    override fun setValue(value: ShibaMap, nativeView: NativeView, parent: ViewGroup) {
        val row = value["row"]?.toString()?.toInt() ?: 0
        val column = value["column"]?.toString()?.toInt() ?: 0
        val rowSpan = value["rowSpan"]?.toString()?.toInt() ?: 1
        val columnSpan = value["columnSpan"]?.toString()?.toInt() ?: 1
        val layoutParams = GridLayout.LayoutParams(nativeView.layoutParams)
        layoutParams.rowSpec = GridLayout.spec(row, rowSpan)
        layoutParams.columnSpec = GridLayout.spec(column, columnSpan)
        nativeView.layoutParams = layoutParams
    }

}