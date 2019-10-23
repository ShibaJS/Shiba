package org.shibajs.shiba.commonProperty

import android.view.ViewGroup
import org.shibajs.shiba.NativeView
import org.shibajs.shiba.type.ShibaObject

class GridProperty(override val name: String = "grid") : AbsCommonProperty<ShibaObject>() {
    override fun setValue(value: ShibaObject, nativeView: NativeView, parent: ViewGroup) {
        val row = value["row"]?.toString()?.toDouble() ?: 0.0
        val column = value["column"]?.toString()?.toDouble() ?: 0.0
        val rowSpan = value["rowSpan"]?.toString()?.toDouble() ?: 1.0
        val columnSpan = value["columnSpan"]?.toString()?.toDouble() ?: 1.0
        val layoutParams = androidx.gridlayout.widget.GridLayout.LayoutParams(nativeView.layoutParams)
        layoutParams.rowSpec = androidx.gridlayout.widget.GridLayout.spec(row.toInt(), rowSpan.toInt())
        layoutParams.columnSpec = androidx.gridlayout.widget.GridLayout.spec(column.toInt(), columnSpan.toInt())
        nativeView.layoutParams = layoutParams
    }

}