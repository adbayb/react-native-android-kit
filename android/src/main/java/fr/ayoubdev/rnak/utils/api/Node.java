package fr.ayoubdev.rnak.utils.api;

import android.content.Context;

/**
 * Created by Adib on 16/01/2016.
 */
public interface Node {
	public Node create(Context context);

	public int getMeasuredWidth();

	public int getMeasuredHeight();

	public void measure(int widthMeasureSpec, int heightMeasureSpec);
}
