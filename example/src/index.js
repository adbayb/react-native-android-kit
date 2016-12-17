import React from "react";
import {
	StyleSheet,
	Text,
	View,
	ToolbarAndroid
} from "react-native";
import ButtonExample from "./components/button";
import FloatingButtonExample from "./components/floatingButton";
import { TabLayoutAndroid } from "react-native-android-kit";

class Example extends React.Component {
	render() {
		return (
			<View style={styles.container}>
				<ToolbarAndroid
					title="React Native Android Kit -- Examples"
					style={styles.toolbar} />

				<TabLayoutAndroid style={{ height: 80 }} backgroundColor="#009688" indicatorTabColor="#ffc400"
					indicatorTabHeight={2} scrollable={false} center={false}>

					<TabLayoutAndroid.Item text="Tab1" textSize={16} textColor="white" selectedTextColor="#ffc400"
						icon="ic_home_black_24dp" iconPosition="left">
						<Text>ButtonAndroid Examples</Text>
						<ButtonExample />
					</TabLayoutAndroid.Item>
					<TabLayoutAndroid.Item text="Tab2" textSize={16} textColor="white" selectedTextColor="#ffc400"
						icon="ic_important_devices_black_24dp" iconPosition="left">
						<Text>FloatingButtonAndroid Examples</Text>
						<Text>
							Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
							Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
							Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.

							Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo.
							Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt.
							Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem.
							Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur.
						</Text>
						<FloatingButtonExample />
					</TabLayoutAndroid.Item>
					<TabLayoutAndroid.Item text="Tab3" textSize={16} textColor="white" selectedTextColor="#ffc400"
						icon="ic_build_black_24dp" iconPosition="left">
						<Text>Hello, I'm the last tab: nothing to show</Text>
					</TabLayoutAndroid.Item>

				</TabLayoutAndroid>

			</View>
		);
	}
}

var styles = StyleSheet.create({
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

export default Example;
