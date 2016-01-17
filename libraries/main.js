//Avec ES5:
//exports.Components = require('./components');
//exports.SwitchAndroidTest = exports.Components.Switch;
//exports.ButtonAndroid = exports.Components.Button;

//Avec ES6:
import {ButtonComponent, FloatingButtonComponent, TabLayoutComponent, SwitchComponent} from './components';

export class ButtonAndroid extends ButtonComponent {}
export class FloatingButtonAndroid extends FloatingButtonComponent {}
export class TabLayoutAndroid extends TabLayoutComponent {}
export class SwitchAndroidTest extends SwitchComponent {}