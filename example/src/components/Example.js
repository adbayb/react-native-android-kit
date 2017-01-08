import React, {
	PureComponent,
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
		flex: 4,
		paddingTop: 10,
		paddingBottom: 10
	},
	content: {
		flex: 10
	},
	title: {
		fontWeight: "bold",
		textAlign: "center",
		fontSize: 20
	},
	text: {
		top: 5,
		fontWeight: "bold",
		color: "orange"
	}
});

export default class Example extends PureComponent {
	static propTypes = {
		description: PropTypes.string,
		log: PropTypes.string,
		children: PropTypes.node
	};

	render() {
		const { description, log, children } = this.props;

		return (
			<View style={styles.container}>
				<View style={styles.header}>
					<Text style={styles.title}>{description}</Text>
					<Text style={styles.text}>
						Action log: {log}
					</Text>
				</View>
				<View style={styles.content}>
					{children}
				</View>
			</View>
		);
	}
}