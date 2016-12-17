package fr.aybadb.rnak.components.tab;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.TextView;
import com.facebook.react.bridge.ReadableMap;
import fr.aybadb.rnak.utils.Drawable;

public class CustomView extends TextView {
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

		return;
	}

	/**
	 * Création contenu custom view dans tab:
	 *
	 * @param tab
	 * @param configMap
	 * @return
	 */
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
					this.setTextColor(TabLayoutView.getColorStateList(configMap.getString("textColor"), configMap.getString("selectedTextColor")));
				}

				tab.setCustomView(this);

				return tab;
			}
		}

		return null;
	}
}
