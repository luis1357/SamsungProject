package com.ruisu1357.samsungproject.di;

import android.content.Context;

import com.ruisu1357.samsungproject.data.local.database.FavoritesSqlHelper;
import com.ruisu1357.samsungproject.data.remote.RemoteServiceHelper;
import com.ruisu1357.samsungproject.data.repositorymodule.Repository;
import com.ruisu1357.samsungproject.data.repositorymodule.RepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule
{
    Context MMContext;

    public MainModule(Context MMContext)
    {
        this.MMContext = MMContext;
    }

    @Provides
    @Singleton
    public Context getMMContext()
    {
        return MMContext;
    }

    @Provides
    @Singleton
    public RemoteServiceHelper getRemoteServiceHelper()
    {
        return new RemoteServiceHelper();
    }

    @Provides
    @Singleton
    public Repository getRepositoryImpl(RemoteServiceHelper InRemoteServiceHelper)
    {
        return new RepositoryImpl(InRemoteServiceHelper);
    }


}
