package fr.ayoubdev.rnak.components.tab;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.TabLayout;

/**
 * Created by Adib on 05/01/2016.
 */
public class TabLayoutComponent extends TabLayout {
	public TabLayoutComponent(Context context) {
		super(context);
	}

	public void setBackgroundDrawable(String filename) {
		//setBackgroundDrawable n'est pas implémenté, on utilise à la place la fonction héritée de ImageView setImageDrawable()
		//(cf. https://android.googlesource.com/platform/frameworks/support/+/master/design/src/android/support/design/widget/FloatingActionButton.java):
		//view.setBackgroundDrawable(view.getResources().getDrawable(com.facebook.stetho.R.drawable.abc_btn_check_to_on_mtrl_015));
		//view.setImageDrawable(view.getResources().getDrawable(com.facebook.stetho.R.drawable.abc_btn_check_to_on_mtrl_015));
		super.setBackground(this.getResources().getDrawable(this.getDrawableID(filename)));

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
			super.setTabMode(TabLayout.MODE_SCROLLABLE);
		else
			super.setTabMode(TabLayout.MODE_FIXED);

		return;
	}

	public void _setSelectedTabIndicatorHeight(int height) {
		//Conversion pixel en densité de pixels indépendant:
		int scaledHeight = (int) (height * this.getResources().getDisplayMetrics().density);
		super.setSelectedTabIndicatorHeight(scaledHeight);

		return;
	}

	public int getDrawableID(String filename) {
		String imageName = this.getFilenameWithoutExtension(filename);
		//Android n'accepte pas les fichiers contenant des -:
		imageName.replace('-', '_');

		//on récupérère le R.drawable.iconName:
		return this.getResources().getIdentifier(imageName, "drawable", this.getContext().getPackageName());
	}

	private String getFilenameWithoutExtension(String filename) {
		int extensionIndex = filename.lastIndexOf('.');

		if(extensionIndex != -1)
			return filename.substring(0, extensionIndex);
		return filename;
	}
}
