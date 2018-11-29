[English](GettingStart.md) | [中文](GettingStart.zh.md)  
# 开始使用

## WPF/UWP/Xamarin.Forms:
从 Nuget 上安装
```
dotnet add package Shiba
```
记得初始化 (最好在 App 的 OnLaunched 方法内):  
```
ShibaApp.Init();
```

## Android:
```
implementation 'moe.tlaster:shiba:0.0.+'
```
记得初始化 (最好在 App 的 onCreate 方法内):  
```
Shiba.init(this)
```
## iOS 和其他平台:
仍在开发中,你可以提起你的 PR 来帮助我

## Notes
初始化方法可在任意位置调用,比如你需要延迟初始化来提高你的程序启动速度,只要你在真正使用 Shiba 之前调用即可

# 基础使用

## Android
添加 ```moe.tlaster.shiba.ShibaHost``` 到任意你想使用的地方
```
<moe.tlaster.shiba.ShibaHost
    android:id="@+id/shibaHost"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"/>
```
通过代码设置你的layout (推荐使用kotlin)
```
val yourLayout: String = ...
shibaHost.load(yourLayout, null)
```
```yourLayout``` 可以从任意位置加载,比如你的服务器  
如果你有数据上下文 (继承自 ```moe.tlaster.shiba.BaseNotifyObject``` 的 class ), 你可以设置 shibaHost 的数据上下文
```
val dataContext = ...
val yourLayout: String = ...
shibaHost.load(yourLayout, dataContext)
```
或者
```
val dataContext = ...
shibaHost.dataContext = dataContext 
```

## UWP/WPF
添加 ```ShibaHost`` 到你希望使用的地方
```
<shiba:ShibaHost Name="shibaHost" />
```
你可以从代码中载入你的 layout
```
var yourLayout = ...;
shibaHost.Layout = yourLayout;
```
或者在 xaml 中直接设置
```
<shiba:ShibaHost>
    yourLayout...
</shiba:ShibaHost>
```
你可以设置 ```ShibaHost``` 的数据上下文,和普通的 ```FrameworkElement``` 一样  
```
var dataContext = ...;
shibaHost.DataContext = dataContext;
```
或者直接在 xaml 中设置
```
<shiba:ShibaHost DataContext="{x:Bind YourCustomDataContext}" />
```

# 下一步
- [编写Shiba](WritingShiba.zh.md)