//NodeJS, lors de l'appel à require sur un dossier, charge par défaut index.js si aucun fichier js n'est spécifié dans le path du require:
//Pour plus d'informations: http://www.bennadel.com/blog/2169-where-does-node-js-and-require-look-for-modules.htm :

import React, {
	Platform
}  from 'react-native';
import Button from './Button';
import SwitchTest from './SwitchTest';
import UnAvailableView from './UnAvailableView';

//Nos composants sont valables que sur la plateforme Android ;)
if (Platform.OS === 'android') {
	//sous ES6 les imports et exports conditionnel ne sont pas acceptés (seulement en statique)
	//Pour contrepasser cette restriction, on utilise exports d'ES5:
	//export class ButtonComponent extends Button {}
	exports.ButtonComponent = Button;
	//exports.Switch = require('./SwitchTest');
	exports.SwitchComponent = SwitchTest;
}
else {
	exports.ButtonComponent = UnAvailableView;
	exports.SwitchComponent = UnAvailableView;
	//exports.Switch = require('./UnAvailableView');
}
