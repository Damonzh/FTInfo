package io.github.damon.ftinfo.ui.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import io.github.damon.ftinfo.R;
import io.github.damon.ftinfo.ui.fragment.MoviesFragment;
import io.github.damonzh.ftinfo.bean.MoviesWrapper;

/**
 * Author:      ZhangYan
 * Date:        15/12/25
 * Description:
 */
public class MoviePagerAdapter extends FragmentStatePagerAdapter{

    private Context mContext;
    private String[] tabTitle;

    private Fragment mFragment;

    public MoviePagerAdapter(Context context,FragmentManager fm) {
        super(fm);
        mContext = context;
        tabTitle = context.getResources().getStringArray(R.array.movies_type);
    }

    @Override
    public int getCount() {
        return tabTitle.length;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 :
                mFragment = new MoviesFragment(MoviesWrapper.MOVIE_TYPE.NOW_PLAYING);
                break;
            case 1:
                mFragment = new MoviesFragment(MoviesWrapper.MOVIE_TYPE.POPULAR);
                break;
            case 2:
                mFragment = new MoviesFragment(MoviesWrapper.MOVIE_TYPE.TOP_RATED);
                break;
            case 3:
                mFragment = new MoviesFragment(MoviesWrapper.MOVIE_TYPE.UP_COMING);
                break;
            case 4:
                mFragment = new MoviesFragment(MoviesWrapper.MOVIE_TYPE.LATEST);
                break;
        }
        return mFragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitle[position];
    }
}
