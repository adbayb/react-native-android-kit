package fr.ayoubdev.rnak.components.floatingbutton;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import fr.ayoubdev.rnak.utils.RNAKDrawable;

public class FabView extends FloatingActionButton {
	public FabView(Context context) {
		super(context);
	}

	public void setColor(String color) {
		this.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(color)));

		return;
	}

	public void setRippleColor(String color) {
		super.setRippleColor(Color.parseColor(color));

		return;
	}

	public void setImageDrawable(String filename) {
		int id = RNAKDrawable.getDrawableID(this, filename);
		super.setImageDrawable(this.getResources().getDrawable(id));

		return;
	}

	public void hide(boolean isHidden) {
		if(isHidden)
			super.hide();
		else
			this.show();

		return;
	}

	private String getFilenameWithoutExtension(String filename) {
		int extensionIndex = filename.lastIndexOf('.');

		if(extensionIndex != -1)
			return filename.substring(0, extensionIndex);
		return filename;
	}
}
