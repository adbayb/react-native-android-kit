import React from "react";
import {
	StyleSheet,
	View,
	ToastAndroid
} from "react-native";
import { ButtonAndroid } from "react-native-android-kit";

const styles = StyleSheet.create({
	container: {
		flex: 1,
		flexDirection: "row"
	},
	buttoncustom: {
		flex: 1,
		height: 60,
		alignSelf: "flex-end"
	}
});

export default class Button extends React.Component {
	render() {
		return (
			<View style={styles.container}>
				<ButtonAndroid
					style={styles.buttoncustom}
					text='Default Button'
					onPress={
						() => {
							ToastAndroid.show("Event onPress: Native Android Button (Default)", ToastAndroid.SHORT);
							console.log("Default Button");
						}
					}
					/>
				<ButtonAndroid
					style={styles.buttoncustom}
					textColor='red'
					backgroundColor='#FF009688'
					textSize={12}
					text='Custom Button'
					onPress={
						() => {
							ToastAndroid.show("Event onPress: Native Android Button (Custom)", ToastAndroid.SHORT);
							console.log("Custom Button");
						}
					}
					/>
			</View>
		);
	}
}
