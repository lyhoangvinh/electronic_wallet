package electronicwallet.lyhoangvinh.com.base.activity;


import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;

import butterknife.BindView;
import electronicwallet.lyhoangvinh.com.R;
import electronicwallet.lyhoangvinh.com.base.interfaces.UiRefreshable;
import electronicwallet.lyhoangvinh.com.base.presenter.BasePresenter;
import electronicwallet.lyhoangvinh.com.base.view.BaseView;
import electronicwallet.lyhoangvinh.com.constants.Constants;
import lyhoangvinh.com.myutil.view.AppBarStateChangeListener;

/**
 * Base Fragment with Swipe to refresh layout
 * All child fragments 's layout must have {@link SwipeRefreshLayout} with id = <b>srl</b>
 */

public abstract class BaseSwipeToRefreshActivity<V extends BaseView, P extends BasePresenter<V>>
        extends BasePresenterActivity<V, P> implements SwipeRefreshLayout.OnRefreshListener, UiRefreshable {

    @BindView(R.id.srl)
    protected SwipeRefreshLayout refreshLayout;

    protected boolean isRefresh = false;

    @Override
    public void initialize(Bundle savedInstanceState) {
        initialize();
    }

    @Override
    protected void initialize() {
        initSwipeToRefresh();
        super.initialize();
    }

    private void initSwipeToRefresh() {
        refreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        refreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        doneRefresh();
    }

    @Override
    public void setRefreshEnabled(boolean enabled) {
        refreshLayout.setEnabled(enabled);
    }

    @Override
    public void showProgress() {
        refreshUi();
    }

    @CallSuper
    public void doneRefresh() {
        isRefresh = false;
        shouldRefreshUi = false;
        if (refreshLayout != null) {
            refreshLayout.setRefreshing(false);
        }
    }

    @Override
    @CallSuper
    public void onRefresh() {
        if (!isRefresh) {
            isRefresh = true;
            refresh();
        }
    }

    public void refreshWithUi(int delay) {
        if (refreshLayout != null) {
            refreshLayout.postDelayed(() -> {
                refreshUi();
                onRefresh();
            }, delay);
        }
    }

    private boolean shouldRefreshUi = true;

    protected void refreshUi() {
        shouldRefreshUi = true;
        new android.os.Handler().postDelayed(() -> {
            if (shouldRefreshUi && refreshLayout != null) {
                refreshLayout.setRefreshing(true);
            }
        }, Constants.PROGRESS_DIALOG_DELAY);
    }

    public void refreshWithUi() {
        refreshWithUi(0);
    }

    public boolean isRefreshing() {
        return isRefresh;
    }

    /**
     * adopt with {@link AppBarLayout} for smoother scrolling
     * when user is interacting with appbar, we should disable swipe to refresh layout
     *
     * @param appBar appbar inside fragment's layout
     */
    public void setupWithAppBarScrollingState(@NonNull AppBarLayout appBar) {
        appBar.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if (state == State.EXPANDED) {
                    if (refreshLayout != null) {
                        Log.d("refresh_layout", "enabled");
                        refreshLayout.setEnabled(true);
                    }
                } else {
                    if (refreshLayout != null && !isRefresh) {
                        Log.d("refresh_layout", "disabled");
                        refreshLayout.setEnabled(false);
                    }
                }
            }
        });
    }

    /**
     * adopt with {@link ViewPager} for smoother scrolling
     * when user is interacting with viewpager, should disable swipe to refresh layout
     *
     * @param pager view pager inside fragment's layout
     */
    public void setupWithViewPagerScrollingState(@NonNull ViewPager pager) {
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            int mPreviousState = ViewPager.SCROLL_STATE_IDLE;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // All of this is to inhibit any scrollable container from consuming our touch events as the user is changing pages
                if (mPreviousState == ViewPager.SCROLL_STATE_IDLE) {
                    if (state == ViewPager.SCROLL_STATE_DRAGGING) {
                        refreshLayout.setEnabled(false);
                    }
                } else {
                    if (state == ViewPager.SCROLL_STATE_IDLE || state == ViewPager.SCROLL_STATE_SETTLING) {
                        refreshLayout.setEnabled(true);
                    }
                }
                mPreviousState = state;
            }
        });
    }
}
