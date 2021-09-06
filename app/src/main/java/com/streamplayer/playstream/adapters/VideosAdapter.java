package com.streamplayer.playstream.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.streamplayer.playstream.R;
import com.streamplayer.playstream.models.VideosModel;

import java.util.List;

public class VideosAdapter extends RecyclerView.Adapter<VideosAdapter.MyViewHolder> {

    private Context mContext;
    private List<VideosModel> videosModelList;

    public VideosAdapter(Context mContext, List<VideosModel> videosModelList) {
        this.mContext = mContext;
        this.videosModelList = videosModelList;
    }

    @NonNull
    @Override
    public VideosAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(mContext)
                .inflate(R.layout.custom_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideosAdapter.MyViewHolder holder, int position) {

        String title = this.videosModelList.get(position).getTitle().toString();
        String subTitle = this.videosModelList.get(position).getSubtitle().toString();
        String description = this.videosModelList.get(position).getDescription().toString();
        String thumb = this.videosModelList.get(position).getThumb();

        Glide.with(mContext)
                .load(thumb)
                .apply(RequestOptions.centerCropTransform())
                .placeholder(R.drawable.loading)
                .into(holder.imgThumb);
        holder.tvTitle.setText(title);
        holder.tvSubtitle.setText(subTitle);
        holder.tvDescription.setText(description);

    }

    @Override
    public int getItemCount() {
        if (this.videosModelList != null)
            return this.videosModelList.size();
        return 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle,tvSubtitle,tvDescription;
        ImageView imgThumb;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvSubtitle = (TextView) itemView.findViewById(R.id.tv_subtitle);
            tvDescription = (TextView) itemView.findViewById(R.id.tv_description);
            imgThumb = (ImageView) itemView.findViewById(R.id.img_thumb);

        }

    }

}
