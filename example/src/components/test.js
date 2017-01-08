import React, {
	Component
} from "react";
import {
	View,
	Text,
	requireNativeComponent
} from "react-native";

import { NestedScrollView } from "react-native-android-kit";

console.log("YOYOYOY", NestedScrollView);

const NativeComponent = requireNativeComponent("NestedScrollViewAndroid", Test, {});

export default class Test extends Component {
	state = {
		height: 200
	};

	componentDidMount() {
		//this.debug();
		setTimeout(() => this.setState({
			height: 400
		}), 5000);
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

		// collapsable=false is important to insure that view exists in hierarchy (not optimized (for example view is removed if no 
		// background color and the grandfather is already a ViewGroup) 
		// by React via NativeViewHierarchyOptimizer) and don't get "Scrollview can host only one direct child" error:
		return (
			<NativeComponent style={{ position: "absolute", top: 10, left: 10, backgroundColor: "blue", height: this.state.height, width: 200 }}>
				<View style={{ height: 1000, width: 1000 }} collapsable={false}>
					<Text> TOOTOTOTOTOTTOTTOOdasdasT </Text>
					<Text> TOOTOTOTOTOTTOTTOOdasdasT </Text>
					<Text> Ayoub </Text>
				</View>
			</NativeComponent>
		);
	}
}