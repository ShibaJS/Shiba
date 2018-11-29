[English](WritingShiba.md) | [中文](WritingShiba.zh.md)  
# 编写 Shiba
一个简单的布局定义如下
```
stack {
    text {
        text = "Hello world!"
    }
}
```
这会在屏幕上显示 "Hello world!"

## 一个简单的 View 定义
一个简单的 View 的定义如下
```
view {

}
```
如果你的 View 只有一个属性,比如下面这样
```
text {
    text = "Hello world!"
}
```
你可以简写为：
```
text -> "Hello world!"
```
**注意: ```text -> "Hello world!"``` 和 ```text = "Hello world"```** 是不一样的！
## 定义子 View
在 View 内部再定义一个 View即可
```
view {
    childView {
        
    }
}
```
## 自定义 View
你可以简单的定义一个自定义的 View,[阅读这里](CustomView.zh.md)

# 属性
## 类型
- [Number](#Number)
- [String](#String)
- [Boolean](#Boolean)
- ```null```
- [Array](#Array)
- [Map](#Array)

### Number
所有平台都会转换为 decimal 如果可能的话

### String
String 会匹配所有在 ```""``` 之内的字符, 也就是说诸如换行符  ```\n``` 也会被匹配到

### Boolean
```true``` or ```false```

### Array
一个数组可以包含任意数据类型, e.g.  
```
["Hello World!", 1, false]
```
在 Native 层将会是 ```List<Object>```

### Map
字典和数组类似,但是包含有 Key
```
[
    "Hello" = "World!",
    "True" = false
]
```
**NOTE: 字典以 ```[``` 开始, 以 ```]``` 结束,而不是 ```{``` 和 ```}```**

# Extension
你可以使用 Extension 来给属性提供值,Extension 以 ```$``` 开始  
所有使用了数据绑定的属性都会在 DataContext 出发 ```propertyChanged``` 的时候得到更新
```
text -> $resource hello
```
这会在 Native 层返回 Key 是 ```hello``` 的资源并设置到 View 的对应属性中   
你可以简单的[添加自定义 Extension](CustomExtension.zh.md)

# DataBinding
你可以将属性的值绑定到 DataContext 上的属性,数据绑定是一组特殊的 Extension
```
text -> $bind hello
```
这会在 DataContext 中寻找名为 hello 的属性并将其值设置到 View 的对应属性中  
你的 DataContext 类应该类似这样
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
一些 View 的一些属性默认是双向绑定的,比如
```
stack {
    input -> $bind text
    text -> $bind text
}
```
下面的 text 将会显示上面 input 所输入的内容

在一些应用情况下,你可以添加一些转换器。Shiba 使用 JavaScript 作为默认的脚本语言。
```
text -> append(hello($bind world), "awesome")
```
这会在 DataContext 中寻找名为 ```world``` 的属性并将其值传给 ```hello()``` ,计算完成后将结果传给 ```append()``` ,最后将最终结果赋值给 View 对应的属性  
你可以跳转到[这里](#Converter)去看如何添加转换器  
或者直接在 Extension 后面添加 
```
text -> $bind hello ${
    return it + " world!";
}
```
这会在 DataContext 中寻找名为 ```hello``` 的属性并将其值传给脚本  
这种类型的转换器以 ```${``` 开始, 并以 ```}``` 结束, 默认参数名为 ```it``` 并且目前**尚不能修改**

# Scripting
Siba 默认使用 JavaScript 作为脚本语言

| 平台 | Runtime |
|:---:|:---:|
|.Net|[ChakraCore](https://github.com/Microsoft/ChakraCore)|
|Android|V8 (By [LiquidCore](https://github.com/LiquidPlayer/LiquidCore/))|
|iOS|JavaScript Core|

## 转换器
你可以添加一下 JavaScript function 作为转换器使用
### Android
```
Shiba.addConverter("function hello(data) { return data + \" world!\"; }")
```
### .Net
```
AbstractShiba.Instance.AddConverter("function hello(data) { return data + \" world!\"; }")
```

## Native Object
你可以添加一些 Native 的实例作为 JavaScript 中 global 的一个属性  
### Android
[Example](../Android/shiba/src/main/java/moe/tlaster/shiba/scripting/runtime/Storage.kt)  
```YourObject``` 必须继承自 ```JSObject```
```
(Shiba.configuration.scriptRuntime as DefaultScriptRuntime).addObject("yourObject") {
    YourObject(it)
}

```
### .Net
[Example](../Windows/Shiba.Shared/Scripting/Runtime/Storage.cs)  
你需要添加 ```[JsExport(Name = "yourMethod")]``` attribute 来将你的方法暴露给 JavaScript Runtime
```
ShibaApp.Instance.ScriptRuntime.AddObject("yourObject", new YourObject());
```

### 自带的 Native Object
- storage

# 更多阅读
- [DataBinding](DataBinding.zh.md)
- [Scripting](Scripting.zh.md)
- [自定义 View](CustomView.zh.md)
- [自定义 Extension](CustomExtension.zh.md)