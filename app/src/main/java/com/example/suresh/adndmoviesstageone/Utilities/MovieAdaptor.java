package com.example.suresh.adndmoviesstageone.Utilities;
import android.content.Context;
import android.support.annotation.NonNull;
import  android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.example.suresh.adndmoviesstageone.R;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class MovieAdaptor extends RecyclerView.Adapter<MovieAdaptor.MovieViewHolder>{
    private int mTotalItems;
    private final ArrayList<MovieInfo> mData;
    final private ItemOnClickListener mItemOnClickListener;

    public MovieAdaptor (ArrayList<MovieInfo> data, ItemOnClickListener itemOnClickListener) {
        this.mTotalItems = data.size();
        this.mData = data;
        this.mItemOnClickListener = itemOnClickListener;
    }

    public interface ItemOnClickListener {
        void ItemClicked (int positionClicked);
    }

    public MovieInfo getItem (int index) {
       return this.mData.get(index);
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.movie_thumbnails;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdForListItem, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.bindItem(holder.imageView.getContext(),position);
    }

    @Override
    public int getItemCount() {
        return mTotalItems;
    }

    public void updateData (ArrayList<MovieInfo> dataSource) {
        this.mData.clear();
        this.mData.addAll(dataSource);
        this.mTotalItems = dataSource.size();
        notifyDataSetChanged();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final ImageView imageView;

        MovieViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.movie_thumbnail);
            itemView.setOnClickListener(this);
        }

        void bindItem(Context context, int index) {
            Picasso.with(context).load(mData.get(index).getMoviePoster()).into(imageView);
        }

        @Override
        public void onClick(View v) {
            int positionClicked = getAdapterPosition();
            mItemOnClickListener.ItemClicked(positionClicked);
        }
    }
}
