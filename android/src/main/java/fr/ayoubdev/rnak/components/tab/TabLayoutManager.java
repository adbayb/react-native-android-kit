package fr.ayoubdev.rnak.components.tab;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.IllegalViewOperationException;
import com.facebook.react.uimanager.ReactProp;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;

import javax.annotation.Nullable;
import java.util.Map;

/**
 * Created by Adib on 17/01/2016.
 */
public class TabLayoutManager extends ViewGroupManager<TabLayoutComponent> {
	private final static String REACT_CLASS = "TabLayoutAndroid";
	private final static int COMMAND_SETUPWITHVIEWPAGER = 0;

	@Override
	public String getName() {
		return REACT_CLASS;
	}

	@Override
	protected TabLayoutComponent createViewInstance(ThemedReactContext themedReactContext) {
		TabLayoutComponent tabLayoutComponent = new TabLayoutComponent(themedReactContext);

		return tabLayoutComponent;
	}

	@Override
	public void receiveCommand(TabLayoutComponent root, int commandId, @Nullable ReadableArray args) {
		//Test assertions: la vue et les arguments sont nécessaires (not null), auquel cas receiveCommand ne représente aucun intérêt:
		Assertions.assertNotNull(root);
		Assertions.assertNotNull(args);

		switch(commandId) {
			case COMMAND_SETUPWITHVIEWPAGER:
				//On récupère l'instance ViewPager depuis notre RootView à partir de l'id ViewPager instancié dans le JS:
				//L'id doit être envoyé en première entrée (args.getInt(0)) dans le tableau d'arguments envoyé
				//par la fonction javascript dispatchViewManagerCommand:
				ViewPager viewPager = (ViewPager) root.getRootView().findViewById(args.getInt(0));
				if(viewPager != null) {
					root.setupWithViewPager(viewPager);

					//Exemple de tab statiques:

					//Nous devons supprimer toutes les tabs au préalable car receiveCommand n'est pas appelé
					//directement lors de la construction du composant js mais après qu'il ait été monté.
					//Du coup, les composants enfant de TabLayoutAndroid ont déjà été ajouté comme Tab view
					//via le callback addView sans pour autant avoir été lié au ViewPager,
					//Il faut donc supprimer toutes les tabs et les recréer:
					root.removeAllTabs();

					/*root.addTab(root.newTab().setText("Tab1"));
					root.addTab(root.newTab().setText("Tab2"));
					root.addTab(root.newTab().setText("Tab3"));*/

					System.out.println("AYOUBBBBBBBBBBBB");
					ReadableArray tabsSettings = args.getArray(1);
					ReadableMap tabSettingMap = null;
					for(int i = 0; i < tabsSettings.size(); i++) {
						tabSettingMap = tabsSettings.getMap(i);
						if(tabSettingMap != null) {
							TabLayout.Tab tab = root.newTab();
							if(tabSettingMap.hasKey("text")) tab.setText(tabSettingMap.getString("text"));
							if(tabSettingMap.hasKey("icon"))
								tab.setIcon(root.getResources().getDrawable(root.getDrawableID(tabSettingMap.getString("icon"))));

							/*
							//Custom View tab (notamment pour gérer taille texte et position icônes):
							//Mais ne fonctionne pas avec setTabTextColors, on doit gérer nous même les changements de couleurs:
							TextView tabOne = new TextView(root.getContext());
							tabOne.setText("ONE");
							//cf. http://developer.android.com/reference/android/widget/TextView.html#setCompoundDrawablesWithIntrinsicBounds(int, int, int, int)
							tabOne.setCompoundDrawablesWithIntrinsicBounds(root.getDrawableID("ic_place"), 0, 0, 0);
							tabOne.setTextSize(26);
							tab.setCustomView(tabOne);
							*/

							root.addTab(tab);
						}
					}

					/*
					//Custom size View:
					TabLayout.LayoutParams layoutParams = new TabLayout.LayoutParams(
							ViewGroup.LayoutParams.MATCH_PARENT,
							ViewGroup.LayoutParams.WRAP_CONTENT);
					ViewGroup.LayoutParams params = root.getLayoutParams();
					params.height = 2;
					params.width = 2;
					root.setLayoutParams(params);
					*/

					if(tabsSettings != null)
						System.out.println("Good");
					else
						System.out.println("Empty TabsSettings");
				} else
					throw new IllegalViewOperationException("Nonexistent ViewPager instance. Null value received by " + getClass().getSimpleName());
				break;
			default:
				throw new IllegalArgumentException(String.format(
						"Unsupported command %d received by %s.",
						commandId,
						getClass().getSimpleName()));
		}
	}

	@Nullable
	@Override
	public Map<String, Integer> getCommandsMap() {
		return MapBuilder.of(
				"setupWithViewPager", COMMAND_SETUPWITHVIEWPAGER
		);
	}

	@Override
	public void addView(TabLayoutComponent parent, View child, int index) {
		//super.addView(parent, child, index);
		parent.addTab(parent.newTab().setCustomView(child));
	}

	@Override
	public int getChildCount(TabLayoutComponent parent) {
		//return super.getChildCount(parent);
		return parent.getTabCount();
	}

	@Override
	public View getChildAt(TabLayoutComponent parent, int index) {
		//return super.getChildAt(parent, index);
		return parent.getTabAt(index).getCustomView();
	}

	@Override
	public void removeViewAt(TabLayoutComponent parent, int index) {
		//super.removeViewAt(parent, index);
		parent.removeTabAt(index);
	}

	@Override
	public void removeAllViews(TabLayoutComponent parent) {
		//super.removeAllViews(parent);
		parent.removeAllTabs();
	}

	@ReactProp(name = "tabColor")
	public void propSetTabColor(TabLayoutComponent view, String color) {
		view.setBackgroundColor(color);
	}

	@ReactProp(name = "indicatorTabColor")
	public void propSetIndicatorColor(TabLayoutComponent view, String color) {
		view.setSelectedTabIndicatorColor(color);
	}

	@ReactProp(name = "scrollable", defaultBoolean = true)
	public void propSetTabMode(TabLayoutComponent view, boolean isScrollable) {
		view.setTabMode(isScrollable);
	}

	@ReactProp(name = "indicatorTabHeight")
	public void propSetSelectedTabIndicatorHeight(TabLayoutComponent view, int height) {
		view._setSelectedTabIndicatorHeight(height);
	}

	@ReactProp(name = "textColor")
	public void propSetTextColor(TabLayoutComponent view, String color) {
		view.setTabTextColors(Color.parseColor(color), view.getTabTextColors().getDefaultColor());
	}

	@ReactProp(name = "selectedTextColor")
	public void propSetSelectedTextColor(TabLayoutComponent view, String color) {
		view.setTabTextColors(view.getTabTextColors().getDefaultColor(), Color.parseColor(color));
	}

	@ReactProp(name = "backgroundImage")
	public void propSetBackgroundImage(TabLayoutComponent view, String filename) {
		view.setBackgroundDrawable(filename);
	}
}
