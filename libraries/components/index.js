import {Platform} from "react-native";
import Button from "./Button";
import FloatingButton from "./FloatingButton";
import {TabLayout, Tab} from "./TabLayout";
import UnAvailableView from "./UnAvailableView";

if(Platform.OS === 'android') {
	exports.ButtonComponent = Button;
	exports.FloatingButtonComponent = FloatingButton;
	exports.TabLayoutComponent = TabLayout;
	exports.TabComponent = Tab;
}
else {
	exports.ButtonComponent = UnAvailableView;
	exports.FloatingButtonComponent = UnAvailableView;
	exports.TabLayoutComponent = UnAvailableView;
	exports.TabComponent = UnAvailableView;
}
