package moe.tlaster.shiba.commonProperty

import android.view.ViewGroup
import moe.tlaster.shiba.NativeView
import moe.tlaster.shiba.type.ShibaMap

class GridProperty(override val name: String = "grid") : AbsCommonProperty<ShibaMap>() {
    override fun setValue(value: ShibaMap, nativeView: NativeView, parent: ViewGroup) {
        val row = value["row"]?.toString()?.toInt() ?: 0
        val column = value["column"]?.toString()?.toInt() ?: 0
        val rowSpan = value["rowSpan"]?.toString()?.toInt() ?: 1
        val columnSpan = value["columnSpan"]?.toString()?.toInt() ?: 1
        val layoutParams = androidx.gridlayout.widget.GridLayout.LayoutParams(nativeView.layoutParams)
        layoutParams.rowSpec = androidx.gridlayout.widget.GridLayout.spec(row, rowSpan)
        layoutParams.columnSpec = androidx.gridlayout.widget.GridLayout.spec(column, columnSpan)
        nativeView.layoutParams = layoutParams
    }

}