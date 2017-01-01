package fr.aybadb.rnak.components;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import fr.aybadb.rnak.utils.Drawable;

public class RCTFloatingActionButton extends SimpleViewManager<RCTFloatingActionButton.NativeView> {
	private final static String REACT_CLASS = "FloatingButtonAndroid";

	@Override
	public String getName() {
		return REACT_CLASS;
	}

	@Override
	protected NativeView createViewInstance(ThemedReactContext themedReactContext) {
		return new NativeView(themedReactContext);
	}

	@ReactProp(name = "icon")
	public void propSetIcon(NativeView view, String iconName) {
		view.setImageDrawable(iconName);
	}

	@ReactProp(name = "backgroundColor")
	public void propSetColor(NativeView view, String color) {
		view.setColor(color);
	}

	@ReactProp(name = "rippleColor")
	public void propSetRippleColor(NativeView view, String color) {
		view.setRippleColor(color);
	}

	@ReactProp(name = "hidden", defaultBoolean = false)
	public void propSetVisibility(NativeView view, boolean isHidden) {
		view.hide(isHidden);
	}

	@ReactProp(name = "rippleEffect", defaultBoolean = true)
	public void propSetRippleEffect(NativeView view, boolean rippleEffect) {
		view.setClickable(rippleEffect);
	}


	/* Android Native View definition: */
	static class NativeView extends FloatingActionButton {
		public NativeView(Context context) {
			super(context);
		}

		public void setColor(String color) {
			this.setBackgroundTintList(
					ColorStateList.valueOf(
							Color.parseColor(color)
					)
			);
		}

		public void setRippleColor(String color) {
			super.setRippleColor(Color.parseColor(color));
		}

		public void setImageDrawable(String filename) {
			super.setImageDrawable(
					ContextCompat.getDrawable(
							this.getContext(),
							Drawable.getID(this, filename)
					)
			);
		}

		public void hide(boolean isHidden) {
			if (isHidden) {
				super.hide();
			} else {
				this.show();
			}
		}
	}
}
