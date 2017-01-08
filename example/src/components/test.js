import React, {
	Component
} from "react";
import {
	requireNativeComponent
} from "react-native";

const NativeComponent = requireNativeComponent("RNAKCoordinatorLayout", Test, {});

export default class Test extends Component {
	render() {
		return (
			<NativeComponent style={{ flex: 1 }} />
		);
	}
}