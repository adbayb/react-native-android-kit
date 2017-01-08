import React, {
	Component,
	PropTypes
} from "react";
import {
	View,
	ScrollView,
	requireNativeComponent
} from "react-native";

const { contentContainerStyle } = ScrollView.propTypes;
console.log("AYOUBBBBB", contentContainerStyle);
const NativeComponent = requireNativeComponent("NestedScrollView", NestedScrollView, {});

export class NestedScrollView extends Component {
	static propTypes = {
		...View.propTypes,
		contentContainerStyle,
		children: PropTypes.node
	};

	renderContainer(content) {
		return (
			<View collapsable={false}>
				{content}
			</View>
		);
	}

	render() {
		const { children } = this.props;

		return (
			<NativeComponent>
				{this.renderContainer(children)}
			</NativeComponent>
		);
	}
}