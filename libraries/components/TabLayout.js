import React from 'react-native';
import {
	Component,
	PropTypes,
	View,
	ViewPagerAndroid,
} from 'react-native';

const RNAKTabLayout = React.requireNativeComponent('TabLayoutAndroid', TabLayout, {});

export default class TabLayout extends Component {
	//static variable non supporté par es6, il faut utiliser méthode static get pour en simuler le comportement:
	/*static get REF_VIEWPAGER() {
	 return 'ViewPager';
	 }*/
	//Mais babel accepte pour react les variables statiques: cf. http://babeljs.io/blog/2015/06/07/react-on-es6-plus/
	static REF_VIEWPAGER = 'refViewPager';
	static REF_TABLAYOUT = 'refTabLayout';
	static propTypes = {
		...View.propTypes,
	};
	static defaultProps = {
		height: 60
	};

	constructor(props) {
		super(props);
	}

	componentDidMount() {
		//Nous dispatchons nos commandes à notre code natif lorsque le composant
		//est monté (pour être certain que tous les composants ont bien été référencés):
		this.attachViewPager();
	}

	attachViewPager() {
		//findNodeHandle retourne l'id du node référencé dans render du composant:
		//let viewPagerId = React.findNodeHandle(this.refs.refViewPager);
		let viewPagerId = React.findNodeHandle(this.refs[TabLayout.REF_VIEWPAGER]);

		//On envoie l'id du composant ViewPager à notre composant natif TabLayoutAndroid
		//pour construire notre TabLayout avec notre ViewPager via la commande native setupWithViewPager:
		React.UIManager.dispatchViewManagerCommand(
			React.findNodeHandle(this.refs[TabLayout.REF_TABLAYOUT]),
			React.UIManager['TabLayoutAndroid'].Commands['setupWithViewPager'],
			[viewPagerId]
		);
		//React.UIManager['TabLayoutAndroid'].Commands['setupWithViewPager'] <=> React.UIManager.TabLayoutAndroid.Commands.setupWithViewPager
	}

	//Inutile de checker si les enfants directs sont de type View car le check est déjà réalisé côté ViewPager :):
	render() {
		/* ViewPagerAndroid ne peut pas être en parent sinon erreur: "Each ViewPager child must be a <View>"
		 Or RNAKTabLayout est de type TabLayout:*/
		//Aucun poids n'est attribué à RNAKTabLayout car il a une hauteur fixe de 50 par défaut;
		//ViewPager a un poids flex égal à 1 (flex:1 ~= flex-grow:1) pour lui permettre
		//tout l'espace libre restant du composant root View:
		return (
			<View style={{flex:1}}>
				<RNAKTabLayout ref={TabLayout.REF_TABLAYOUT}
					{...this.props}>
				</RNAKTabLayout>
				<ViewPagerAndroid ref={TabLayout.REF_VIEWPAGER} style={{flex:1}}>
					{this.props.children}
				</ViewPagerAndroid>
			</View>
		);
	}
}
