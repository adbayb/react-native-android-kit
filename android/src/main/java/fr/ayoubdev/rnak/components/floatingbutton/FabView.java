package fr.ayoubdev.rnak.components.floatingbutton;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import fr.ayoubdev.rnak.utils.RNAKDrawable;

/**
 * Created by Adib on 16/01/2016.
 */
public class FabView extends FloatingActionButton {
	public FabView(Context context) {
		super(context);
		//Valeurs par défaut://Inutile: on le spécifie dans le js via defaultProps:
		//this.show();
		//this.setClickable(true);
	}

	public void setColor(String color) {
		//setBackgroundColor n'est pas implémentée ("Setting a custom background is not supported.")
		//on utilise à la place la fonction héritée de ImageView setBackgroundTintList()
		//(cf. https://android.googlesource.com/platform/frameworks/support/+/master/design/src/android/support/design/widget/FloatingActionButton.java):
		//view.setBackgroundColor(Color.BLACK);
		this.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(color)));

		return;
	}

	public void setRippleColor(String color) {
		super.setRippleColor(Color.parseColor(color));

		return;
	}

	public void setImageDrawable(String filename) {
		int id = RNAKDrawable.getDrawableID(this, filename);

		//setBackgroundDrawable n'est pas implémenté, on utilise à la place la fonction héritée de ImageView setImageDrawable()
		//(cf. https://android.googlesource.com/platform/frameworks/support/+/master/design/src/android/support/design/widget/FloatingActionButton.java):
		//view.setBackgroundDrawable(view.getResources().getDrawable(com.facebook.stetho.R.drawable.abc_btn_check_to_on_mtrl_015));
		//view.setImageDrawable(view.getResources().getDrawable(com.facebook.stetho.R.drawable.abc_btn_check_to_on_mtrl_015));
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
