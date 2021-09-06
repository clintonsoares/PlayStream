package com.streamplayer.playstream.network;

import com.streamplayer.playstream.models.MoviesModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface VideosAPI {

    @GET("v1/a65cdcf2-509a-4f2c-aead-9e32c6eddbbc")
    Call<MoviesModel> getMovies();

}
