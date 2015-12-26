package io.github.damon.ftinfo.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import io.github.damon.ftinfo.ui.fragment.PopularMoviesFragment;
import io.github.damon.ftinfo.ui.fragment.TopRatedMoviesFragment;

/**
 * Author:      ZhangYan
 * Date:        15/12/25
 * Description:
 */
public class MoviePagerAdapter extends FragmentStatePagerAdapter{

    private String[] tabTitle = {"Popular","TopRated"};

    private Fragment mFragment;

    public MoviePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return tabTitle.length;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 :
                mFragment = new PopularMoviesFragment();
                break;
            case 1:
                mFragment = new TopRatedMoviesFragment();
                break;
        }
        return mFragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitle[position];
    }
}
