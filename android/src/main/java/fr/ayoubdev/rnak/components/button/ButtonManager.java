package fr.ayoubdev.rnak.components.button;

import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.ReactProp;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import fr.ayoubdev.rnak.utils.RNAKNode;

/**
 * Created by Adib on 27/12/2015.
 */
public class ButtonManager extends SimpleViewManager<ButtonComponent> {
	private final static String REACT_CLASS = "ButtonAndroid";

	@Override
	public String getName() {
		return REACT_CLASS;
	}

	@Override
	protected ButtonComponent createViewInstance(ThemedReactContext themedReactContext) {
		ButtonComponent view = new ButtonComponent(themedReactContext);

		return view;
	}

	@Override
	public LayoutShadowNode createShadowNodeInstance() {
		return new RNAKNode<ButtonComponent>();
	}

	@Override
	public Class getShadowNodeClass() {
		return RNAKNode.class;
	}

	@ReactProp(name = "color")
	public void setColor(ButtonComponent view, String color) {
		view._setBackgroundColor(color);
	}

	@ReactProp(name = "textColor")
	public void setTextColor(ButtonComponent view, String color) {
		view._setTextColor(color);
	}

	@ReactProp(name = "textSize")
	public void setTextSize(ButtonComponent view, int size) {
		view._setTextSize(size);
	}

	@ReactProp(name = "text")
	public void setText(ButtonComponent view, String text) {
		view.setText(text);
	}
}
