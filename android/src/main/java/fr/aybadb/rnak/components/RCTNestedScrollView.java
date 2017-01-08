package fr.aybadb.rnak.components;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import fr.aybadb.rnak.R;

public class RCTNestedScrollView extends ViewGroupManager<RCTNestedScrollView.NativeView> {
	private final static String REACT_CLASS = "NestedScrollView";

	@Override
	public String getName() {
		return REACT_CLASS;
	}

	@Override
	protected NativeView createViewInstance(ThemedReactContext reactContext) {
		// LayoutInfater allows us to translate an xml layout to a view object
		// We use a custom xml file to define NativeView attributes that cannot be set programmatically (like here android:scrollbars, cf. res/layout folder):

		// We obtain the LayoutInflater from the given context via from method:
		LayoutInflater layoutInflater = LayoutInflater.from(reactContext);
		// We instanciate our view with custom attribute from xml via inflate (the second parameter is the parent viewgroup,
		// we set it to null since the generated hierarchy doesn't need a parent since it's an alone component view
		// that React will attach to a specific parent from jsx code:
		NativeView nestedScrollView = (NativeView)layoutInflater.inflate(R.layout.nestedscrollview, null);
		// Always show vertical scrollbar :
		// TODO: props
		nestedScrollView.setVerticalScrollBarEnabled(true);

		return nestedScrollView;
	}


	/*
	 Android Native View definition:
	 public to allow res.layout.nestedscrollview to access to NativeView class:
	 */
	public static class NativeView extends NestedScrollView {
		public NativeView(Context context) {
			super(context);
		}

		public NativeView(Context context, AttributeSet attrs) {
			super(context, attrs);
		}

		public NativeView(Context context, AttributeSet attrs, int defStyleAttr) {
			super(context, attrs, defStyleAttr);
		}

		/* onMeasure necessary to avoid Exception thrown by com.facebook.react.uimanager.MeasureSpecAssertions
		As a reminder, onMeasure() tells to Android how big the current view to be dependent the layout constraints provided by the parent
		void onMeasure (int widthParentSpec,
				int heightParentSpec) : This method is invoked by measure(int, int)

		void measure (int widthParentSpec,
				int heightParentSpec) : This is called to find out how big a view should be. The parent supplies constraint information in the width and height parameters.
				The actual measurement work of a view is performed in onMeasure(int, int), called by this method.
		This measure function is called from the parent to allow sending parent width/height contraints to onMeasure (which is responsible to set current view dimension via setMeasuredDimension() function)
		(In React bridge, the entry point to update all the view and call each measure function based on parent view constraints is via com.facebook.react.uimanager.NativeViewHierarchyManager (via updateLayout function))

		After onMeasure call, for nestedscrollview, it calls measureChildWithMargins to send width/height nestedscrollview contraints to children (and that's why we override onMeasure here to avoid
		sending View.MeasureSpec.UNSPECIFIED at the first initial render (during createView react bridge process) (set by default if no override) and get the exception from com.facebook.react.uimanager.MeasureSpecAssertions.
		 */
		@Override
		protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
			setMeasuredDimension(
					MeasureSpec.getSize(widthMeasureSpec),
					MeasureSpec.getSize(heightMeasureSpec)
			);
		}
	}
}
