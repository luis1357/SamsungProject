package com.ruisu1357.samsungproject.ui.viemodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.support.annotation.NonNull;
import android.util.Log;

import com.ruisu1357.samsungproject.AppController;
import com.ruisu1357.samsungproject.data.local.database.FavoritesSqlHelper;
import com.ruisu1357.samsungproject.data.remote.models.ImageResponse;
import com.ruisu1357.samsungproject.data.repositorymodule.Repository;
import com.ruisu1357.samsungproject.data.repositorymodule.RepositoryImpl;

import java.util.ArrayList;

import javax.inject.Inject;

public class MainViewModel extends AndroidViewModel
{
    public static final String TAG = "MainViewModel: ";

    @Inject
    Repository myRepo;

    public MainViewModel(@NonNull Application application)
    {
        super(application);

        ((AppController) application).getAppComponent().inject(this);
    }

    public LiveData<ArrayList<ImageResponse>> getImageResponse()
    {
        myRepo.getImageResponse();

        return Transformations.map(((RepositoryImpl) myRepo)
                        .getImageResponseLiveData(),
                input ->
                {
                    Log.d(TAG, "getResponse: " +
                            input.get(0).getTitle());
                    return input;
                });
    }

}
