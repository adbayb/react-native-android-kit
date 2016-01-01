package fr.ayoubdev.rnak.components.button;

import android.view.View;
import android.view.ViewGroup;
import com.facebook.csslayout.CSSNode;
import com.facebook.csslayout.MeasureOutput;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.ReactProp;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;

/**
 * Created by Adib on 27/12/2015.
 */
public class ButtonManager extends SimpleViewManager<ButtonComponent> {
    private final static String REACT_CLASS = "ButtonAndroid";

    private static class ReactSwitchShadowNode extends LayoutShadowNode implements
            CSSNode.MeasureFunction {

        private int mWidth;
        private int mHeight;
        private boolean mMeasured;

        private ReactSwitchShadowNode() {
            setMeasureFunction(this);
        }

        @Override
        public void measure(CSSNode node, float width, MeasureOutput measureOutput) {
            if(!mMeasured) {
                // Create a switch with the default config and measure it; since we don't (currently)
                // support setting custom switch text, this is fine, as all switches will measure the same
                // on a specific device/theme/locale combination.
                ButtonComponent reactSwitch = new ButtonComponent(getThemedContext());
                final int spec = View.MeasureSpec.makeMeasureSpec(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        View.MeasureSpec.UNSPECIFIED);
                reactSwitch.measure(spec, spec);
                mWidth = reactSwitch.getMeasuredWidth();
                mHeight = reactSwitch.getMeasuredHeight();
                mMeasured = true;
            }
            measureOutput.width = mWidth;
            measureOutput.height = mHeight;
        }
    }

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
        return new ReactSwitchShadowNode();
    }

    @Override
    public Class getShadowNodeClass() {
        return ReactSwitchShadowNode.class;
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
        view._setText(text);
    }
}
