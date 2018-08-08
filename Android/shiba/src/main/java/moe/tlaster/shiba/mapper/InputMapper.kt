package moe.tlaster.shiba.mapper

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import moe.tlaster.shiba.IShibaContext

class InputMapper : ViewMapper<EditText>()  {
    override fun createNativeView(context: IShibaContext): EditText {
        return EditText(context.getContext())
    }
    override fun propertyMaps(): ArrayList<PropertyMap> {
        return super.propertyMaps().apply {
            add(TwoWayPropertyMap("text", { view, it -> if (it is CharSequence && view is EditText) view.setText(it) }, { view, callback ->
                if (view is EditText) {
                    view.addTextChangedListener(object : TextWatcher {
                        override fun afterTextChanged(s: Editable?) {
                        }
                        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                        }
                        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                            callback.invoke(s?.toString())
                        }
                    })
                }
            }))
        }
    }
}