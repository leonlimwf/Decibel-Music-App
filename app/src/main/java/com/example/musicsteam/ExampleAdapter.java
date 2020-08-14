package com.example.musicsteam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.musicsteam.util.AppUtil;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {
    private ArrayList<Song> mExampleList;
    private OnItemClickListener mListener;
    private Context context;
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mSongName;
        public TextView mArtisteName;


        public ExampleViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView); //refers to immediate parents property
            mImageView = itemView.findViewById(R.id.imageView);
            mSongName = itemView.findViewById(R.id.songName);
            mArtisteName = itemView.findViewById(R.id.artisteName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public ExampleAdapter(Context context, ArrayList<Song> exampleList) {
        this.mExampleList = exampleList;
        this.context = context;
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        Song currentItem = mExampleList.get(position);
        int imageId = AppUtil.getImageIdFromDrawable(this.context, currentItem.getCoverArt());
        holder.mImageView.setImageResource(imageId);
        holder.mSongName.setText(currentItem.getTitle());
        holder.mArtisteName.setText(currentItem.getArtiste());
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    public void filterList(ArrayList<Song> filteredList) {
        mExampleList = filteredList;
        notifyDataSetChanged();
    }
}