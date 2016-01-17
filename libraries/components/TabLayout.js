import React from 'react-native';
import {
	Component,
	PropTypes,
	View,
	TouchableWithoutFeedback,
	requireNativeComponent,
	ViewPagerAndroid
} from 'react-native';

const TabLayoutAndroid = requireNativeComponent('TabLayoutAndroid', TabLayout, {});

export default class TabLayout extends Component {
	static propTypes = {
		...View.propTypes,
	};
	
	static defaultProps = {
		height: 50,
	};
	
	constructor(props) {
		super(props);
	}

	render() {
		/* ViewPagerAndroid ne peut pas être en parent car erreur: Each ViewPager child must be a <View>
		Or RNAKTabLayout est de type TabLayout:*/
		return (
			<TabLayoutAndroid
				{...this.props}>
			</TabLayoutAndroid>
		);
	}
}
