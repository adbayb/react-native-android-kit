package fr.aybadb.rnak.components;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.TypedValue;
import android.widget.Button;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

public class RCTButton extends SimpleViewManager<RCTButton.NativeView> {
	private final static String REACT_CLASS = "ButtonAndroid";

	@Override
	public String getName() {
		return REACT_CLASS;
	}

	@Override
	protected NativeView createViewInstance(ThemedReactContext themedReactContext) {
		return new NativeView(themedReactContext);
	}

	@ReactProp(name = "backgroundColor")
	public void propSetColor(NativeView view, String color) {
		view.setBackgroundColor(color);
	}

	@ReactProp(name = "textColor")
	public void propSetTextColor(NativeView view, String color) {
		view.setTextColor(color);
	}

	@ReactProp(name = "textSize")
	public void propSetTextSize(NativeView view, int size) {
		view.setTextSize(size);
	}

	@ReactProp(name = "text")
	public void propSetText(NativeView view, String text) {
		view.setText(text);
	}


	/* Android Native View definition: */
	static class NativeView extends Button {
		public NativeView(Context context) {
			super(context);

			this.setAllCaps(false);
		}

		public void setTextColor(String color) {
			super.setTextColor(Color.parseColor(color));
		}

		public void setBackgroundColor(String color) {
			this.getBackground().setColorFilter(Color.parseColor(color), PorterDuff.Mode.MULTIPLY);
		}

		public void setTextSize(int size) {
			super.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
		}
	}
}
