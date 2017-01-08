package fr.aybadb.rnak.components;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.IllegalViewOperationException;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import fr.aybadb.rnak.utils.Drawable;

import javax.annotation.Nullable;
import java.util.Locale;
import java.util.Map;

public class RCTTabLayout extends ViewGroupManager<RCTTabLayout.NativeView> {
	private final static String REACT_CLASS = "TabLayout";
	private final static int COMMAND_SETUPWITHVIEWPAGER = 0;

	@Override
	public String getName() {
		return REACT_CLASS;
	}

	@Override
	protected NativeView createViewInstance(ThemedReactContext themedReactContext) {
		return new NativeView(themedReactContext);
	}

	@Override
	public void receiveCommand(NativeView root, int commandId, @Nullable ReadableArray args) {
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
						Locale.getDefault(),
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
	public void addView(NativeView parent, View child, int index) {
		parent.addTab(
			parent.newTab().setCustomView(
				child
			)
		);
	}

	@Override
	public int getChildCount(NativeView parent) {
		return parent.getTabCount();
	}

	@Override
	public View getChildAt(NativeView parent, int index) {
		return parent.getTabAt(index).getCustomView();
	}

	@Override
	public void removeViewAt(NativeView parent, int index) {
		parent.removeTabAt(index);
	}

	@Override
	public void removeAllViews(NativeView parent) {
		parent.removeAllTabs();
	}

	@ReactProp(name = "backgroundColor")
	public void propSetTabColor(NativeView view, String color) {
		view.setBackgroundColor(color);
	}

	@ReactProp(name = "indicatorTabColor")
	public void propSetIndicatorColor(NativeView view, String color) {
		view.setSelectedTabIndicatorColor(color);
	}

	@ReactProp(name = "scrollable", defaultBoolean = true)
	public void propSetTabMode(NativeView view, boolean isScrollable) {
		view.setTabMode(isScrollable);
	}

	@ReactProp(name = "indicatorTabHeight")
	public void propSetSelectedTabIndicatorHeight(NativeView view, int height) {
		view._setSelectedTabIndicatorHeight(height);
	}

	@ReactProp(name = "backgroundImage")
	public void propSetBackgroundImage(NativeView view, String filename) {
		view.setBackgroundDrawable(filename);
	}

	@ReactProp(name = "center", defaultBoolean = true)
	public void propSetCenter(NativeView view, boolean isCenter) {
		view.setTabGravity(isCenter);
	}

	private boolean addTabs(NativeView view, ReadableArray tabsSettings) {
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


	/* NativeView extending an android sdk view  (as a static nested class for readability and consistency
	into one file purposes + to avoid memory leak if we declare it as
	a non static class since it will be dependent with the parent lifecycle class):
	As a reminder: Static inner class (or static nested class): As with class methods and variables, a static nested class is associated
	with its outer class. And like static class methods, a static nested class cannot refer directly to instance
	variables or methods defined in its enclosing class: it can use them only through an object reference.
	A static nested class interacts with the instance members of its outer class (and other classes) just like any other
	top-level class. In effect, a static nested class is behaviorally a top-level class that has been nested in another
	top-level class for packaging convenience.*/
	static class NativeView extends TabLayout {
		public NativeView(Context context) {
			super(context);
		}

		public void setBackgroundDrawable(String filename) {
			super.setBackground(ContextCompat.getDrawable(this.getContext(), Drawable.getID(this, filename)));

			return;
		}

		public void setBackgroundColor(String color) {
			super.setBackgroundColor(Color.parseColor(color));
		}

		public void setSelectedTabIndicatorColor(String color) {
			super.setSelectedTabIndicatorColor(Color.parseColor(color));
		}

		public void setTabMode(boolean isScrollable) {
			if (isScrollable) {
				super.setTabMode(NativeView.MODE_SCROLLABLE);
			} else {
				super.setTabMode(NativeView.MODE_FIXED);
			}
		}

		public void _setSelectedTabIndicatorHeight(int height) {
			int scaledHeight = (int) (height * this.getResources().getDisplayMetrics().density);
			super.setSelectedTabIndicatorHeight(scaledHeight);
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
			if (isCenter) {
				super.setTabGravity(NativeView.GRAVITY_CENTER);
			} else {
				super.setTabGravity(NativeView.GRAVITY_FILL);
			}
		}

		public boolean attachCustomTab(ReadableMap configMap) {
			if (configMap != null) {
				Tab tab = this.newTab();
				tab = new CustomView(this.getContext())
						.setContent(tab, configMap);
				this.addTab(tab);

				return true;
			}
			return false;
		}

		public boolean attachTab(ReadableMap configMap) {
			if (configMap != null) {
				Tab tab = this.newTab();
				if (configMap.hasKey("text")) {
					tab.setText(configMap.getString("text"));
				}
				if (configMap.hasKey("icon")) {
					tab.setIcon(ContextCompat.getDrawable(this.getContext(), Drawable.getID(this, configMap.getString("icon"))));
				}

				this.addTab(tab);

				return true;
			}
			return false;
		}

		private class CustomView extends TextView {
			public CustomView(Context context) {
				super(context);
				this.setAllCaps(false);
				this.setGravity(Gravity.CENTER);
			}

			public void setDrawablePosition(String filename, String position) {
				if (filename != null) {
					if (position != null) {
						switch (position) {
							case "left":
								this.setCompoundDrawablesWithIntrinsicBounds(Drawable.getID(this, filename), 0, 0, 0);
								break;
							case "top":
								this.setCompoundDrawablesWithIntrinsicBounds(0, Drawable.getID(this, filename), 0, 0);
								break;
							case "right":
								this.setCompoundDrawablesWithIntrinsicBounds(0, 0, Drawable.getID(this, filename), 0);
								break;
							case "bottom":
								this.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, Drawable.getID(this, filename));
								break;
							default://Par défaut icône placé en top comme le comportement natif de tab.setIcon sans custom view:
								this.setCompoundDrawablesWithIntrinsicBounds(0, Drawable.getID(this, filename), 0, 0);
								break;
						}
					} else {
						throw new IllegalArgumentException("TabCustomView->setDrawablePosition(): null String (position)");
					}
				} else {
					throw new IllegalArgumentException("TabCustomView->setDrawablePosition(): null String (filename)");
				}
			}

			public TabLayout.Tab setContent(TabLayout.Tab tab, ReadableMap configMap) {
				if (tab != null) {
					if (configMap != null) {
						if (configMap.hasKey("text")) {
							this.setText(configMap.getString("text"));
						}
						if (configMap.hasKey("textSize")) {
							this.setTextSize(TypedValue.COMPLEX_UNIT_SP, configMap.getInt("textSize"));
						}
						if (configMap.hasKey("icon")) {
							if (configMap.hasKey("iconPosition")) {
								this.setDrawablePosition(configMap.getString("icon"), configMap.getString("iconPosition"));
							} else {
								this.setDrawablePosition(configMap.getString("icon"), "");
							}
						}
						if (configMap.hasKey("textColor") && configMap.hasKey("selectedTextColor")) {
							this.setTextColor(NativeView.getColorStateList(configMap.getString("textColor"), configMap.getString("selectedTextColor")));
						}

						tab.setCustomView(this);

						return tab;
					}
				}

				return null;
			}
		}
	}
}
