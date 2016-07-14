import React, {Component, PropTypes, View, ViewPagerAndroid} from "react-native";

const RNAKTabLayout = React.requireNativeComponent('TabLayoutAndroid', TabLayout, {});

export class TabLayout extends Component {
	static REF_VIEWPAGER = 'refViewPager';
	static REF_TABLAYOUT = 'refTabLayout';
	static propTypes = {
		...View.propTypes,
		...ViewPagerAndroid.propTypes,
		backgroundColor: React.PropTypes.string,
		indicatorTabColor: React.PropTypes.string,
		indicatorTabHeight: React.PropTypes.number,
		scrollable: React.PropTypes.bool,
		backgroundImage: React.PropTypes.string,
		center: React.PropTypes.bool
	};
	static defaultProps = {
		height: 60,
	};

	constructor(props) {
		super(props);
		this.tabsSettings = new Array();
	}

	componentDidMount() {
		this.attachViewPager();
	}

	manageChildren() {
		let children = this.props.children;
		if(children) {
			if(!this.containMixViews(children)) {
				if(Array.isArray(this.props.children)) {
					this.props.children.forEach((obj, key, array) => {
						this.tabsSettings.push(this.getChildProps(obj));
					});
				}
				else
					this.tabsSettings.push(this.getChildProps(children));

				return children;
			}

			console.warn('TabLayoutAndroid View must only have TabAndroid as direct children');
			return null;
		}
		else {
			console.warn('No Children, use TabAndroid tag to add some children');
			return null;
		}
	}

	getChildProps(child) {
		if(child) {
			let tabSettings = new Object;
			for(let propKey in child.props) {
				let propValue = child.props[propKey];
				if(typeof propValue !== 'object')
					tabSettings[propKey] = propValue;
			}

			return tabSettings;
		}
		console.warn('No Children, use TabAndroid tag to add some children');
		return null;
	}

	containMixViews(children) {
		if(children) {
			if(Array.isArray(children)) {
				return children.some((obj, key, array) => {
					if(obj.type.name !== 'TabAndroid' || obj.type.__proto__.name !== 'Tab')
						return true;
				});
			}
			else {
				if(children.type.name !== 'TabAndroid' || children.type.__proto__.name !== 'Tab')
					return true;
			}
		}

		return false;
	}

	attachViewPager() {
		let viewPagerId = React.findNodeHandle(this.refs[TabLayout.REF_VIEWPAGER]);

		React.UIManager.dispatchViewManagerCommand(
			React.findNodeHandle(this.refs[TabLayout.REF_TABLAYOUT]),
			React.UIManager['TabLayoutAndroid'].Commands['setupWithViewPager'],
			[viewPagerId, this.tabsSettings]
		);
	}

	render() {
		let {
			backgroundColor,
			indicatorTabColor,
			indicatorTabHeight,
			scrollable,
			backgroundImage,
			center,
			initialPage,
			keyboardDismissMode,
			onPageScroll,
			onPageSelected,
			...viewProps
		} = this.props;

		return (
			<View style={{flex:1}}>

				<RNAKTabLayout ref={TabLayout.REF_TABLAYOUT}
					{...viewProps}
							   backgroundColor={backgroundColor}
							   indicatorTabColor={indicatorTabColor}
							   indicatorTabHeight={indicatorTabHeight}
							   scrollable={scrollable}
							   backgroundImage={backgroundImage}
							   center={center}>
				</RNAKTabLayout>

				<ViewPagerAndroid ref={TabLayout.REF_VIEWPAGER}
								  style={{flex:1}}
								  initialPage={initialPage}
								  keyboardDismissMode={keyboardDismissMode}
								  onPageScroll={onPageScroll}
								  onPageSelected={onPageSelected}>

					{this.manageChildren()}
				</ViewPagerAndroid>

			</View>
		);
	}
}

export class Tab extends Component {
	static propTypes = {
		...View.propTypes,
		text: React.PropTypes.string,
		textSize: PropTypes.number,
		icon: React.PropTypes.string,
		iconPosition: React.PropTypes.string,
		textColor: React.PropTypes.string,
		selectedTextColor: React.PropTypes.string,
		customView: React.PropTypes.bool
	};
	static defaultProps = {
		textColor: 'grey',
		selectedTextColor: 'black',
		customView: true
	};

	constructor(props) {
		super(props);
	}

	render() {
		return (
			<View {...this.props}>
				{this.props.children}
			</View>
		);
	}
}
