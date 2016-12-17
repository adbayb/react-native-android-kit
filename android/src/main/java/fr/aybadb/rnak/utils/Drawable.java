package fr.aybadb.rnak.utils;

import android.view.View;

public class Drawable {
	public static <T extends View> int getID(T view, String filename) {
		String imageName = getFilenameWithoutExtension(filename);
		imageName = imageName.replace('-', '_');

		return view.getResources().getIdentifier(imageName, "drawable", view.getContext().getPackageName());
	}

	private static String getFilenameWithoutExtension(String filename) {
		int extensionIndex = filename.lastIndexOf('.');
		if (extensionIndex != -1) {
			return filename.substring(0, extensionIndex);
		}

		return filename;
	}
}
