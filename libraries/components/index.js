//NodeJS, lors de l'appel à require sur un dossier, charge par défaut index.js si aucun fichier js n'est spécifié dans le path du require:
//Pour plus d'informations: http://www.bennadel.com/blog/2169-where-does-node-js-and-require-look-for-modules.htm :

import {Platform} from "react-native";
import SwitchTest from "./SwitchTest";
import Button from "./Button";
import FloatingButton from "./FloatingButton";
import {TabLayout, Tab} from "./TabLayout";
import UnAvailableView from "./UnAvailableView";

//Nos composants sont valables que sur la plateforme Android ;)
if(Platform.OS === 'android') {
	//sous ES6 les imports et exports conditionnel ne sont pas acceptés (seulement en statique)
	//Pour contrepasser cette restriction, on utilise exports d'ES5:
	//export class ButtonComponent extends Button {}
	//exports.Switch = require('./SwitchTest');
	exports.SwitchComponent = SwitchTest;
	exports.ButtonComponent = Button;
	exports.FloatingButtonComponent = FloatingButton;
	exports.TabLayoutComponent = TabLayout;
	exports.TabComponent = Tab;
}
else {
	exports.SwitchComponent = UnAvailableView;
	exports.ButtonComponent = UnAvailableView;
	exports.FloatingButtonComponent = UnAvailableView;
	exports.TabLayoutComponent = UnAvailableView;
}
