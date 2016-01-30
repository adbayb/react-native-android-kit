package fr.ayoubdev.rnak.components.floatingbutton;

import android.view.View;
import android.view.ViewGroup;
import com.facebook.csslayout.CSSNode;
import com.facebook.csslayout.MeasureOutput;
import com.facebook.react.uimanager.LayoutShadowNode;

/**
 * Created by Adib on 30/01/2016.
 */
public class FabNode extends LayoutShadowNode implements CSSNode.MeasureFunction {
	private int measuredWidth;
	private int measuredHeight;
	private boolean measured;

	public FabNode() {
		setMeasureFunction(this);
	}

	@Override
	public void measure(CSSNode cssNode, float v, MeasureOutput measureOutput) {
		if(!this.measured) {
			FabView node = new FabView(this.getThemedContext());
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
