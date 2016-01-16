import React from 'react-native';
import {
	Component,
	View,
	StyleSheet,
	Text
} from 'react-native';

const styles = StyleSheet.create({
	unavailableview: {
		borderWidth: 5,
		borderColor: 'red',
		alignSelf: 'flex-start',
	}
});

export default class UnAvailableView extends Component {
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
