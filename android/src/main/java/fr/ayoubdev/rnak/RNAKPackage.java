package fr.ayoubdev.rnak;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import fr.ayoubdev.rnak.components.button.ButtonManager;
import fr.ayoubdev.rnak.components.switchtest.SwitchManager;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Adib on 27/12/2015.
 */
public class RNAKPackage implements ReactPackage {
    @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext reactApplicationContext) {
        return Collections.emptyList();
    }

    @Override
    public List<Class<? extends JavaScriptModule>> createJSModules() {
        return Collections.emptyList();
    }

    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactApplicationContext) {
        return Arrays.<ViewManager>asList(
                new SwitchManager(),
                new ButtonManager()
        );
    }
}
