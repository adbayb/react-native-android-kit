import React, {
	Component,
	PropTypes
} from "react";
import {
	StyleSheet,
	Text,
	View
} from "react-native";

const styles = StyleSheet.create({
	container: {
		flex: 1
	},
	header: {
		flex: 2,
		paddingTop: 10,
		paddingBottom: 10
	},
	content: {
		flex: 10
	},
	text: {
		fontWeight: "bold",
		textAlign: "center",
		fontSize: 20
	}
});

export default class Example extends Component {
	static propTypes = {
		description: PropTypes.string,
		children: PropTypes.node
	}
	render() {
		const { description, children } = this.props;

		return (
			<View style={styles.container}>
				<View style={styles.header}>
					<Text style={styles.text}> {description} </Text>
				</View>
				<View style={styles.content}>
					{children}
				</View>
			</View>
		);
	}
}