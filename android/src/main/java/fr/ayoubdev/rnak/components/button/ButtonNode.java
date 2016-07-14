package fr.ayoubdev.rnak.components.button;

import android.view.View;
import android.view.ViewGroup;
import com.facebook.csslayout.CSSNode;
import com.facebook.csslayout.MeasureOutput;
import com.facebook.react.uimanager.LayoutShadowNode;

public class ButtonNode extends LayoutShadowNode implements CSSNode.MeasureFunction {
	private int measuredWidth;
	private int measuredHeight;
	private boolean measured;

	public ButtonNode() {
		setMeasureFunction(this);
	}

	@Override
	public void measure(CSSNode cssNode, float v, MeasureOutput measureOutput) {
		if(!this.measured) {
			ButtonView node = new ButtonView(this.getThemedContext());
			final int spec = View.MeasureSpec.makeMeasureSpec(
					ViewGroup.LayoutParams.WRAP_CONTENT,
					View.MeasureSpec.UNSPECIFIED);
			node.measure(spec, spec);
			this.measuredWidth = node.getMeasuredWidth();
			this.measuredHeight = node.getMeasuredHeight();
			this.measured = true;
		}
		measureOutput.width = measuredWidth;
		measureOutput.height = measuredHeight;
	}
}
