import React, {
	Component,
	PropTypes
} from "react";
import { TabLayoutAndroid } from "react-native-android-kit";

export default class TabLayout extends Component {
	static propTypes = {
		items: PropTypes.arrayOf(
			PropTypes.shape({
				icon: PropTypes.string.isRequired,
				element: PropTypes.element
			})
		)
	}

	renderTabs(items) {
		return items.map((item, index) => {
			return (
				<TabLayoutAndroid.Item
					key={index}
					text={`Tab${index + 1}`}
					textSize={16}
					textColor="white"
					selectedTextColor="#ffc400"
					icon={item.icon} iconPosition="left">
					{item.element}
				</TabLayoutAndroid.Item >
			);
		});
	}

	render() {
		const { items } = this.props;

		return (
			<TabLayoutAndroid style={{ height: 80 }} backgroundColor="#009688" indicatorTabColor="#ffc400"
				indicatorTabHeight={2} scrollable={false} center={false}>

				{this.renderTabs(items)}

			</TabLayoutAndroid>
		);
	}
}