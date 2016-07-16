import React, { Component } from "react";
import {
	AppRegistry
} from "react-native";
import Example from "./src";

class RNAKexamples extends Component {
	render() {
		return (
			<Example />
		);
	}
}

AppRegistry.registerComponent("RNAKexamples", () => RNAKexamples);
