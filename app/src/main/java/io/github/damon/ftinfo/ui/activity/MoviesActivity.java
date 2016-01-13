package io.github.damon.ftinfo.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.damon.ftinfo.R;
import io.github.damon.ftinfo.presenter.impl.ConfigurationPresenter;
import io.github.damon.ftinfo.ui.adapter.MoviePagerAdapter;
import io.github.damon.ftinfo.view.IHomeView;

/**
 * Author:      ZhangYan
 * Date:        15/12/25
 * Description:
 */
public class MoviesActivity extends AppCompatActivity implements IHomeView {

    private static final String TAG = MoviesActivity.class.getSimpleName();


    @Bind(R.id.tool_bar)
    Toolbar mToolBar;
    @Bind(R.id.tl_tab_layout)
    TabLayout mTabLayout;
    @Bind(R.id.vp_view_pager)
    ViewPager mViewPager;
    @Bind(R.id.pb_progress_bar)
    ProgressBar mProgressBar;
    @Bind(R.id.ll_content_view)
    LinearLayout mContentView;

    private MoviePagerAdapter mMoviePagerAdapter;
    private ConfigurationPresenter mConfigurationPresenter = new ConfigurationPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //改变状态栏图标颜色为深色，只在6.0+有效
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//        getWindow().setStatusBarColor(Color.WHITE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        setSupportActionBar(mToolBar);
        mConfigurationPresenter.start(null);
    }

//    private void showToolBar() {
//        mToolBar.startAnimation(AnimationUtils.loadAnimation(this, R.anim.translation_down));
//    }
//
//    private void hideToolBar() {
//        mToolBar.startAnimation(AnimationUtils.loadAnimation(this, R.anim.translation_up));
//    }

    @Override
    public void attachFragment() {
        mViewPager.setAdapter(mMoviePagerAdapter = new MoviePagerAdapter(this,getSupportFragmentManager()));
        //绑定TabLayout和ViewPager
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabTextColors(Color.WHITE, Color.GREEN);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    @Override
    public void hideProgressBar() {
        if (mProgressBar.getVisibility() == View.VISIBLE){
            mProgressBar.setVisibility(View.GONE);
            mContentView.setVisibility(View.VISIBLE);
        }
    }
}
