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
		color: PropTypes.string,
		rippleColor: PropTypes.string,
		icon: PropTypes.string,
		hide: PropTypes.bool,
		rippleEffect: PropTypes.bool
	};
	
	static defaultProps = {
		style: {width:50,height:50},
		hide: false,
		rippleEffect: true,
	};
	
	constructor(props) {
		super(props);
	}

	render() {
		return (
			<TouchableWithoutFeedback {...this.props}>
				<RNAKFloatingButton
					style={this.props.style}
					color={this.props.color}
					rippleColor={this.props.rippleColor}
					icon={this.props.icon}
					hide={this.props.hide}
					rippleEffect={this.props.rippleEffect}/>
			</TouchableWithoutFeedback>
		);
	}
}
