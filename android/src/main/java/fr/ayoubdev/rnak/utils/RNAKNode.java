package fr.ayoubdev.rnak.utils;

import android.view.View;
import android.view.ViewGroup;
import com.facebook.csslayout.CSSNode;
import com.facebook.csslayout.MeasureOutput;
import com.facebook.react.uimanager.LayoutShadowNode;
import fr.ayoubdev.rnak.utils.api.Node;

/**
 * Created by Adib on 16/01/2016.
 */

//Classe permettant d'intégrer le noeud représentant notre composant dans la vue qui le contiendra.
public class RNAKNode<T extends Node> extends LayoutShadowNode implements CSSNode.MeasureFunction {
	private int measuredWidth;
	private int measuredHeight;
	private boolean measured;
	private T nodeComponent;

	public RNAKNode() {
		setMeasureFunction(this);
	}

	@Override
	public void measure(CSSNode cssNode, float v, MeasureOutput measureOutput) {
		if(!this.measured) {
			this.nodeComponent.create(getThemedContext());
			final int spec = View.MeasureSpec.makeMeasureSpec(
					ViewGroup.LayoutParams.WRAP_CONTENT,
					View.MeasureSpec.UNSPECIFIED);
			this.nodeComponent.measure(spec, spec);
			this.measuredWidth = this.nodeComponent.getMeasuredWidth();
			this.measuredHeight = this.nodeComponent.getMeasuredHeight();
			this.measured = true;
		}
		measureOutput.width = measuredWidth;
		measureOutput.height = measuredHeight;
	}
}
