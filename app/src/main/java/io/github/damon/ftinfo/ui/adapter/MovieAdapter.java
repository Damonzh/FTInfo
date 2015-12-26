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
import io.github.damon.ftinfo.widget.MetaballView;
import io.github.damonzh.ftinfo.API;
import io.github.damonzh.ftinfo.bean.Movies;

/**
 * Author:      ZhangYan
 * Date:        15/12/25
 * Description:
 */
public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Movies> mMovieList;

    private Context mContext;

    private OnItemClickListener mOnItemClickListener;

    private static final int VIEW_TYPE_CONTENT = 1;
    private static final int VIEW_TYPE_PROGRESS = 2;

    public MovieAdapter(Context context,List<Movies> mMovieList) {
        this.mMovieList = mMovieList;
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (VIEW_TYPE_CONTENT == viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_movie, parent, false);
            return new MovieHolder(view);
        }else{
            View view = LayoutInflater.from(mContext).inflate(R.layout.load_more_footer,parent,false);
            return new ProgressHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof MovieHolder) {
            final MovieHolder movieHolder = (MovieHolder) holder;
            final Movies movies = mMovieList.get(position);
            movieHolder.mMovieName.setText(movies.getTitle());
            Picasso.with(mContext).load(API.IMAGE_BASE_URL + movies.getBackdrop_path()).into(movieHolder.mMoviePoster);
            movieHolder.mMoviePoster.setOnClickListener(new View.OnClickListener() {
                @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onClick(View v) {
                    if (null != mOnItemClickListener) {
                        movieHolder.mMoviePoster.setTransitionName(movies.getTitle());
                        mOnItemClickListener.onItemClick(v, position);
                    }
                }
            });
        }else if (holder instanceof ProgressHolder){
            ProgressHolder progressHolder = (ProgressHolder) holder;
        }
    }

    @Override
    public int getItemCount() {
        return mMovieList == null ? 0 : mMovieList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mMovieList.get(position) != null ? VIEW_TYPE_CONTENT : VIEW_TYPE_PROGRESS;
    }

    public void addMoreMovies(List<Movies> moreMovies) {
        if (mMovieList != null) {
            mMovieList.addAll(moreMovies);
            notifyDataSetChanged();
        }
    }

    public void addMovie(Movies movie){
        if (mMovieList != null){
            mMovieList.add(movie);
            notifyItemInserted(mMovieList.size() - 1);
        }
    }

    public void removeMovie(int position){
        if (mMovieList != null){
            mMovieList.remove(position);
            notifyItemRemoved(position);
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

    static class ProgressHolder extends RecyclerView.ViewHolder{

        public MetaballView mProgressView;

        public TextView mHintText;

        public ProgressHolder(View itemView) {
            super(itemView);
            mProgressView = (MetaballView) itemView.findViewById(R.id.mv_progress_bar);
            mHintText = (TextView) itemView.findViewById(R.id.tv_hint_text);
        }

    }

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }
}
