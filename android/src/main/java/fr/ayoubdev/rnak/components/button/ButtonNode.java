package fr.ayoubdev.rnak.components.button;

import android.view.View;
import android.view.ViewGroup;
import com.facebook.csslayout.CSSNode;
import com.facebook.csslayout.MeasureOutput;
import com.facebook.react.uimanager.LayoutShadowNode;

/**
 * Created by Adib on 30/01/2016.
 */
//Classe permettant d'intégrer le noeud représentant notre composant dans la vue qui le contiendra (via propriétés css):
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

	/*
	//Exemple appel constructeur à plusieurs paramètres via un type générique
	//Défault: beaucoup d'exception à gérer (si on avait un constructeur par
	//défault, aucune exception à gérer car géré en interne: this.reference.newInstance();
	private T nodeComponent;
	private Class<T> reference;
	public ButtonNode(Class<T> reference) {
		setMeasureFunction(this);
		this.reference = reference;
		try {
			this.nodeComponent = this.reference.getConstructor(Context.class).newInstance(getThemedContext());
		} catch(Exception e) {}
	}
	*/
}

