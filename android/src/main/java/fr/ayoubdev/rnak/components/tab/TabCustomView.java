package fr.ayoubdev.rnak.components.tab;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.TextView;
import com.facebook.react.bridge.ReadableMap;
import fr.ayoubdev.rnak.utils.RNAKDrawable;

/**
 * Created by Adib on 24/01/2016.
 */

/**
 * Classe permettant de créer une Custom view pour une tab:
 * permet notamment de gérer plus librement la taille et disposition du texte
 * ainsi que la position de l'icône (droite, gauche, haut, bas par rapport au texte)
 * au sein d'une tab:
 */
public class TabCustomView extends TextView {
	public TabCustomView(Context context) {
		super(context);

		//Configurations par défaut:
		//Tout en maj désactivée
		this.setAllCaps(false);
		//Set position texte au centre:
		//tabOne.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
		//ou plus rapide pour mettre le texte au centre verticalement et horizontalement:
		this.setGravity(Gravity.CENTER);
	}

	public void setDrawablePosition(String filename, String position) {
		if(filename != null) {
			if(position != null) {
				switch(position) {
					case "left":
						this.setCompoundDrawablesWithIntrinsicBounds(RNAKDrawable.getDrawableID(this, filename), 0, 0, 0);
						break;
					case "top":
						this.setCompoundDrawablesWithIntrinsicBounds(0, RNAKDrawable.getDrawableID(this, filename), 0, 0);
						break;
					case "right":
						this.setCompoundDrawablesWithIntrinsicBounds(0, 0, RNAKDrawable.getDrawableID(this, filename), 0);
						break;
					case "bottom":
						this.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, RNAKDrawable.getDrawableID(this, filename));
						break;
					default://Par défaut icône placé en top comme le comportement natif de tab.setIcon sans custom view:
						this.setCompoundDrawablesWithIntrinsicBounds(0, RNAKDrawable.getDrawableID(this, filename), 0, 0);
						break;
				}
			} else
				throw new IllegalArgumentException("TabCustomView->setDrawablePosition(): null String (position)");
		} else
			throw new IllegalArgumentException("TabCustomView->setDrawablePosition(): null String (filename)");
	}

	/**
	 * Création contenu custom view dans tab:
	 *
	 * @param tab
	 * @param configMap
	 * @return
	 */
	public TabLayout.Tab setContent(TabLayout.Tab tab, ReadableMap configMap) {
		if(tab != null) {
			if(configMap != null) {
				//Custom View tab (notamment pour gérer taille texte et position icônes):
				//Mais ne fonctionne pas avec setTabTextColors, on doit gérer nous même les changements de couleurs:
				if(configMap.hasKey("text"))
					this.setText(configMap.getString("text"));
				if(configMap.hasKey("textSize"))
					this.setTextSize(TypedValue.COMPLEX_UNIT_SP, configMap.getInt("textSize"));
				//cf. http://developer.android.com/reference/android/widget/TextView.html#setCompoundDrawablesWithIntrinsicBounds(int, int, int, int)
				//tabOne.setCompoundDrawablesWithIntrinsicBounds(RNAKDrawable.getDrawableID(root,"ic_place"), 0, 0, 0);
				if(configMap.hasKey("icon")) {
					if(configMap.hasKey("iconPosition"))
						this.setDrawablePosition(configMap.getString("icon"), configMap.getString("iconPosition"));
					else
						this.setDrawablePosition(configMap.getString("icon"), "");
				}
				if(configMap.hasKey("textColor") && configMap.hasKey("selectedTextColor"))
					this.setTextColor(TabLayoutView.getColorStateList(
							configMap.getString("textColor"),
							configMap.getString("selectedTextColor")
					));

				tab.setCustomView(this);

				return tab;
			}

			return null;
		}

		return null;
	}
}
