import React, {
	PureComponent,
	PropTypes
} from "react";
import {
	View,
	ViewPagerAndroid,
	requireNativeComponent,
	findNodeHandle,
	UIManager
} from "react-native";
import { elementOf, getDisplayName } from "../utils";

const NativeComponent = requireNativeComponent("TabLayout", TabLayout, {});

class Tab extends PureComponent {
	static displayName = "TabLayout.Item";
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
		// Since ViewPager needs View children, this component is just responsible for managing props
		// without rendering (TabLayout is responsible to get Tab children props and inject them into View components inside the 
		// ViewPager container, cf. TabLayout->manageChildren())
		return null;
	}
}

export class TabLayout extends PureComponent {
	static Item = Tab;
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
			elementOf(Tab),
			PropTypes.arrayOf(elementOf(Tab))
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
			if (getDisplayName(child.type) === "TabLayout.Item") {
				return (
					<View key={index} {...child.props}>
						{child.props.children}
					</View>
				);
			}
			//Since proptypes displays only warning with no possible error thrown, 
			//we thrown error during render process:
			throw new Error(`Invalid child supplied to \`${this.constructor.displayName}\`, expected an element of type TabLayout.Item.`);
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
			console.warn("No Children, use Tab tag to add some children");
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

		console.warn("No Children, use Tab tag to add some children");
		return null;
	}

	attachViewPager() {
		const viewPagerId = findNodeHandle(this.refs[TabLayout.REF_VIEWPAGER]);

		return UIManager.dispatchViewManagerCommand(
			findNodeHandle(this.refs[TabLayout.REF_TABLAYOUT]),
			UIManager["TabLayout"].Commands["setupWithViewPager"],
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

		// TODO: clean render View:
		return (
			<View style={{ flex: 1 }}>

				<NativeComponent
					ref={TabLayout.REF_TABLAYOUT}
					{...viewProps}
					backgroundColor={backgroundColor}
					indicatorTabColor={indicatorTabColor}
					indicatorTabHeight={indicatorTabHeight}
					scrollable={scrollable}
					backgroundImage={backgroundImage}
					center={center} />

				<ViewPagerAndroid
					ref={TabLayout.REF_VIEWPAGER}
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