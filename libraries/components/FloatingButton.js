import React from 'react-native';
import {
	Component,
	PropTypes,
	View,
	TouchableWithoutFeedback,
	requireNativeComponent
} from 'react-native';

const RNAKFloatingButton = requireNativeComponent('FloatingButtonAndroid', FloatingButton, {});

export default class FloatingButton extends Component {
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
		style: {width: 50, height: 50},
		hidden: false,
		rippleEffect: true
	};

	constructor(props) {
		super(props);
	}

	render() {
		return (
			<TouchableWithoutFeedback {...this.props}>
				<RNAKFloatingButton
					style={this.props.style}
					backgroundColor={this.props.backgroundColor}
					rippleColor={this.props.rippleColor}
					icon={this.props.icon}
					hidden={this.props.hidden}
					rippleEffect={this.props.rippleEffect}/>
			</TouchableWithoutFeedback>
		);
	}
}
