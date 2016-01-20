package fr.ayoubdev.rnak.components.tab;

import android.support.v4.view.ViewPager;
import android.view.View;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.IllegalViewOperationException;
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

					root.addTab(root.newTab().setText("Tab1"));
					root.addTab(root.newTab().setText("Tab2"));
					root.addTab(root.newTab().setText("Tab3"));
				}
				else
					throw new IllegalViewOperationException("Nonexistent ViewPager instance. Null value received by "+getClass().getSimpleName());
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
}
