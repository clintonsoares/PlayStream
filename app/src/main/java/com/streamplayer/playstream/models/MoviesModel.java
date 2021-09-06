package com.streamplayer.playstream.models;

public class MoviesModel {
    //Model Class
    private String name;
    private VideosModel[] videos;

    public MoviesModel(String name, VideosModel[] videos) {
        this.name = name;
        this.videos = videos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VideosModel[] getVideos() {
        return videos;
    }

    public void setVideos(VideosModel[] videos) {
        this.videos = videos;
    }
}
