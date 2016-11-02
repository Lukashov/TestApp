package skywell.testappskywell;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.vk.sdk.VKSdk;

import skywell.testappskywell.data.network.PreferencesManager;

/**
 * Created by Den on 30.10.16.
 */

public class TestApp extends Application {

    private static TestApp INSTANCE;

    public static TestApp getInstance() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        VKSdk.initialize(this);
        PreferencesManager.initializeInstance(this);
        Stetho.initializeWithDefaults(this);
//        MultiDex.install(this);
    }

}
