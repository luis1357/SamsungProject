package com.ruisu1357.samsungproject.data.repositorymodule;

import android.arch.lifecycle.MutableLiveData;

import com.ruisu1357.samsungproject.data.remote.models.ImageResponse;

public interface Repository
{
    MutableLiveData<ImageResponse> imageResponseLiveData = null;

    void getImageResponse();

    void insertIntoDatabase(ImageResponse imageResponse);
}
