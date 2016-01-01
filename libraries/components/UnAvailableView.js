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

class UnAvailableView extends Component {
  setNativeProps() {
    // Do nothing.
    // This method is required in order to use this view as a Touchable* child.
    // See ensureComponentIsNative.js for more info
  }
  
  render() {
    //Workaround require cycle from requireNativeComponent
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
