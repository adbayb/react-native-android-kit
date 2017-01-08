import React, {
	PureComponent,
	PropTypes
} from "react";
import {
	View,
	TouchableWithoutFeedback,
	requireNativeComponent
} from "react-native";

const NativeComponent = requireNativeComponent("Button", Button, {});

export class Button extends PureComponent {
	static propTypes = {
		...View.propTypes,
		...TouchableWithoutFeedback.propTypes,
		backgroundColor: PropTypes.string,
		textColor: PropTypes.string,
		text: PropTypes.string,
		textSize: PropTypes.number
	};
	static defaultProps = {
		textSize: 15,
		textColor: "black",
	};

	constructor(props) {
		super(props);
	}

	render() {
		return (
			<TouchableWithoutFeedback {...this.props}>
				<NativeComponent
					style={this.props.style}
					backgroundColor={this.props.backgroundColor}
					textColor={this.props.textColor}
					textSize={this.props.textSize}
					text={this.props.text} />
			</TouchableWithoutFeedback>
		);
	}
}
