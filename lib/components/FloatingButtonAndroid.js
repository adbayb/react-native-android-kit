import React, {
	PureComponent
} from "react";
import {
	View,
	TouchableWithoutFeedback,
	requireNativeComponent
} from "react-native";
import PropTypes from 'prop-types';

const NativeComponent = requireNativeComponent("FloatingButtonAndroid", FloatingButtonAndroid, {});

export class FloatingButtonAndroid extends PureComponent {
	static propTypes = {
		...View.propTypes,
		...TouchableWithoutFeedback.propTypes,
		backgroundColor: PropTypes.string,
		rippleColor: PropTypes.string,
		icon: PropTypes.string,
		hidden: PropTypes.bool,
		rippleEffect: PropTypes.bool
	};
	static defaultProps = {
		style: { width: 50, height: 50 },
		hidden: false,
		rippleEffect: true
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
					rippleColor={this.props.rippleColor}
					icon={this.props.icon}
					hidden={this.props.hidden}
					rippleEffect={this.props.rippleEffect} />
			</TouchableWithoutFeedback>
		);
	}
}
