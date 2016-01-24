package fr.ayoubdev.rnak.components.tab;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import com.facebook.react.bridge.ReadableMap;
import fr.ayoubdev.rnak.utils.RNAKDrawable;

/**
 * Created by Adib on 05/01/2016.
 */
public class TabLayoutView extends TabLayout {
	public TabLayoutView(Context context) {
		super(context);
	}

	public void setBackgroundDrawable(String filename) {
		//setBackgroundDrawable n'est pas implémenté, on utilise à la place la fonction héritée de ImageView setImageDrawable()
		//(cf. https://android.googlesource.com/platform/frameworks/support/+/master/design/src/android/support/design/widget/FloatingActionButton.java):
		//view.setBackgroundDrawable(view.getResources().getDrawable(com.facebook.stetho.R.drawable.abc_btn_check_to_on_mtrl_015));
		//view.setImageDrawable(view.getResources().getDrawable(com.facebook.stetho.R.drawable.abc_btn_check_to_on_mtrl_015));
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
		//Conversion pixel en densité de pixels indépendant:
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
		//Switch entre GRAVITY_FILL et GRAVITY_CENTER ne prend effet que si tab en MODE_FIXED
		//(en mode scrollable, toujours GRAVITY_FILL):
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

			/*
			//Custom size View:
			TabLayout.LayoutParams layoutParams = new TabLayout.LayoutParams(
					ViewGroup.LayoutParams.MATCH_PARENT,
					ViewGroup.LayoutParams.WRAP_CONTENT);
			ViewGroup.LayoutParams params = this.getLayoutParams();
			params.height = 2;
			params.width = 2;
			this.setLayoutParams(params);
			*/
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
