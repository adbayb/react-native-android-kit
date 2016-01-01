package fr.ayoubdev.rnak.components.switchtest;

import com.facebook.react.views.switchview.ReactSwitchManager;

/**
 * Created by Adib on 27/12/2015.
 */
public class SwitchManager extends ReactSwitchManager {
    private final static String REACT_CLASS = "SwitchAndroidTest";

    @Override
    public String getName() {
        return REACT_CLASS;
    }
}
