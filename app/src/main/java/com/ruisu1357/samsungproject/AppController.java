package com.ruisu1357.samsungproject;

import android.app.Application;

import com.ruisu1357.samsungproject.di.DaggerMainComponent;
import com.ruisu1357.samsungproject.di.MainComponent;
import com.ruisu1357.samsungproject.di.MainModule;

public class AppController extends Application
{
    private MainComponent MyMainComponent;

    public MainComponent getAppComponent()
    {
        if (MyMainComponent == null)
        {
            MyMainComponent = DaggerMainComponent.builder()
                                                .mainModule(new MainModule(this))
                                                .build();
        }
        return MyMainComponent;
    }
}
