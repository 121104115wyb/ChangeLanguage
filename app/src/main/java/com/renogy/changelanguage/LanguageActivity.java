package com.renogy.changelanguage;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

public class LanguageActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context newBase) {
        String type = LanguageUtils.getLanguageType();
        super.attachBaseContext(LanguageUtils.attachBaseContext(newBase,type));
    }
}
