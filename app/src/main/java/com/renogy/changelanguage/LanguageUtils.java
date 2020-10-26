package com.renogy.changelanguage;


import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import androidx.annotation.RequiresApi;

import java.util.Locale;

public class LanguageUtils {

    public final static String CHINESE = "chinese";
    public final static String ENGLISH = "english";
    public final static String LANGUAGE_TYPE = "app-language";
    public final static String SHARE_NAME = "share_name";

    public static String getLanguageType() {
        SharedPreferences preferences = MyApplication.getInstance().getSharedPreferences(SHARE_NAME, Context.MODE_PRIVATE);
        if (preferences != null) {
            return preferences.getString(LANGUAGE_TYPE, "");
        } else {
            return CHINESE;
        }
    }

    public static void saveLanguage(String type) {
        SharedPreferences preferences = MyApplication.getInstance().getSharedPreferences(SHARE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(LANGUAGE_TYPE, type);
        editor.apply();
    }

    public static Context attachBaseContext(Context context, String language) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return updateResources(context, language);
        } else {
            return context;
        }
    }

    @TargetApi(Build.VERSION_CODES.N)
    private static Context updateResources(Context context, String language) {
        Resources resources = context.getResources();
        Locale locale = getLocaleByLanguage(language);

        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);
        configuration.setLocales(new LocaleList(locale));
        return context.createConfigurationContext(configuration);
    }


    /**
     * @param context
     * @param newLanguage 想要切换的语言类型 比如 "en" ,"zh"
     */
    public static void changeAppLanguage(Context context, String newLanguage) {
        if (TextUtils.isEmpty(newLanguage)) {
            return;
        }
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        //获取想要切换的语言类型
        Locale locale = getLocaleByLanguage(newLanguage);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLocale(locale);
        }
        // updateConfiguration
        DisplayMetrics dm = resources.getDisplayMetrics();
        resources.updateConfiguration(configuration, dm);
    }


    public static Locale getLocaleByLanguage(String type) {
        Locale locale;
        if (ENGLISH.equals(type)) {
            locale = Locale.ENGLISH;
        } else {
            locale = Locale.SIMPLIFIED_CHINESE;
        }
        return locale;
    }




}
