package com.ruisu1357.samsungproject.di;


import com.ruisu1357.samsungproject.ui.activities.MainActivity;
import com.ruisu1357.samsungproject.ui.viemodel.MainViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = MainModule.class)
public interface MainComponent
{
    void inject(MainActivity mainActivity);
    void inject(MainViewModel mainViewModel);
}
