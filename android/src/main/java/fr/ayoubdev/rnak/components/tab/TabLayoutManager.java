package fr.ayoubdev.rnak.components.tab;

import android.support.design.widget.TabLayout;
import android.view.View;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;

/**
 * Created by Adib on 17/01/2016.
 */
public class TabLayoutManager extends ViewGroupManager<TabLayoutComponent> {
	private final static String REACT_CLASS = "TabLayoutAndroid";

	@Override
	public String getName() {
		return REACT_CLASS;
	}

	@Override
	protected TabLayoutComponent createViewInstance(ThemedReactContext themedReactContext) {
		TabLayoutComponent tabLayoutComponent = new TabLayoutComponent(themedReactContext);

		//Test tabs:
		tabLayoutComponent.setTabMode(TabLayout.MODE_SCROLLABLE);
		tabLayoutComponent.addTab(tabLayoutComponent.newTab().setText("Tab1"));
		tabLayoutComponent.addTab(tabLayoutComponent.newTab().setText("Tab2"));
		tabLayoutComponent.addTab(tabLayoutComponent.newTab().setText("Tab3"));

		return tabLayoutComponent;
	}

	@Override
	public void addView(TabLayoutComponent parent, View child, int index) {
		//super.addView(parent, child, index);
		parent.addTab(parent.newTab().setCustomView(child), index);
	}

	@Override
	public int getChildCount(TabLayoutComponent parent) {
		//return super.getChildCount(parent);
		return parent.getTabCount();
	}

	@Override
	public View getChildAt(TabLayoutComponent parent, int index) {
		//return super.getChildAt(parent, index);
		return parent.getTabAt(index).getCustomView();
	}

	@Override
	public void removeViewAt(TabLayoutComponent parent, int index) {
		//super.removeViewAt(parent, index);
		parent.removeTabAt(index);
	}

	@Override
	public void removeAllViews(TabLayoutComponent parent) {
		//super.removeAllViews(parent);
		parent.removeAllTabs();
	}
}
