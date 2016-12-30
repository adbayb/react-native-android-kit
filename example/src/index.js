import React from "react";
import {
	StyleSheet,
	View,
	ToolbarAndroid
} from "react-native";
import Button from "./components/button";
import FloatingButton from "./components/floatingButton";
import TabLayout from "./components/tabLayout";
import Example from "./components/example";
import Test from "./components/test";

const styles = StyleSheet.create({
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
	renderTabLayout() {
		return (
			<TabLayout items={[{
				icon: "ic_home_black_24dp",
				element: (
					<Example description="ButtonAndroid">
						<Button />
					</Example>
				)
			}, {
				icon: "ic_important_devices_black_24dp",
				element: (
					<Example description="FloatingButtonAndroid">
						<FloatingButton />
					</Example>
				)
			}, {
				icon: "ic_build_black_24dp",
				element: <Example description="Nothing to show" />
			}]} />
		);
	}

	renderTest() {
		return <Test />;
	}

	render() {
		// return (
		// 	<View style={styles.container}>
		// 		<ToolbarAndroid
		// 			title="React Native Android Kit -- Examples"
		// 			style={styles.toolbar} />
		// 		{this.renderTabLayout()}
		// 	</View>
		// );
		return this.renderTest();
	}
}
