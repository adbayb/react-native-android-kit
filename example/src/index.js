import React, {
	PureComponent
} from "react";
import {
	View,
	Text,
	ToolbarAndroid,
	UIManager
} from "react-native";
import { Button, FloatingButton, TabLayout, NestedScrollView } from "react-native-android-kit";
import Example from "./components/Example";
import Test from "./components/test";

console.log(UIManager);

const styles = {
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
			flex: 1
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
};

export default class App extends PureComponent {
	easterCount = 0;
	state = {
		log: "No Action",
		easterEgg: false
	};

	renderButton() {
		const { button: { container, item } } = styles;

		return (
			<View style={container}>
				<Button
					style={item}
					text='Default Button'
					onPress={
						() => {
							this.easterCount++;
							this.setState(() => {
								const updatedState = {};
								if (this.easterCount > 2) {
									updatedState.easterEgg = true;
								}

								return {
									...updatedState,
									log: "onPress: Native Android Button (Default)"
								};
							});
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
							this.setState({
								log: "onPress: Native Android Button (Custom)"
							});
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
							this.setState({
								log: "onPress: Native Android FloatingAction Button (Ripple effect)"
							});
						}
					}
				/>

				<FloatingButton
					icon='ic_home_black_24dp'
					rippleEffect={false}
					onPress={
						() => {
							this.setState({
								log: "onPress: Native Android FloatingAction Button (No Ripple effect)"
							});
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
							this.setState({
								log: "onPress: Native Android FloatingAction Button (Custom size)"
							});
						}
					}
				/>
			</View>
		);
	}

	renderTabLayout() {
		const { tabLayout: { container } } = styles;
		const { log } = this.state;

		return (
			<TabLayout style={container} height={80} backgroundColor="#009688" indicatorTabColor="#ffc400"
				indicatorTabHeight={2} scrollable={false} center={false}>

				<TabLayout.Item
					text="Tab1"
					textSize={16}
					textColor="white"
					selectedTextColor="#ffc400"
					icon="ic_home_black_24dp"
					iconPosition="left">

					<Example log={log} description="ButtonAndroid">
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

					<Example log={log} description="FloatingButtonAndroid">
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

					<Example log={log} description="NestedScrollView">
						<NestedScrollView style={{ backgroundColor: "brown", flex: 1 }} contentContainerStyle={{ height: 500 }} >
							<Text> NestedScrollView1 </Text>
							<NestedScrollView style={{ top: 50, backgroundColor: "grey", height: 200 }} contentContainerStyle={{ height: 1000 }} >
								<Text> NestedScrollView2 </Text>
							</NestedScrollView>
						</NestedScrollView>
					</Example>

				</TabLayout.Item >

			</TabLayout>
		);
	}

	renderTest() {
		return <Test />;
	}

	render() {
		const { easterEgg } = this.state;
		if (easterEgg) {
			return this.renderTest();
		}

		return (
			<View style={styles.container}>
				<ToolbarAndroid
					title="React Native Android Kit -- Examples"
					style={styles.toolbar} />
				{this.renderTabLayout()}
			</View>
		);
	}
}
