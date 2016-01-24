package fr.ayoubdev.rnak.components.floatingbutton;

import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.ReactProp;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import fr.ayoubdev.rnak.utils.RNAKNode;

/**
 * Created by Adib on 16/01/2016.
 */
public class FabManager extends SimpleViewManager<FabComponent> {
	private final static String REACT_CLASS = "FloatingButtonAndroid";

	@Override
	public String getName() {
		return REACT_CLASS;
	}

	@Override
	protected FabComponent createViewInstance(ThemedReactContext themedReactContext) {
		FabComponent view = new FabComponent(themedReactContext);

		return view;
	}

	@Override
	public LayoutShadowNode createShadowNodeInstance() {
		return new RNAKNode<FabComponent>();
	}

	@Override
	public Class getShadowNodeClass() {
		return RNAKNode.class;
	}

	@ReactProp(name = "icon")
	public void propSetIcon(FabComponent view, String iconName) {
		view.setImageDrawable(iconName);
	}

	@ReactProp(name = "color")
	public void propSetColor(FabComponent view, String color) {
		view.setColor(color);
	}

	@ReactProp(name = "rippleColor")
	public void propSetRippleColor(FabComponent view, String color) {
		view.setRippleColor(color);
	}

	@ReactProp(name = "hidden", defaultBoolean = false)
	public void propSetVisibility(FabComponent view, boolean isHidden) {
		view.hide(isHidden);
	}

	@ReactProp(name = "rippleEffect", defaultBoolean = true)
	public void propSetRippleEffect(FabComponent view, boolean rippleEffect) {
		//si on ne rend pas notre FAB clickable, l'effet ripple ne sera pas appliqué:
		view.setClickable(rippleEffect);
	}
}
