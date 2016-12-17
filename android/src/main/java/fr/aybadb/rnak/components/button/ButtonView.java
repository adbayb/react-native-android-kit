package fr.aybadb.rnak.components.button;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.TypedValue;
import android.widget.Button;

public class ButtonView extends Button {
	public ButtonView(Context context) {
		super(context);

		this.setAllCaps(false);
	}

	public void setTextColor(String color) {
		super.setTextColor(Color.parseColor(color));
	}

	public void setBackgroundColor(String color) {
		this.getBackground().setColorFilter(Color.parseColor(color), PorterDuff.Mode.MULTIPLY);

		return;
	}

	public void setTextSize(int size) {
		super.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);

		return;
	}
}
