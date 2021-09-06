package com.streamplayer.playstream.models;

public class VideosModel {
    // Model Class
    private String title;
    private String thumb;
    private String subtitle;
    private String sources;
    private String description;

    public VideosModel(String title, String thumb, String subtitle, String sources, String description) {
        this.title = title;
        this.thumb = thumb;
        this.subtitle = subtitle;
        this.sources = sources;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getSources() {
        return sources;
    }

    public void setSources(String sources) {
        this.sources = sources;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
