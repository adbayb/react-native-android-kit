import React, {
	Component
} from "react";
import {
	StyleSheet,
	View,
	ToastAndroid
} from "react-native";
import { FloatingButtonAndroid } from "react-native-android-kit";

const styles = StyleSheet.create({
	container: {
		// position in React Native is similar to regular CSS, but everything is set 
		// to relative by default, so absolute positioning is always just relative to the parent:
		// position: "absolute",
		flex: 1,
		flexDirection: "row",
		alignItems: "center",
		justifyContent: "space-around"
	}
});

export default class FloatingButton extends Component {
	render() {
		return (
			<View style={styles.container}>
				<FloatingButtonAndroid
					backgroundColor='purple'
					rippleColor='white'
					icon='ic_add_white_24dp'
					onPress={
						() => {
							ToastAndroid.show("Event onPress: Native Android FloatingAction Button (Ripple effect)", ToastAndroid.SHORT);
							console.log("Default Ripple Icon FAButton");
						}
					}
					/>

				<FloatingButtonAndroid
					icon='ic_home_black_24dp'
					rippleEffect={false}
					onPress={
						() => {
							ToastAndroid.show("Event onPress: Native Android FloatingAction Button (No Ripple effect)", ToastAndroid.SHORT);
							console.log("Default No Ripple Icon FAButton");
						}
					}
					/>

				<FloatingButtonAndroid
					style={{ height: 100, width: 100 }}
					backgroundColor='#ffff0000'
					rippleColor='black'
					icon='ic_reply_all_black_24dp'
					onPress={
						() => {
							ToastAndroid.show("Event onPress: Native Android FloatingAction Button (Custom size)", ToastAndroid.SHORT);
							console.log("Custom Ripple FAButton");
						}
					}
					/>

				<FloatingButtonAndroid
					style={{ height: 40, width: 70 }}
					backgroundColor='darkgray'
					rippleColor='green'
					icon='ic_add_white_24dp'
					rippleEffect={true}
					onPress={
						() => {
							ToastAndroid.show("Event onPress: Native Android FloatingAction Button (Custom size [deformed])", ToastAndroid.SHORT);
							console.log("Custom Ripple FAButton");
						}
					}
					/>
			</View>
		);
	}
}
