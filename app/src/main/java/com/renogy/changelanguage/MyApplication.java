package com.renogy.changelanguage;

import android.app.Application;
import android.os.Build;

public class MyApplication extends Application {

    private static MyApplication instance;

    public static MyApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        String language = LanguageUtils.getLanguageType();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            LanguageUtils.changeAppLanguage(instance, language);
        }
    }
}
