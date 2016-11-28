package fr.ayoubdev.rnak.components.button;

import android.view.View;
import android.view.ViewGroup;
import com.facebook.csslayout.CSSMeasureMode;
import com.facebook.csslayout.CSSNodeAPI;
import com.facebook.csslayout.MeasureOutput;
import com.facebook.react.uimanager.LayoutShadowNode;

public class ButtonNode extends LayoutShadowNode implements CSSNodeAPI.MeasureFunction {
	private int mWidth;
	private int mHeight;
	private boolean mMeasured;

	public ButtonNode() {
		setMeasureFunction(this);
	}

	@Override
	public long measure(CSSNodeAPI node, float width, CSSMeasureMode widthMode, float height, CSSMeasureMode heightMode) {
		if(!mMeasured) {
			ButtonView nodeView = new ButtonView(getThemedContext());
			final int spec = View.MeasureSpec.makeMeasureSpec(
					ViewGroup.LayoutParams.WRAP_CONTENT,
					View.MeasureSpec.UNSPECIFIED);
			nodeView.measure(spec, spec);
			mWidth = nodeView.getMeasuredWidth();
			mHeight = nodeView.getMeasuredHeight();
			mMeasured = true;
		}
		return MeasureOutput.make(mWidth, mHeight);
	}
}
