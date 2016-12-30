import React, {
	Component
} from "react";
import {
	View,
	Text,
	ViewPagerAndroid,
	requireNativeComponent,
	findNodeHandle,
	UIManager
} from "react-native";

const NativeComponent = requireNativeComponent("CoordinatorLayout", CoordinatorLayout, {});

export default class CoordinatorLayout extends Component {
	static REF_COORDINATOR = "refCoordinator";

	componentDidMount() {
		//this.debug();
	}

	debug() {
		console.log("AYOUB", UIManager.CoordinatorLayout.Commands.debug);
		UIManager.dispatchViewManagerCommand(
			findNodeHandle(this.refs[CoordinatorLayout.REF_COORDINATOR]),
			UIManager.CoordinatorLayout.Commands.debug,
			null
		);
	}

	render() {
		// <ViewPagerAndroid
		// 	ref={CoordinatorLayout.REF_VIEWPAGER}
		// 	style={{ flex: 1 }}>
		// 	<View>
		// 		<Text> TOOTOTOTOTOTTOTTOOT </Text>
		// 	</View>
		// 	<View>
		// 		<Text> TEMP </Text>
		// 	</View>
		// </ViewPagerAndroid>
		return (
			<NativeComponent ref={CoordinatorLayout.REF_COORDINATOR} style={{ flex: 1, backgroundColor: "blue" }} />
		);
	}
}
