package moe.tlaster.shibasample;

import org.jetbrains.annotations.Nullable;

import java.util.PropertyResourceBundle;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import moe.tlaster.shiba.Binding;
import moe.tlaster.shiba.INotifyPropertyChanged;
import moe.tlaster.shiba.PropertyChanged;

public class Model implements INotifyPropertyChanged {
    private String text = "hello world!";
    private PropertyChanged propertyChanged;

    @Binding(name = "text")
    public String getText() {
        return text;
    }

    @Binding(name = "text")
    public void setText(String text) {
        this.text = text;
        getPropertyChanged().invoke(this, "text");
    }

    @Nullable
    @Override
    public PropertyChanged getPropertyChanged() {
        return propertyChanged;
    }

    @Override
    public void setPropertyChanged(@Nullable PropertyChanged propertyChanged) {
        this.propertyChanged = propertyChanged;
    }
}
