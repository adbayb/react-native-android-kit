import {Platform} from "react-native";
import Button from "./Button";
import FloatingButton from "./FloatingButton";
import {TabLayout, Tab} from "./TabLayout";
import UnAvailableView from "./UnAvailableView";

if(Platform.OS === 'android') {
	exports.ButtonAndroid = class ButtonAndroid extends Button {};
	exports.FloatingButtonAndroid = class FloatingButtonAndroid extends FloatingButton {};
	exports.TabLayoutAndroid = class TabLayoutAndroid extends TabLayout {};
	exports.TabAndroid = class TabAndroid extends Tab {};
}
else {
	exports.ButtonAndroid = UnAvailableView;
	exports.FloatingButtonAndroid = UnAvailableView;
	exports.TabLayoutAndroid = UnAvailableView;
	exports.TabAndroid = UnAvailableView;
}
