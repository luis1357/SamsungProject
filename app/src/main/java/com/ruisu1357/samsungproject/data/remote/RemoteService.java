package com.ruisu1357.samsungproject.data.remote;

import com.ruisu1357.samsungproject.data.remote.models.ImageResponse;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;

import static com.ruisu1357.samsungproject.utils.Constants.PATH_PHOTOS;

public interface RemoteService
{
    /*@GET(PATH_PHOTOS)
    Observable<ArrayList<ImageResponse>> getImages();
    */

    @GET(PATH_PHOTOS)
    Single<ArrayList<ImageResponse>> getImages();

}
