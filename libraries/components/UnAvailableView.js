/**
 * Common implementation for a simple stubbed view. Simply applies the view's styles to the inner
 * View component and renders its children.
 *
 * @providesModule UnAvailableView
 */
'use strict';

const React = require('react-native');
const {
	Component,
	View,
	StyleSheet,
	Text
} = React;

class UnAvailableView extends React.Component {
	render() {
		return (
			<View style={[styles.unavailableview, this.props.style]}>
				<Text>
					One or more view component(s) currently not available
				</Text>
			</View>
		);
	}
}

const styles = StyleSheet.create({
	unavailableview: {
		borderWidth: 5,
		borderColor: 'red',
		alignSelf: 'flex-start',
	}
});

module.exports = UnAvailableView;
