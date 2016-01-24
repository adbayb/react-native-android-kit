import React from 'react-native';
import {
	Component,
	PropTypes,
	View,
	TouchableWithoutFeedback,
	NativeMethodsMixin,
	requireNativeComponent
} from 'react-native';

//requireNativeComponent permet de faire l'interface entre le code javascript et le code java natif.
//Le première paramètre prend le nom de la vue définie dans notre SimpleViewManager du composant implémenté en java
//Le deuxième paramètre correspond à l'objet javascript définissant notre composant react-native en js.
//Un troisième paramètre optionnel permet de spécifier les propriétés accessibles (via un objet littéral contenant nativeOnly:...)
//seulement via le code java et non via l'api javascript exposé au futur utilisateur dev du composant js.
//cf. https://facebook.github.io/react-native/docs/native-components-android.html#5-implement-the-javascript-module
const RNAKButton = requireNativeComponent('ButtonAndroid', Button, {});

export default class Button extends Component {
	//propTypes permet de définir les contraintes liées aux types des propriétés de notre componsant.
	//propTypes permet une validation par le framework React-Native des propriétés (de leur typage).
	//cf. https://facebook.github.io/react/docs/reusable-components.html
	//propTypes est un objet littéral (objet donnant une liste clé: valeur entouré par des braces).
	//An object literal is a list of zero or more pairs of property names and associated values of an object
	//Les objets littéraux permettent de réduire le nombre de ligne de codes, en effet:
	//var x = {'float': 'right'} is the nicer/shorter form of var x = new Object(); x.float = 'right';
	static propTypes = {
		//On récupère par héritage les contraintes des propriétés du componsant react-native View. 
		//Les propriétés du composant View sont affectées à notre composant en natif 
		//via l'héritage des propriétés de SimpleViewManager dans le code Java. 
		//On peut donc affecter la liste des propriétés de View à notre composant réutilisable Button:
		...View.propTypes,
		...TouchableWithoutFeedback.propTypes,
		//Propriétés custom définies dans ButtonManager.java:
		backgroundColor: PropTypes.string,
		textColor: PropTypes.string,
		text: PropTypes.string,
		textSize: PropTypes.number
	};

	//Définition des valeurs par défault des propriétés.
	//Doit être en static dans le cas où Button est une classe (ES6).
	//Si utilisation de React.createClass, on peut les définir en utilisant 
	//getDefaultProps: function():
	static defaultProps = {
		//style: {width:200,height:50},
		textSize: 15,
		textColor: 'black',
	};
	//Les propriétés de largeur, hauteur... des composants natifs react (View...) sont exprimées 
	//en DIP et non en pixel. Le code Java fb se charge ensuite de traduire les valeurs dip en pixel
	//=> cf. PixelUtil.toPixelFromDIP fonction dans LayoutShadowNode.java

	//Constructeur: on passe les propriétés spécifiés lors de 
	//l'utilisation de notre composant <ButtonAndroid> à notre objet correspondant:
	constructor(props) {
		super(props);
	}

	render() {
		//console.log(this.props);//Debug: sous chrome F12 (onglet console)

		//On affecte les propriétés modifiables via notre composant. 
		//Lors de la réutilisation de notre composant ButtonAndroid, 
		//les propriétés telles que style, color (...) pourront donc être customisées:

		//Le transfert de l'ensemble des propriétés saisies dans la balise <ButtonAndroid> 
		//vers un sous-composant le constituant (TouchableWithoutFeedback ou RNAKButton ici) se fait via l'opérateur 
		//{...blabla} donc ici via {...this.props}.
		//Le sous-composant recevant les propriétés n'utilisera que les propriétés qui lui sont valides 
		//(i.e. définit en native). Par exemple, si ButtonAndroid est défini par:
		//<ButtonAndroid style={styles.button} text='Button' onPress={() => var toto="Callback"}/>
		//le sous-composant TouchableWithoutFeedback sera alors représenté par 
		//<TouchableWithoutFeedback style={styles.button} text='Button' onPress={() => var toto="Callback"}/>
		//mais il ne prendra en compte que les attributs le caractérisant, ici onPress 
		//(cf. https://facebook.github.io/react-native/docs/touchablewithoutfeedback.html#content)

		//Pour le sous-composant de gestion des clicks et évènements TouchableWithoutFeedback: nous allons transférer 
		//l'ensemble des propriété pour autoriser potentiellement notre dev à utiliser l'ensemble des propriétés valides de TouchableWithoutFeedback
		//Pour notre bouton natif (RNAKButton), nous n'allons pas transférer l'ensembles des propriétés spécifiés lors de la déclaration <ButtonAndroid> 
		//pour ne restreindre la modification que de certaines propriétés de notre composant.
		//En l'occurrence ici, on restreint les propriétés de View à seulement la propriété style
		//+ nos propriétés customs définies en natif (textColor, text, color, textSize).
		//En effet, notre composant natif hérite de SimpleViewManager. Or SimpleViewManager définit le composant View 
		//qui contient tout un tas de propriétés qui me semble inutile dans mon cas (cf. https://facebook.github.io/react-native/docs/view.html#content)
		//Or en mettant {...this.props}, on autorise potentiellement le futur dev à intéragir sur ces propriétés. Pour plus de sécurité 
		//et assurer un bon comportement du composant, on restreint les propriétés possibles en les listant un par un:
		return (
			<TouchableWithoutFeedback {...this.props}>
				<RNAKButton
					style={this.props.style}
					backgroundColor={this.props.backgroundColor}
					textColor={this.props.textColor}
					textSize={this.props.textSize}
					text={this.props.text}/>
			</TouchableWithoutFeedback>
		);
	}
}
