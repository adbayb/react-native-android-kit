package fr.ayoubdev.rnak.utils;

import android.view.View;

/**
 * Created by Adib on 24/01/2016.
 */
public class RNAKDrawable {
	public static <T extends View> int getDrawableID(T view, String filename) {
		String imageName = getFilenameWithoutExtension(filename);
		//Android n'accepte pas les fichiers contenant des -:
		imageName.replace('-', '_');

		//on récupérère le R.drawable.iconName:
		return view.getResources().getIdentifier(imageName, "drawable", view.getContext().getPackageName());
	}

	private static String getFilenameWithoutExtension(String filename) {
		int extensionIndex = filename.lastIndexOf('.');

		if(extensionIndex != -1)
			return filename.substring(0, extensionIndex);
		return filename;
	}
}
