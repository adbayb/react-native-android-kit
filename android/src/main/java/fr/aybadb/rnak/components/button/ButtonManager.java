package fr.aybadb.rnak.components.button;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

public class ButtonManager extends SimpleViewManager<ButtonView> {
	private final static String REACT_CLASS = "ButtonAndroid";

	@Override
	public String getName() {
		return REACT_CLASS;
	}

	@Override
	protected ButtonView createViewInstance(ThemedReactContext themedReactContext) {
		return new ButtonView(themedReactContext);
	}

	@ReactProp(name = "backgroundColor")
	public void propSetColor(ButtonView view, String color) {
		view.setBackgroundColor(color);
	}

	@ReactProp(name = "textColor")
	public void propSetTextColor(ButtonView view, String color) {
		view.setTextColor(color);
	}

	@ReactProp(name = "textSize")
	public void propSetTextSize(ButtonView view, int size) {
		view.setTextSize(size);
	}

	@ReactProp(name = "text")
	public void propSetText(ButtonView view, String text) {
		view.setText(text);
	}
}
