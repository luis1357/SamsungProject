package com.ruisu1357.samsungproject.data.remote;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.ruisu1357.samsungproject.data.remote.models.ImageResponse;
import com.ruisu1357.samsungproject.utils.Constants;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteServiceHelper
{
    private Retrofit getRetrofitInstance() {
        return  new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public RemoteService getRemoteService(){
        return getRetrofitInstance().create(RemoteService.class);
    }

}
