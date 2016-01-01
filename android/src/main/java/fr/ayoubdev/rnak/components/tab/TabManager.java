package fr.ayoubdev.rnak.components.tab;

import com.facebook.react.views.viewpager.ReactViewPagerManager;

/**
 * Created by Adib on 31/12/2015.
 */
public class TabManager extends ReactViewPagerManager {
    private static final String REACT_CLASS = "TabAndroid";

    @Override
    public String getName() {
        return REACT_CLASS;
    }
}
