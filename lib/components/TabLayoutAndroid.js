import React, {
	PureComponent
} from "react";
import {
	View,
	ViewPagerAndroid,
	requireNativeComponent,
	findNodeHandle,
	UIManager
} from "react-native";
import PropTypes from 'prop-types';
import { elementOf, getDisplayName } from "../utils";

const NativeComponent = requireNativeComponent("TabLayoutAndroid", TabLayoutAndroid, {});

class TabAndroid extends PureComponent {
	static displayName = "TabLayoutAndroid.Item";
	static propTypes = {
		...View.propTypes,
		text: PropTypes.string,
		textSize: PropTypes.number,
		icon: PropTypes.string,
		iconPosition: PropTypes.string,
		textColor: PropTypes.string,
		selectedTextColor: PropTypes.string,
		customView: PropTypes.bool
	};
	static defaultProps = {
		textColor: "grey",
		selectedTextColor: "black",
		customView: true
	};

	render() {
		// Since ViewPagerAndroid needs View children, this component is just responsible for managing props
		// without rendering (TabLayoutAndroid is responsible to get TabAndroid children props and inject them into View components inside the 
		// ViewPagerAndroid container, cf. TabLayout->manageChildren())
		return null;
	}
}

export class TabLayoutAndroid extends PureComponent {
	static Item = TabAndroid;
	static REF_VIEWPAGER = "refViewPager";
	static REF_TABLAYOUT = "refTabLayout";
	static propTypes = {
		...View.propTypes,
		...ViewPagerAndroid.propTypes,
		backgroundColor: PropTypes.string,
		indicatorTabColor: PropTypes.string,
		indicatorTabHeight: PropTypes.number,
		scrollable: PropTypes.bool,
		backgroundImage: PropTypes.string,
		center: PropTypes.bool,
		children: PropTypes.oneOfType([
			elementOf(TabAndroid),
			PropTypes.arrayOf(elementOf(TabAndroid))
		])
	};
	static defaultProps = {
		style: {
			height: 60
		}
	};

	constructor(props) {
		super(props);
		this.tabsSettings = new Array();
	}

	componentDidMount() {
		this.attachViewPager();
	}

	renderTabs(children) {
		return children.map((child, index) => {
			if (getDisplayName(child.type) === "TabLayoutAndroid.Item") {
				return (
					<View key={index} {...child.props}>
						{child.props.children}
					</View>
				);
			}
			//Since proptypes displays only warning with no possible error thrown, 
			//we thrown error during render process:
			throw new Error(`Invalid child supplied to \`${this.constructor.displayName}\`, expected an element of type TabLayoutAndroid.Item.`);
		});
	}

	manageChildren() {
		if (this.props.children) {
			const children = Array.isArray(this.props.children) ? this.props.children : [this.props.children];
			children.forEach((obj) => {
				this.tabsSettings.push(this.getChildProps(obj));
			});

			return this.renderTabs(children);
		}
		else {
			console.warn("No Children, use TabAndroid tag to add some children");
			return null;
		}
	}

	getChildProps(child) {
		if (child) {
			let tabSettings = new Object;
			for (let propKey in child.props) {
				let propValue = child.props[propKey];
				if (typeof propValue !== "object") {
					tabSettings[propKey] = propValue;
				}
			}

			return tabSettings;
		}

		console.warn("No Children, use TabAndroid tag to add some children");
		return null;
	}

	attachViewPager() {
		let viewPagerId = findNodeHandle(this.refs[TabLayoutAndroid.REF_VIEWPAGER]);

		UIManager.dispatchViewManagerCommand(
			findNodeHandle(this.refs[TabLayoutAndroid.REF_TABLAYOUT]),
			UIManager["TabLayoutAndroid"].Commands["setupWithViewPager"],
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
			<View style={{ flex: 1 }}>

				<NativeComponent
					ref={TabLayoutAndroid.REF_TABLAYOUT}
					{...viewProps}
					backgroundColor={backgroundColor}
					indicatorTabColor={indicatorTabColor}
					indicatorTabHeight={indicatorTabHeight}
					scrollable={scrollable}
					backgroundImage={backgroundImage}
					center={center} />

				<ViewPagerAndroid
					ref={TabLayoutAndroid.REF_VIEWPAGER}
					style={{ flex: 1 }}
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
