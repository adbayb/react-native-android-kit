import React from "react";
import {
	StyleSheet,
	View,
	ToolbarAndroid,
	ToastAndroid
} from "react-native";
import { Button, FloatingButton, TabLayout } from "react-native-android-kit";
import Example from "./views/example";
import Test from "./views/test";

const styles = StyleSheet.create({
	button: {
		container: {
			flex: 1,
			flexDirection: "row"
		},
		item: {
			flex: 1,
			height: 60,
			alignSelf: "flex-end"
		}
	},
	floatingButton: {
		container: {
			// position in React Native is similar to regular CSS, but everything is set 
			// to relative by default, so absolute positioning is always just relative to the parent:
			// position: "absolute",
			flex: 1,
			flexDirection: "row",
			alignItems: "center",
			justifyContent: "space-around"
		}
	},
	tabLayout: {
		container: {
			height: 80
		}
	},
	container: {
		flex: 4,
		alignItems: "stretch",
		backgroundColor: "#F5FCFF",
	},
	toolbar: {
		backgroundColor: "#009688",
		height: 60,
	}
});

export default class App extends React.Component {
	renderButton() {
		const { button: { container, item } } = styles;

		return (
			<View style={container}>
				<Button
					style={item}
					text='Default Button'
					onPress={
						() => {
							ToastAndroid.show("Event onPress: Native Android Button (Default)", ToastAndroid.SHORT);
							console.log("Default Button");
						}
					}
					/>
				<Button
					style={item}
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

	renderFloatingButton() {
		const { floatingButton: { container } } = styles;

		return (
			<View style={container}>
				<FloatingButton
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

				<FloatingButton
					icon='ic_home_black_24dp'
					rippleEffect={false}
					onPress={
						() => {
							ToastAndroid.show("Event onPress: Native Android FloatingAction Button (No Ripple effect)", ToastAndroid.SHORT);
							console.log("Default No Ripple Icon FAButton");
						}
					}
					/>

				<FloatingButton
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

				<FloatingButton
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

	renderTabLayout() {
		const { tabLayout: { container } } = styles;

		return (
			<TabLayout style={container} backgroundColor="#009688" indicatorTabColor="#ffc400"
				indicatorTabHeight={2} scrollable={false} center={false}>

				<TabLayout.Item
					text="Tab1"
					textSize={16}
					textColor="white"
					selectedTextColor="#ffc400"
					icon="ic_home_black_24dp"
					iconPosition="left">

					<Example description="ButtonAndroid">
						{this.renderButton()}
					</Example>

				</TabLayout.Item >

				<TabLayout.Item
					text="Tab2"
					textSize={16}
					textColor="white"
					selectedTextColor="#ffc400"
					icon="ic_important_devices_black_24dp"
					iconPosition="left">

					<Example description="FloatingButtonAndroid">
						{this.renderFloatingButton()}
					</Example>

				</TabLayout.Item >

				<TabLayout.Item
					text="Tab3"
					textSize={16}
					textColor="white"
					selectedTextColor="#ffc400"
					icon="ic_build_black_24dp"
					iconPosition="left">

					<Example description="ButtonAndroid">
						<Example description="Nothing to show" />
					</Example>

				</TabLayout.Item >

			</TabLayout>
		);
	}

	renderTest() {
		return <Test />;
	}

	render() {
		return (
			<View style={styles.container}>
				<ToolbarAndroid
					title="React Native Android Kit -- Examples"
					style={styles.toolbar} />
				{this.renderTabLayout()}
			</View>
		);
		// return this.renderTest();
	}
}
