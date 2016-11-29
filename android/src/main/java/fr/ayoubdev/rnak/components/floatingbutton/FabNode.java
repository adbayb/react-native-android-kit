package fr.ayoubdev.rnak.components.floatingbutton;
import com.facebook.csslayout.MeasureOutput;

import android.view.View;
import com.facebook.csslayout.CSSMeasureMode;
import com.facebook.csslayout.CSSNode;
import com.facebook.csslayout.CSSNodeAPI;
import com.facebook.csslayout.MeasureOutput;
import com.facebook.react.uimanager.LayoutShadowNode;

public class FabNode extends LayoutShadowNode implements CSSNode.MeasureFunction {
	private int mWidth;
	private int mHeight;
	private boolean mMeasured;

	public FabNode() {
		setMeasureFunction(this);
	}

	@Override
	public long measure(CSSNodeAPI node, float width, CSSMeasureMode widthMode, float height, CSSMeasureMode heightMode) {
		if(!mMeasured) {
			FabView nodeView = new FabView(getThemedContext());
			final int spec = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.UNSPECIFIED,
					View.MeasureSpec.UNSPECIFIED);
			nodeView.measure(spec, spec);
			mWidth = nodeView.getMeasuredWidth();
			mHeight = nodeView.getMeasuredHeight();
			mMeasured = true;
		}
		return MeasureOutput.make(mWidth, mHeight);
	}
}
