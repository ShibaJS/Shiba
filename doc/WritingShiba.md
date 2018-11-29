[English](WritingShiba.md) | [中文](WritingShiba.zh.md)  
# Writing Shiba
A simple layout like this
```
stack {
    text {
        text = "Hello world!"
    }
}
```
And it will show the text "Hello world!"  

## writing a view
You can write a view like this
```
view {

}
```
If your view just contain single property like 
```
text {
    text = "Hello world!"
}
```
You can simplify it
```
text -> "Hello world!"
```
**NOTE: ```text -> "Hello world!"``` is different from ```text = "Hello world"```**
## Add child view
Just add a new view inside a view
```
view {
    childView {
        
    }
}
```
## Custom view
You can add your custom view easily, [read more](CustomView.md)

# Property
## Types
- [Number](#Number)
- [String](#String)
- [Boolean](#Boolean)
- ```null```
- [Array](#Array)
- [Map](#Array)

### Number
All platform will convert to decimal value if possible

### String
A string will match all content inside ```""```, which contains the ```\n``` and others

### Boolean
```true``` or ```false```

### Array
An array can contain any object, e.g.  
```
["Hello World!", 1, false]
```
It will convert to ```List<Object>``` if possible

### Map
A map is similar to Array, but it contains keys
```
[
    "Hello" = "World!",
    "True" = false
]
```
**NOTE: Map start with ```[``` and end with ```]``` NOT ```{``` and ```}```**

# Extension
You can provide some extension to set the property value, the extension start with ```$```
```
text -> $resource hello
```
It will provide a native resource by key ```hello``` and set the property   
You can [add your custom extension](CustomExtension.md) easily

# DataBinding
You can use data binding to bind your property to your data context, and data binding is a specific extension  
All property that use data binding will be update when data context fire a ```propertyChanged```
```
text -> $bind hello
```
It will provide the ```hello``` property value from the data context  
Your data context class look like this:
### Android
```
class Model : BaseNotifyObject() {
    @get:Binding(name = "hello")
    @set:Binding(name = "hello")
    var text = "World"
        set(text) {
            field = text
            notifyPropertyChanged("text")
        }
}
```
### .Net
```
public class Model : INotifyPropertyChanged
{
    private string _hello = "World!";
    public string Hello
    {
        get => _hello;
        set
        {
            _hello = value;
            OnPropertyChanged("hello");
        }
    }
    public event PropertyChangedEventHandler PropertyChanged;
    protected virtual void OnPropertyChanged([CallerMemberName] string propertyName = null)
    {
        PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(propertyName));
    }
}
```
Some View's property is two-way binding as default, e.g.
```
stack {
    input -> $bind text
    text -> $bind text
}
```
The text below will show the input's text

You can add some converter for some use case, Shiba use JavaScript as the default scripting language
```
text -> append(hello($bind world), "awesome")
```
It will provide the ```world``` property value from data context, and pass it to ```hello()```, then pass the result to ```append()```, finally set the final result to view's property  
You can go to [here](#Converter) to see how to add the converter  
Or just writing the script after the extension 
```
text -> $bind hello ${
    return it + " world!";
}
```
It will provide the ```hello``` property value from data context, and pass it to the script  
This kind of script start with ```${``` and end with ```}```, the parameter name is ```it``` and currently **cannot be changed**

# Scripting
Shiba allow using JavaScript as the script language

| Platform | Runtime |
|:---:|:---:|
|.Net|[ChakraCore](https://github.com/Microsoft/ChakraCore)|
|Android|V8 (By [LiquidCore](https://github.com/LiquidPlayer/LiquidCore/))|
|iOS|JavaScript Core|

## Converter
Currently, you can use JavaScript to write some converter for DataBinding
### Android
```
Shiba.addConverter("function hello(data) { return data + \" world!\"; }")
```
### .Net
```
AbstractShiba.Instance.AddConverter("function hello(data) { return data + \" world!\"; }")
```

## Native Object
You can add a native object as a global property  
### Android
[Example](../Android/shiba/src/main/java/moe/tlaster/shiba/scripting/runtime/Storage.kt)  
```YourObject``` must extends from ```JSObject```
```
(Shiba.configuration.scriptRuntime as DefaultScriptRuntime).addObject("yourObject") {
    YourObject(it)
}

```
### .Net
[Example](../Windows/Shiba.Shared/Scripting/Runtime/Storage.cs)  
You must add ```[JsExport(Name = "yourMethod")]``` attribute in order to export your method to JavaScript Runtime
```
ShibaApp.Instance.ScriptRuntime.AddObject("yourObject", new YourObject());
```

### Build in native object
- storage

# More
- [DataBinding](DataBinding.md)
- [Scripting](Scripting.md)
- [Custom View](CustomView.md)
- [Custom Extension](CustomExtension.md)