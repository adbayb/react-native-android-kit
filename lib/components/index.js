import { Platform } from "react-native";

if (Platform.OS !== "android") {
	throw new Error(
		"react-native-android-kit focuses on Android platform. " +
		"iOS platform not supported. Please use '.android.js' filename extension " +
		"or 'Platform.OS' api to manage Android components vs iOS one's."
	);
}

export * from "./ButtonAndroid.js";
export * from "./FloatingButtonAndroid.js";
export * from "./TabLayoutAndroid.js";
