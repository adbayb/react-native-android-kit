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

const nativeComponentName = "RNAKTabLayout";
const NativeComponent = requireNativeComponent(nativeComponentName, TabLayout, {});

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
	static propTypes = {
		...View.propTypes,
		...ViewPagerAndroid.propTypes,
		contentContainerStyle: View.propTypes.style,
		backgroundColor: PropTypes.string,
		indicatorTabColor: PropTypes.string,
		indicatorTabHeight: PropTypes.number,
		scrollable: PropTypes.bool,
		backgroundImage: PropTypes.string,
		center: PropTypes.bool,
		height: PropTypes.number,
		children: PropTypes.oneOfType([
			elementOf(Tab),
			PropTypes.arrayOf(elementOf(Tab))
		])
	};
	static defaultProps = {
		height: 60
	};

	constructor(props) {
		super(props);
	}

	componentDidMount() {
		this.attachViewPager();
	}

	renderTabs() {
		const { children } = this.props;

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

	manageNativeProps() {
		const children = Array.isArray(this.props.children) ? this.props.children : [this.props.children];

		return children.map(child => this.getPrimitiveProps(child));
	}

	getPrimitiveProps(child) {
		if (child) {
			const props = {};
			for (const propKey in child.props) {
				const propValue = child.props[propKey];
				if (typeof propValue !== "object") {
					props[propKey] = propValue;
				}
			}

			return props;
		}

		console.warn("No Children, use Tab tag to add some children");
		return null;
	}

	attachViewPager() {
		const viewPagerNode = findNodeHandle(this.viewPagerNode);
		const tabSettings = this.manageNativeProps();

		if (viewPagerNode) {
			UIManager.dispatchViewManagerCommand(
				findNodeHandle(this.tabLayoutNode),
				UIManager[nativeComponentName].Commands["setupWithViewPager"],
				[viewPagerNode, tabSettings]
			);
		}
	}

	render() {
		const {
			contentContainerStyle,
			backgroundColor,
			indicatorTabColor,
			indicatorTabHeight,
			scrollable,
			backgroundImage,
			center,
			height,
			initialPage,
			keyboardDismissMode,
			onPageScroll,
			onPageSelected,
			...viewProps
		} = this.props;

		return (
			<View {...viewProps}>

				<NativeComponent
					ref={(node) => this.tabLayoutNode = node}
					style={{ height }}
					backgroundColor={backgroundColor}
					indicatorTabColor={indicatorTabColor}
					indicatorTabHeight={indicatorTabHeight}
					scrollable={scrollable}
					backgroundImage={backgroundImage}
					center={center} />

				<ViewPagerAndroid
					ref={(node) => this.viewPagerNode = node}
					style={contentContainerStyle || { flex: 1 }}
					initialPage={initialPage}
					keyboardDismissMode={keyboardDismissMode}
					onPageScroll={onPageScroll}
					onPageSelected={onPageSelected}>

					{this.renderTabs()}

				</ViewPagerAndroid>

			</View>
		);
	}
}