import React, {
	Component,
	PropTypes
} from "react";
import {
	View,
	requireNativeComponent
} from "react-native";

const NativeComponent = requireNativeComponent("RNAKNestedScrollView", NestedScrollView, {});

export class NestedScrollView extends Component {
	static propTypes = {
		...View.propTypes,
		contentContainerStyle: View.propTypes.style,
		children: PropTypes.node
	};

	renderContainer(content, style) {
		// collapsable=false is important to insure that view exists in hierarchy (not optimized (for example view is removed if no 
		// background color and the grandfather is already a ViewGroup) 
		// by React via NativeViewHierarchyOptimizer) and don't get "Scrollview can host only one direct child" error:
		return (
			<View style={style} collapsable={false}>
				{content}
			</View>
		);
	}

	render() {
		const { children, contentContainerStyle, ...viewProps } = this.props;

		return (
			<NativeComponent {...viewProps}>
				{this.renderContainer(children, contentContainerStyle)}
			</NativeComponent>
		);
	}
}