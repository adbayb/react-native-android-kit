//Avec ES5:
//exports.Components = require('./components');
//exports.SwitchAndroidTest = exports.Components.Switch;
//exports.ButtonAndroid = exports.Components.Button;

//Avec ES6:
import {ButtonComponent, SwitchComponent} from './components';

export class ButtonAndroid extends ButtonComponent {}
export class SwitchAndroidTest extends SwitchComponent {}