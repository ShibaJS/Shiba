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
You can writing a view like this
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
**NOTE: ```text -> "Hello world!"``` is defferent from ```text = "Hello world"```**
## Add child view
Just add a new view inside a view
```
view {
    childView {
        
    }
}
```
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
String will match all content inside ```""```, which contains the ```\n``` and others

### Boolean
```true``` or ```false```

### Array
Array can contain any object, e.g.  
```
["Hello World!", 1, false]
```
It will convert to ```List<Object>``` if possible

### Map
Map is similar to Array, but it contain keys
```
[
    "Hello" = "World!",
    "True" = false
]
```
**NOTE: Map is start with ```[``` and end with ```]``` NOT ```{``` and ```}```**
