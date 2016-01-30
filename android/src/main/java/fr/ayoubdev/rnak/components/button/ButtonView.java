package fr.ayoubdev.rnak.components.button;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.TypedValue;
import android.widget.Button;

/**
 * Created by Adib on 27/12/2015.
 */
public class ButtonView extends Button {
	public ButtonView(Context context) {
		super(context);

		this.setAllCaps(false);
	}

	public void setTextColor(String color) {
		super.setTextColor(Color.parseColor(color));
	}

	public void setBackgroundColor(String color) {
		//setBackgroundColor ne conserve pas le comportement "shaded" du bouton,
		//on doit utiliser un filtre de couleur pour conserver les effets:
		//this.setBackgroundColor(Color.argb(255,255,0,0));

		//Couleurs acceptées: #RRGGBB #AARRGGBB 'red', 'blue', 'green', 'black', 'white',
		//'gray', 'cyan', 'magenta', 'yellow', 'lightgray', 'darkgray':
		this.getBackground().setColorFilter(Color.parseColor(color), PorterDuff.Mode.MULTIPLY);

		return;
	}

    /*public void setText(String text) {
		this.setText(text);

        return;
    }*/

	public void setTextSize(int size) {
		//graduation sp au lieu de dip pour les fonts:
		super.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);

		return;
	}
}
