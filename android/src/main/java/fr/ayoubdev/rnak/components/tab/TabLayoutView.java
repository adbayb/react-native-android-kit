package fr.ayoubdev.rnak.components.tab;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import com.facebook.react.bridge.ReadableMap;
import fr.ayoubdev.rnak.utils.RNAKDrawable;

public class TabLayoutView extends TabLayout {
	public TabLayoutView(Context context) {
		super(context);
	}

	public void setBackgroundDrawable(String filename) {
		super.setBackground(this.getResources().getDrawable(RNAKDrawable.getDrawableID(this, filename)));

		return;
	}

	public void setBackgroundColor(String color) {
		super.setBackgroundColor(Color.parseColor(color));

		return;
	}

	public void setSelectedTabIndicatorColor(String color) {
		super.setSelectedTabIndicatorColor(Color.parseColor(color));

		return;
	}

	public void setTabMode(boolean isScrollable) {
		if(isScrollable)
			super.setTabMode(TabLayoutView.MODE_SCROLLABLE);
		else
			super.setTabMode(TabLayoutView.MODE_FIXED);

		return;
	}

	public void _setSelectedTabIndicatorHeight(int height) {
		int scaledHeight = (int) (height * this.getResources().getDisplayMetrics().density);
		super.setSelectedTabIndicatorHeight(scaledHeight);

		return;
	}

	public static int[] getSelectedStateSet() {
		return SELECTED_STATE_SET;
	}

	public static int[] getEmptyStateSet() {
		return EMPTY_STATE_SET;
	}

	public static ColorStateList getColorStateList(String emptyStateColor, String selectedColor) {
		int[][] states = new int[][]{
				getSelectedStateSet(),
				getEmptyStateSet()
		};
		int[] colors = new int[]{
				Color.parseColor(selectedColor),
				Color.parseColor(emptyStateColor)
		};

		return new ColorStateList(states, colors);
	}

	public void setTabGravity(boolean isCenter) {
		if(isCenter)
			super.setTabGravity(TabLayoutView.GRAVITY_CENTER);
		else
			super.setTabGravity(TabLayoutView.GRAVITY_FILL);
	}

	/**
	 * Ajout Tab configurée à partir d'une Custom View:
	 *
	 * @param configMap
	 * @return
	 */
	public boolean attachCustomTab(ReadableMap configMap) {
		if(configMap != null) {
			Tab tab = this.newTab();
			tab = new TabCustomView(this.getContext()).setContent(tab, configMap);
			this.addTab(tab);

			return true;
		}
		return false;
	}

	/**
	 * Ajout Tab contribuée sans Custom View:
	 *
	 * @param configMap
	 * @return
	 */
	public boolean attachTab(ReadableMap configMap) {
		if(configMap != null) {
			Tab tab = this.newTab();
			if(configMap.hasKey("text"))
				tab.setText(configMap.getString("text"));
			if(configMap.hasKey("icon"))
				tab.setIcon(this.getResources().getDrawable(RNAKDrawable.getDrawableID(this, configMap.getString("icon"))));

			this.addTab(tab);

			return true;
		}
		return false;
	}
}
