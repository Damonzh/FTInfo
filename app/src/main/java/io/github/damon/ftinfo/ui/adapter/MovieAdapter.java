package io.github.damon.ftinfo.ui.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import io.github.damon.ftinfo.R;
import io.github.damonzh.ftinfo.API;
import io.github.damonzh.ftinfo.bean.Movies;

/**
 * Author:      ZhangYan
 * Date:        15/12/25
 * Description:
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {

    private List<Movies> mMovieList;

    private Context mContext;

    private OnItemClickListener mOnItemClickListener;

    public MovieAdapter(Context context,List<Movies> mMovieList) {
        this.mMovieList = mMovieList;
        this.mContext = context;
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_movie, parent, false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(final MovieHolder holder, final int position) {
        final Movies movies = mMovieList.get(position);
        holder.mMovieName.setText(movies.getTitle());
        Picasso.with(mContext).load(API.IMAGE_BASE_URL + movies.getBackdrop_path()).into(holder.mMoviePoster);
        holder.mMoviePoster.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                if (null != mOnItemClickListener){
                    holder.mMoviePoster.setTransitionName(movies.getTitle());
                    mOnItemClickListener.onItemClick(v, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMovieList == null ? 0 : mMovieList.size();
    }

    public void addMoreMovies(List<Movies> moreMovies) {
        if (mMovieList != null) {
            mMovieList.addAll(moreMovies);
            notifyDataSetChanged();
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mOnItemClickListener = listener;
    }

    public List<Movies> getAllMovies() {
        return mMovieList;
    }

    public Movies getItemAtPosition(int position) {
        if (null != mMovieList) {
            return mMovieList.get(position);
        }
        return null;
    }


    static class MovieHolder extends RecyclerView.ViewHolder {

        public ImageView mMoviePoster;

        public TextView mMovieName;

        public MovieHolder(View itemView) {
            super(itemView);
            mMoviePoster = (ImageView) itemView.findViewById(R.id.iv_movie_poster);
            mMovieName = (TextView) itemView.findViewById(R.id.tv_movie_name);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }
}
