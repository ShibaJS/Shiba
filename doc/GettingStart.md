[English](GettingStart.md) | [中文](GettingStart.zh.md)  
# Getting Start

## For WPF/UWP/Xamarin.Forms:
You can install from nuget
```
dotnet add package Shiba
```
And call the initialization (Better from your App's OnLaunched):  
```
ShibaApp.Init();
```

## For Android:
```
implementation 'moe.tlaster:shiba:0.0.+'
```
And call the initialization (Better from your App's onCreate):  
```
Shiba.init(this)
```

## iOS and others:
Still working on it, you can add your PR to help me

## Notes
All the initialization can be called from anywhere, e.g. you want delay initialization, just call it before actually using Shiba

# Useage

## Android
Add ```moe.tlaster.shiba.ShibaHost``` to where you want to use
```
<moe.tlaster.shiba.ShibaHost
    android:id="@+id/shibaHost"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"/>
```
Load your layout from your code (Kotlin is recommended)
```
val yourLayout: String = ...
shibaHost.load(yourLayout, null)
```
```yourLayout``` can be fetch from anywhere, e.g. a web server  
If you have a data context (a class extends from ```moe.tlaster.shiba.BaseNotifyObject```), you can set the dataContext to ShibaHost
```
val dataContext = ...
val yourLayout: String = ...
shibaHost.load(yourLayout, dataContext)
```
Or
```
val dataContext = ...
shibaHost.dataContext = dataContext 
```

## UWP/WPF
Add ```ShibaHost`` to where you want to use
```
<shiba:ShibaHost Name="shibaHost" />
```
You can load your layout from code behind
```
var yourLayout = ...;
shibaHost.Layout = yourLayout;
```
Or from xaml
```
<shiba:ShibaHost>
    yourLayout...
</shiba:ShibaHost>
```
You can set the DataContext of the ```ShibaHost``` just like a normal ```FrameworkElement```
```
var dataContext = ...;
shibaHost.DataContext = dataContext;
```
Or from xaml
```
<shiba:ShibaHost DataContext="{x:Bind YourCustomDataContext}" />
```
# What's Next?
- [WritingShiba](WritingShiba.md)