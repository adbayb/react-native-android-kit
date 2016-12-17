package fr.aybadb.rnak.components.tab;

import android.support.v4.view.ViewPager;
import android.view.View;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.IllegalViewOperationException;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;

import javax.annotation.Nullable;
import java.util.Map;

public class TabLayoutManager extends ViewGroupManager<TabLayoutView> {
	private final static String REACT_CLASS = "TabLayoutAndroid";
	private final static int COMMAND_SETUPWITHVIEWPAGER = 0;

	@Override
	public String getName() {
		return REACT_CLASS;
	}

	@Override
	protected TabLayoutView createViewInstance(ThemedReactContext themedReactContext) {
		return new TabLayoutView(themedReactContext);
	}

	@Override
	public void receiveCommand(TabLayoutView root, int commandId, @Nullable ReadableArray args) {
		Assertions.assertNotNull(root);
		Assertions.assertNotNull(args);

		switch (commandId) {
			case COMMAND_SETUPWITHVIEWPAGER:
				ViewPager viewPager = (ViewPager) root.getRootView().findViewById(args.getInt(0));
				if (viewPager != null) {
					root.setupWithViewPager(viewPager);
					ReadableArray tabsSettingsArray = args.getArray(1);
					if (!this.addTabs(root, tabsSettingsArray)) {
						throw new IllegalViewOperationException(
								"One or more tabs was/were not created: an error occurred (ReadableArray null and/or TabLayoutView null) in " +
										getClass().getSimpleName()
						);
					}
				} else {
					throw new IllegalViewOperationException(
							"Nonexistent ViewPager instance. Null value received by " +
									getClass().getSimpleName()
					);
				}
				break;
			default:
				throw new IllegalArgumentException(
						String.format(
								"Unsupported command %d received by %s.",
								commandId,
								getClass().getSimpleName()
						)
				);
		}
	}

	@Nullable
	@Override
	public Map<String, Integer> getCommandsMap() {
		return MapBuilder.of(
				"setupWithViewPager",
				COMMAND_SETUPWITHVIEWPAGER
		);
	}

	@Override
	public void addView(TabLayoutView parent, View child, int index) {
		parent.addTab(
				parent.newTab().setCustomView(
						child
				)
		);
	}

	@Override
	public int getChildCount(TabLayoutView parent) {
		return parent.getTabCount();
	}

	@Override
	public View getChildAt(TabLayoutView parent, int index) {
		return parent.getTabAt(index).getCustomView();
	}

	@Override
	public void removeViewAt(TabLayoutView parent, int index) {
		parent.removeTabAt(index);
	}

	@Override
	public void removeAllViews(TabLayoutView parent) {
		parent.removeAllTabs();
	}

	@ReactProp(name = "backgroundColor")
	public void propSetTabColor(TabLayoutView view, String color) {
		view.setBackgroundColor(color);
	}

	@ReactProp(name = "indicatorTabColor")
	public void propSetIndicatorColor(TabLayoutView view, String color) {
		view.setSelectedTabIndicatorColor(color);
	}

	@ReactProp(name = "scrollable", defaultBoolean = true)
	public void propSetTabMode(TabLayoutView view, boolean isScrollable) {
		view.setTabMode(isScrollable);
	}

	@ReactProp(name = "indicatorTabHeight")
	public void propSetSelectedTabIndicatorHeight(TabLayoutView view, int height) {
		view._setSelectedTabIndicatorHeight(height);
	}

	@ReactProp(name = "backgroundImage")
	public void propSetBackgroundImage(TabLayoutView view, String filename) {
		view.setBackgroundDrawable(filename);
	}

	@ReactProp(name = "center", defaultBoolean = true)
	public void propSetCenter(TabLayoutView view, boolean isCenter) {
		view.setTabGravity(isCenter);
	}

	private boolean addTabs(TabLayoutView view, ReadableArray tabsSettings) {
		if (view != null) {
			if (tabsSettings != null) {
				ReadableMap tabSettingsMap = null;

				view.removeAllTabs();
				for (int i = 0; i < tabsSettings.size(); i++) {
					tabSettingsMap = tabsSettings.getMap(i);
					if (tabSettingsMap != null) {
						if (tabSettingsMap.hasKey("customView")) {
							boolean customView = tabSettingsMap.getBoolean("customView");
							if (customView) {
								view.attachCustomTab(tabSettingsMap);
							} else {
								view.attachTab(tabSettingsMap);
							}
						}
					} else {
						return false;
					}
				}

				return true;
			}
		}

		return false;
	}
}
