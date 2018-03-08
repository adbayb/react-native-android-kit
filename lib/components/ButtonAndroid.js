import React, {
	PureComponent
} from "react";
import {
	View,
	TouchableWithoutFeedback,
	requireNativeComponent
} from "react-native";
import PropTypes from 'prop-types';

const NativeComponent = requireNativeComponent("ButtonAndroid", ButtonAndroid, {});

export class ButtonAndroid extends PureComponent {
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
