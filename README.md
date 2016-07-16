# React Native Android Kit <br/> [![npm version](https://img.shields.io/badge/npm-v1.1.0-green.svg)](https://www.npmjs.com/package/react-native-android-kit)	[![react-native version](https://img.shields.io/badge/react--native-min%20v0.25.1-blue.svg)](https://github.com/facebook/react-native) 

A set of native Android UI components and modules for React Native framework. 
The purpose of this kit is to offer to React Native developers some new Android native components that are currently not implemented by React Native core team. 
For example, some components from Android Design Support Library are now available through this kit.

<br/>
## Table of Contents

- [Getting Started](#getting-started)
- [Demo](#demo)
- [Components](#components)
	* [TabLayoutAndroid](#tablayoutandroid)
		* [Introduction](#introduction)
		* [Props](#props)
		* [Example](#example)
	* [ButtonAndroid](#buttonandroid)
		* [Introduction](#introduction-1)
		* [Props](#props-1)
		* [Example](#example-1)
	* [FloatingButtonAndroid](#floatingbuttonandroid)
		* [Introduction](#introduction-2)
		* [Props](#props-2)
		* [Example](#example-2)
- [Modules](#modules)
- [Misc](#misc)
	* [Color](#color)
	* [Drawable](#drawable)
- [License](#license)

<br/>

## Getting Started

<br/>
To use this kit inside your react native project, you must follow these steps:

- [x] Go to your root project folder
- [x] Install react-native-android-kit using npm install command: `npm install react-native-android-kit --save`
- [x] Add the following lines to your `android/settings.gradle`:
	```gradle
	include ':ReactNativeAndroidKit'
	project(':ReactNativeAndroidKit').projectDir = file('../node_modules/react-native-android-kit/android')
	```
	
- [x] Inside `android/app/build.gradle` file, add compilation instruction for ReactNativeAndroidKit like that:
	```gradle
	dependencies {
		...
		compile project(':ReactNativeAndroidKit')			
	}
	```

- [x] Finally, edit `MainApplication` class (generally located at `android/app/src/main/java/<main-package-path>/MainApplication.java`):
	```java
	...

	import com.facebook.react.ReactPackage;
	import com.facebook.react.shell.MainReactPackage;
	import fr.ayoubdev.rnak.RNAKPackage;	//<------------------------------------------ Add this import statement

	...

	public class MainApplication extends Application implements ReactApplication {
		
		...

			@Override
			protected List<ReactPackage> getPackages() {
				return Arrays.<ReactPackage>asList(
					new MainReactPackage(),
					new RNAKPackage()	//<-------------------------- Add this statement
				);
			}
		};
	
	...
	```

<br/><br/><br/>
## Demo

<br/>
If you want an overview of RNAK, it's interesting to try the demonstration application located inside `./example` folder
For this, just follow these steps:

- [x] Connect your device or launch your Android emulator
- [x] Clone this repository
- [x] Go to example folder: `cd ./example`
- [x] Install npm dependencies, buil and deploy the demonstration application by running: `npm install`
- [x] Enjoy RNAK on your device/emulator !

<br/><br/><br/>
## Components

<br/>
### TabLayoutAndroid

#### Introduction

TabLayoutAndroid component provides a horizontal layout to display tabs.
Population of the tabs to display is done through TabAndroid component.
Transition between tabs are managed by a ViewPager instance (you don't need to care about it: all is managed by TabLayoutAndroid component).<br/>
For more details, see: [Native TabLayout documentation](http://developer.android.com/reference/android/support/design/widget/TabLayout.html "TabLayout Android Developers")

#### Props:

##### TabLayoutAndroid props:
It is important all children of TabLayoutAndroid are TabAndroid(s) component and not composite components:

> [View props...](https://facebook.github.io/react-native/docs/view.html#props)<br/><br/>
> [ViewPagerAndroid props...](https://facebook.github.io/react-native/docs/viewpagerandroid.html#props)<br/><br/>
> **backgroundColor** [color](#color) *optional* <br/>Sets the background color for TabLayout container.<br/><br/>
> **indicatorTabColor** [color](#color) *optional* <br/>Sets the tab indicator's color for the currently selected tab.<br/><br/>
> **indicatorTabHeight** number *optional* <br/>Sets the tab indicator's height for the currently selected tab.<br/><br/>
> **scrollable** boolean *optional, default = true* <br/>Set the behavior mode for the Tabs in this layout: <br/>true = SCROLLABLE tabs mode. <br/>false = FIXED tabs mode.<br/><br/>
> **backgroundImage** string *optional* <br/>Set the background's TabLayout to a given Drawable (see [Drawable](#drawable)).<br/><br/>
> **center** boolean *optional, default = true* <br/>Set the gravity to use when laying out the tabs: <br/>true = CENTER tabs gravity (only takes effect if you are on FIXED tabs Mode).<br/>false = FILL tabs gravity.<br/><br/>

*By default, 60 is the height value for tabs container.*


##### TabAndroid props:
TabAndroid represents a child for TabLayoutAndroid (i.e a tab instance). Especially, it's a container that allows you to store child view(s) for current tab instance. In a nutshell, it works like a <View> container but for TabLayoutAndroid. 

Besides, each TabAndroid can be customized by several properties:

> [View props...](https://facebook.github.io/react-native/docs/view.html#props)<br/><br/>
> **text** string *optional* <br/>Sets the tab label.<br/><br/>
> **icon** string *optional* <br/>Sets the tab icon (see [Drawable](#drawable)).<br/><br/>
> **iconPosition** string *optional, default = 'top' [only, if customView prop === true]* <br/>Sets the Drawables (if any) to appear to the left of, above, to the right of, and below the text.<br/>Allowed values: left, top, right, bottom (if wrong string, top value is set by default).<br/><br/>
> **textSize** number *optional [only, if customView prop === true]* <br/>Set the default text size to the given value, interpreted as "scaled pixel" unit (sp unit).<br/><br/>
> **textColor** [color](#color) *optional [only, if customView prop === true]* <br/>Sets the text color for the normal state.<br/><br/>
> **selectedTextColor** [color](#color) *optional [only, if customView prop === true]* <br/>Sets the text color for the selected state.<br/><br/>
> **customView** boolean *optional, default = true* <br/>Sets custom view behavior for current tab.<br/>true = Custom View enabled.<br/>false = Custom View disabled: only, text and icon properties take effect.<br/><br/>

#### Example

###### Basic Usage:

```jsx
import React, {AppRegistry, StyleSheet, Text, View} from "react-native";
import {TabLayoutAndroid, TabAndroid} from "react-native-android-kit";

class TabLayoutExample extends React.Component {
	render() {
		return (
			<View style={{flex:1}}>
			
				<TabLayoutAndroid style={{height:60}} backgroundColor='#009688' indicatorTabColor='#ffc400'
								  indicatorTabHeight={2} scrollable={false} center={false}>

					<TabAndroid text='Tab1' textSize={16} textColor="white" selectedTextColor='#ffc400'
								icon='ic_home_black_24dp' iconPosition='left'>
						
						<Text>I'm the first Tab content!</Text>
						
					</TabAndroid>
					
					<TabAndroid text='Tab2' textSize={16} textColor='white' selectedTextColor='#ffc400'
								icon='ic_important_devices_black_24dp' iconPosition='left'>
						
						<Text>I'm the second Tab content!</Text>
						
					</TabAndroid>

				</TabLayoutAndroid>
				
			</View>
		);
	}
}
```

###### Demonstration:

<p align="center">
	<img src="https://raw.githubusercontent.com/ayoubdev/assets/master/react-native-android-kit/tablayout.Gif" title="TabLayout Demonstration" alt="TabLayoutAndroid & TabAndroid"/>
</p>
*For corresponding code, see [Code from demonstration application](example/src/index.js)*

<br/><br/>
### ButtonAndroid

#### Introduction

Represents a push-button widget. Push-buttons can be pressed, or clicked, by the user to perform an action.<br/>
For more details, see: [Native Button documentation](http://developer.android.com/reference/android/widget/Button.html "Button Android Developers")

#### Props:

> [View props...](https://facebook.github.io/react-native/docs/view.html#props)<br/><br/>
> [TouchableWithoutFeedback props...](https://facebook.github.io/react-native/docs/touchablewithoutfeedback.html#props)<br/><br/>
> **text** string *optional* <br/>Sets the button label.<br/><br/>
> **textSize** number *optional, default = 15* <br/>Set the default text size to the given value, interpreted as "scaled pixel" unit (sp unit).<br/><br/>
> **textColor** [color](#color) *optional, default = 'black'* <br/>Sets the text color.<br/><br/>
> **backgroundColor** [color](#color) *optional* <br/>Sets the background color.<br/><br/>

#### Example

###### Basic Usage:

```jsx
import React, {StyleSheet, View, ToastAndroid} from "react-native";
import {ButtonAndroid} from "react-native-android-kit";

class ButtonExample extends React.Component {
	render() {
		return (
			<View style={{flex:1}}>
				<ButtonAndroid
					textColor='red'
					backgroundColor='#FF009688'
					textSize={12}
					text='Custom Button'
					onPress={
						() => {
							ToastAndroid.show("Event onPress", ToastAndroid.SHORT);
						}
					}
				/>
			</View>
		);
	}
}
```

###### Demonstration:

<p align="center">
	<img src="https://raw.githubusercontent.com/ayoubdev/assets/master/react-native-android-kit/button.Gif" title="Button Demonstration" alt="ButtonAndroid"/>
</p>

*For corresponding code, see [Code from demonstration application](example/src/components/button.js)*

<br/><br/>
### FloatingButtonAndroid

#### Introduction

Floating action buttons are used for a special type of promoted action. They are distinguished by a circled icon floating above the UI.<br/>
For more details, see: [Native FloatingActionButton documentation](http://developer.android.com/reference/android/support/design/widget/FloatingActionButton.html "FloatingActionButton Android Developers")

#### Props:

> [View props...](https://facebook.github.io/react-native/docs/view.html#props)<br/><br/>
> [TouchableWithoutFeedback props...](https://facebook.github.io/react-native/docs/touchablewithoutfeedback.html#props)<br/><br/>
> **icon** string *optional* <br/>Sets the button icon (see [Drawable](#drawable)).<br/><br/>
> **backgroundColor** [color](#color) *optional* <br/>Sets the background color.<br/><br/>
> **rippleColor** [color](#color) *optional* <br/>Sets the ripple color.<br/><br/>
> **hidden** boolean *optional, default = false* <br/>Hides/Shows the button: <br/>true = Hides the button.<br/>false = Shows the button.<br/><br/>
> **rippleEffect** boolean *optional, default = true* <br/>Defines whether this view reacts to click by a ripple effect or not: <br/>true = Ripple effect enabled.<br/>false = Ripple effect disabled.<br/><br/>

#### Example:

###### Basic Usage:

```jsx
import React, {StyleSheet, View, ToastAndroid} from "react-native";
import {FloatingButtonAndroid} from "react-native-android-kit";

class FloatingButtonExample extends React.Component {
	render() {
		return (
			<View style={{flex:1}}>
				<FloatingButtonAndroid
					style={[styles.fab,{height:100, width:100}]}
					backgroundColor='#ffff0000'
					rippleColor='black'
					icon='ic_reply_all_black_24dp'
					onPress={
						() => {
							ToastAndroid.show("Event onPress", ToastAndroid.SHORT);
						}
					}
				/>
			</View>
		);
	}
}
```

###### Demonstration:

<p align="center">
	<img src="https://raw.githubusercontent.com/ayoubdev/assets/master/react-native-android-kit/fab.Gif" title="FloatingActionButton Demonstration" alt="FloatingButtonAndroid"/>
</p>

*For corresponding code, see [Code from demonstration application](example/src/components/floatingButton.js)*

<br/><br/><br/>
## Modules

<br/>
Nothing for now ( maybe Snackbar module soon, stay tuned :) )

<br/><br/><br/>
## Misc

<br/>
### Color

Color value property is set via a string input. <br/>
Supported formats are: '#RRGGBB' , '#AARRGGBB' or one of the following names: 'red', 'blue', 'green', 'black', 'white', 'gray', 'cyan', 'magenta', 'yellow', 'lightgray', 'darkgray', 'grey', 'lightgrey', 'darkgrey', 'aqua', 'fuchsia', 'lime', 'maroon', 'navy', 'olive', 'purple', 'silver', 'teal'.

<br/>
### Drawable

For now, only static images resources are supported. They must be located inside one of drawable folders (usually located at `android/app/src/main/res/drawable` or `android/app/src/main/res/drawable-XXXXXX` if you want to manage icon size according to display format). <br/>
To target a resource, you only need to specify string basename (i.e. without extension) and it must respect underscored name. For example, if you have an image called toto-tata.png, you must specify 'toto_tata' as a property value.


<br/><br/><br/>
## License

[MIT](./LICENSE "License MIT")
