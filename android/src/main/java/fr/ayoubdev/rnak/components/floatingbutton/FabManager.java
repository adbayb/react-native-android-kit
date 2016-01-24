package fr.ayoubdev.rnak.components.floatingbutton;

import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.ReactProp;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import fr.ayoubdev.rnak.utils.RNAKNode;

/**
 * Created by Adib on 16/01/2016.
 */
public class FabManager extends SimpleViewManager<FabView> {
	private final static String REACT_CLASS = "FloatingButtonAndroid";

	@Override
	public String getName() {
		return REACT_CLASS;
	}

	@Override
	protected FabView createViewInstance(ThemedReactContext themedReactContext) {
		return new FabView(themedReactContext);
	}

	@Override
	public LayoutShadowNode createShadowNodeInstance() {
		return new RNAKNode<FabView>();
	}

	@Override
	public Class getShadowNodeClass() {
		return RNAKNode.class;
	}

	@ReactProp(name = "icon")
	public void propSetIcon(FabView view, String iconName) {
		view.setImageDrawable(iconName);
	}

	@ReactProp(name = "backgroundColor")
	public void propSetColor(FabView view, String color) {
		view.setColor(color);
	}

	@ReactProp(name = "rippleColor")
	public void propSetRippleColor(FabView view, String color) {
		view.setRippleColor(color);
	}

	@ReactProp(name = "hidden", defaultBoolean = false)
	public void propSetVisibility(FabView view, boolean isHidden) {
		view.hide(isHidden);
	}

	@ReactProp(name = "rippleEffect", defaultBoolean = true)
	public void propSetRippleEffect(FabView view, boolean rippleEffect) {
		//si on ne rend pas notre FAB clickable, l'effet ripple ne sera pas appliqué:
		view.setClickable(rippleEffect);
	}
}
