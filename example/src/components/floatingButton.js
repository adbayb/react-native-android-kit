import React from "react";
import {
	StyleSheet, 
	View, 
	ToastAndroid
} from "react-native";
import { FloatingButtonAndroid } from "react-native-android-kit";

class FloatingButtonExample extends React.Component {
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
					style={styles.fab}
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
					style={[styles.fab, { height: 100, width: 100 }]}
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
					style={[styles.fab, { height: 40, width: 70 }]}
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

const styles = StyleSheet.create({
	container: {
		flex: 3,
		flexDirection: "column",
		alignItems: "center",
		paddingTop: 10,
		top: -380
	},

	fab: {
		marginTop: 10,
		height: 50,
		width: 50
	}
});

export default FloatingButtonExample;
