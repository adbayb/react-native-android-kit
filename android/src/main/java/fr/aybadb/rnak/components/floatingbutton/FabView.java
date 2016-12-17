package fr.aybadb.rnak.components.floatingbutton;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import fr.aybadb.rnak.utils.Drawable;

public class FabView extends FloatingActionButton {
	public FabView(Context context) {
		super(context);
	}

	public void setColor(String color) {
		this.setBackgroundTintList(
				ColorStateList.valueOf(
						Color.parseColor(color)
				)
		);

		return;
	}

	public void setRippleColor(String color) {
		super.setRippleColor(Color.parseColor(color));

		return;
	}

	public void setImageDrawable(String filename) {
		super.setImageDrawable(
				ContextCompat.getDrawable(
						this.getContext(),
						Drawable.getID(this, filename)
				)
		);

		return;
	}

	public void hide(boolean isHidden) {
		if (isHidden) {
			super.hide();
		} else {
			this.show();
		}

		return;
	}
}
