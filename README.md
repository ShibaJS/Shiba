# Status  
[![Build status](https://ci.appveyor.com/api/projects/status/2qp96xf35naduen7?svg=true)](https://ci.appveyor.com/project/Tlaster/shiba)
[![NuGet version](https://badge.fury.io/nu/Shiba.svg)](https://badge.fury.io/nu/Shiba)  
Shiba is still in development, you can check the progress at the bottom of page

# Getting Start

Make sure calling the initialization before using Shiba

A simple layout like this
```
stack {
  input {
    text = "hello world!"
  }
}
```

You can use data binding
```
stack {
  input {
    text = $bind Text
  }
}
```

You can add some converters using javascript
```
stack {
  input {
    text = reverse($bind Text)
  }
}
```
```
function reverse(value) {
  return value.split("").reverse().join("");
}
```

You can provide platform specific value like this  
```
stack {
  input {
    text = [UWP: reverse($bind UwpText), Android: reverse($bind AndroidText)]
  }
}
```
# How does it work
Shiba will 'translate' the Shiba layout into native layout at runtime, for example, when you define a Shiba layout in UWP like this:
```
<Grid>
  <ShibaHost>
    <ShibaHost.Layout>
      stack {
        input {
          Text = "Hello world!"
        }
      }
    </ShibaHost.Layout>
  </ShibaHost>
</Grid>
```
You will get this at runtime:
```
<Grid>
  <ShibaHost>
    <StackLayout>
      <TextBox Text="Hello wrold!"/>
    </StackLayout>
  </ShibaHost>
</Grid>
```

# Goal
Not like other cross-platform framework, Shiba is NOT a framework to replace the native development, Shiba will replace the UI code which is not complex but will change frequently.  
Shiba will also provide a easy way to define your costom layout translator.  
You can mix your native code with Shiba, you can also use Shiba to create a simple application without writing native code.

# Progress
- [ ] Core rendering
  - [x] WPF && UWP   
  - [x] Android   
  - [ ] iOS && macOS  
  - [ ] Xamarin support
- [ ] Basic layout renderer
  - [x] WPF && UWP   
  - [ ] Android  
  - [ ] iOS && macOS  
  - [ ] Xamarin support

# LICENSE
The MIT License (MIT)

Copyright (c) 2018

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
