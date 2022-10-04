package com.kainee.videoapp;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PagerAdapter extends RecyclerView.Adapter<PagerAdapter.ViewHolder> {

    ArrayList<DataHandler> dataHandlers;
    private Activity activity;

    public PagerAdapter(ArrayList<DataHandler> dataHandlers, Activity activity) {
        this.dataHandlers = dataHandlers;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_video, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.title.setText(dataHandlers.get(position).title);

        holder.videoView.setVideoURI(Uri.parse(dataHandlers.get(position).url));
        holder.videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                holder.progressBar.setVisibility(View.GONE);
                mediaPlayer.start();

                float vidRatio = mediaPlayer.getVideoWidth()/(float)mediaPlayer.getVideoHeight();
                float screenRatio = holder.videoView.getWidth()/(float)holder.videoView.getHeight();

                float scale = vidRatio/screenRatio;
                if (scale>=1){
                    holder.videoView.setScaleX(scale);
                } else {
                    holder.videoView.setScaleY(1f/scale);
                }
            }
        });

        holder.videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {

                mediaPlayer.start();
            }
        });



    }

    @Override
    public int getItemCount() {
        return dataHandlers.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        VideoView videoView;
        ProgressBar progressBar;
        TextView title;
        //ImageView commentBtn, shareBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            videoView = itemView.findViewById(R.id.videoView);
            progressBar = itemView.findViewById(R.id.prog);
            title = itemView.findViewById(R.id.videoTitle);
            //commentBtn = itemView.findViewById(R.id.commentVideo);
            //shareBtn = itemView.findViewById(R.id.shareVideo);
        }
    }
}
