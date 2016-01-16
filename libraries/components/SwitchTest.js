import React, {
	PropTypes,
	View,
	NativeMethodsMixin,
	requireNativeComponent,
	Switch
} from 'react-native';

var RKSwitch = requireNativeComponent('SwitchAndroidTest', SwitchTest, {
	nativeOnly: {
		on: true,
		enabled: true,
	}
});

//Exemple d'héritage d'un composant de base React-Native (ici Switch). 
//Seul la fonction render() est overridée:
export default class SwitchTest extends Switch {
	_onChange(event) {
		super._onChange(event);
	}

	render() {
		return (
			<RKSwitch
				style={this.props.style}
				enabled={!this.props.disabled}
				on={this.props.value}
				onChange={this._onChange}
				testID={this.props.testID}
				onStartShouldSetResponder={() => true}
				onResponderTerminationRequest={() => false}
			/>
		);
	}
}

SwitchTest.mixins = [NativeMethodsMixin];
SwitchTest.propTypes = {
	...View.propTypes,
	/**
	 * Boolean value of the switch.
	 */
	value: PropTypes.bool,
	/**
	 * If `true`, this component can't be interacted with.
	 */
	disabled: PropTypes.bool,
	/**
	 * Invoked with the new value when the value chages.
	 */
	onValueChange: PropTypes.func,
	/**
	 * Used to locate this view in end-to-end tests.
	 */
	testID: PropTypes.string,
};
SwitchTest.defaultProps = {
	value: false,
	disabled: false,
};
