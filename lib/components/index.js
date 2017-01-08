import { Platform } from "react-native";

if (Platform.OS !== "android") {
	throw new Error(
		"react-native-android-kit focuses on Android platform. " +
		"iOS platform not supported. Please use '.android.js' filename extension " +
		"or 'Platform.OS' api to manage Android components vs iOS one's."
	);
}

export * from "./Button.js";
export * from "./FloatingButton.js";
export * from "./TabLayout.js";
export * from "./NestedScrollView.js";
