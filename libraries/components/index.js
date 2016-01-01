//NodeJS, lors de l'appel à require sur un dossier, charge par défaut index.js si aucun fichier js n'est spécifié dans le path du require:
//Pour plus d'informations: http://www.bennadel.com/blog/2169-where-does-node-js-and-require-look-for-modules.htm :

const React = require('react-native');
const {
	Platform
} = React;

//Nos composants sont valables que sur la plateforme Android ;)
if(Platform.OS === 'android') {
	exports.Button = require('./Button');
	exports.Switch = require('./SwitchTest');
	exports.Tab = require('./Tab');
}
else {
	exports.Button = require('./UnAvailableView');
	exports.Switch = require('./UnAvailableView');
	exports.Tab = require('./UnAvailableView');
}
