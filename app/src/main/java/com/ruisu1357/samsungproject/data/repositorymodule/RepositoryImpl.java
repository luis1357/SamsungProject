package com.ruisu1357.samsungproject.data.repositorymodule;

import android.annotation.SuppressLint;
import android.arch.lifecycle.MutableLiveData;
import android.provider.ContactsContract;

import com.ruisu1357.samsungproject.data.local.database.FavoritesSqlHelper;
import com.ruisu1357.samsungproject.data.remote.RemoteServiceHelper;
import com.ruisu1357.samsungproject.data.remote.models.ImageResponse;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RepositoryImpl implements Repository
{
    private MutableLiveData<ArrayList<ImageResponse>> imageResponseLiveData = new MutableLiveData<>();
    private RemoteServiceHelper myRemoteServiceHelper;
    private FavoritesSqlHelper favoritesSqlHelper;

    public RepositoryImpl(RemoteServiceHelper remoteServiceHelper)
    {
        this.myRemoteServiceHelper = remoteServiceHelper;
    }

    @SuppressLint("CheckResult")
    @Override
    public void getImageResponse()
    {
        myRemoteServiceHelper.getRemoteService().getImages()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(apiResponse ->
                        imageResponseLiveData.setValue(apiResponse),
                        Throwable::printStackTrace);

    }

    @Override
    public void insertIntoDatabase(ImageResponse imageResponse)
    {
        favoritesSqlHelper.insertIntoDatabase(imageResponse);
    }

    public MutableLiveData<ArrayList<ImageResponse>> getImageResponseLiveData()
    {
        return imageResponseLiveData;
    }


}
