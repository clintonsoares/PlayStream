package com.streamplayer.playstream.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.streamplayer.playstream.R;
import com.streamplayer.playstream.adapters.VideosAdapter;
import com.streamplayer.playstream.models.MoviesModel;
import com.streamplayer.playstream.models.VideosModel;
import com.streamplayer.playstream.network.VideosAPI;
import com.streamplayer.playstream.utils.RecyclerTouchListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VideosListActivity extends AppCompatActivity {

    private static String BASE_URL = "https://mocki.io/";

    private RecyclerView recyclerView;
    List<VideosModel> videosModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos_list);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        final TextView noresult = (TextView) findViewById(R.id.tv_noResult);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        VideosAPI videosApi = retrofit.create(VideosAPI.class);

        Call<MoviesModel> call = videosApi.getMovies();

        call.enqueue(new Callback<MoviesModel>() {
            @Override
            public void onResponse(Call<MoviesModel> call, Response<MoviesModel> response) {
                MoviesModel moviesModel = response.body();
                videosModelList = new ArrayList<>(Arrays.asList(moviesModel.getVideos()));
                PutDataIntoRecyclerView(videosModelList);
            }

            @Override
            public void onFailure(Call<MoviesModel> call, Throwable t) {

            }
        });

    }

    private void PutDataIntoRecyclerView(List<VideosModel> videosModelList) {
        VideosAdapter videosAdapter = new VideosAdapter(this,videosModelList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(videosAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                VideosModel videosModel = videosModelList.get(position);
                String vidSrc = videosModel.getSources();
                Toast.makeText(getApplicationContext(), vidSrc + " is selected!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(VideosListActivity.this,VideoPlayerActivity.class);
                intent.putExtra("url", vidSrc);
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }
}