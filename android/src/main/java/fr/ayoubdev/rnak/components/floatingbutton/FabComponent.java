package fr.ayoubdev.rnak.components.floatingbutton;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import fr.ayoubdev.rnak.utils.api.Node;

/**
 * Created by Adib on 16/01/2016.
 */
public class FabComponent extends FloatingActionButton implements Node {
	public FabComponent(Context context) {
		super(context);
		//Valeurs par défaut://Inutile: on le spécifie dans le js via defaultProps:
		//this.show();
		//this.setClickable(true);
	}

	public void _setColor(String color) {
		//setBackgroundColor n'est pas implémentée ("Setting a custom background is not supported.")
		//on utilise à la place la fonction héritée de ImageView setBackgroundTintList()
		//(cf. https://android.googlesource.com/platform/frameworks/support/+/master/design/src/android/support/design/widget/FloatingActionButton.java):
		//view.setBackgroundColor(Color.BLACK);
		this.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(color)));

		return;
	}

	public void _setRippleColor(String color) {
		this.setRippleColor(Color.parseColor(color));

		return;
	}

	public void _setImageDrawable(String filename) {
		String imageName = getFilenameWithoutExtension(filename);
		//Android n'accepte pas les fichiers contenant des -:
		imageName.replace('-', '_');
		//on récupérère le R.drawable.iconName:
		int id = this.getResources().getIdentifier(imageName, "drawable", this.getContext().getPackageName());

		//setBackgroundDrawable n'est pas implémenté, on utilise à la place la fonction héritée de ImageView setImageDrawable()
		//(cf. https://android.googlesource.com/platform/frameworks/support/+/master/design/src/android/support/design/widget/FloatingActionButton.java):
		//view.setBackgroundDrawable(view.getResources().getDrawable(com.facebook.stetho.R.drawable.abc_btn_check_to_on_mtrl_015));
		//view.setImageDrawable(view.getResources().getDrawable(com.facebook.stetho.R.drawable.abc_btn_check_to_on_mtrl_015));
		this.setImageDrawable(this.getResources().getDrawable(id));

		return;
	}

	public void _hide(boolean isHidden) {
		if(isHidden)
			this.hide();
		else
			this.show();

		return;
	}

	@Override
	public FabComponent create(Context context) {
		return new FabComponent(context);
	}

	private String getFilenameWithoutExtension(String filename) {
		int extensionIndex = filename.lastIndexOf('.');

		if(extensionIndex != -1)
			return filename.substring(0, extensionIndex);
		return filename;
	}
}
