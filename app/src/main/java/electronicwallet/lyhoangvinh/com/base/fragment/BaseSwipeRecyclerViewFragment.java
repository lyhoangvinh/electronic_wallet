package electronicwallet.lyhoangvinh.com.base.fragment;


import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.BindView;
import butterknife.OnTouch;
import electronicwallet.lyhoangvinh.com.R;
import electronicwallet.lyhoangvinh.com.base.adapter.SimpleDividerItemDecoration;
import electronicwallet.lyhoangvinh.com.base.interfaces.ListData;
import electronicwallet.lyhoangvinh.com.base.interfaces.LoadMoreable;
import electronicwallet.lyhoangvinh.com.base.presenter.BasePresenter;
import electronicwallet.lyhoangvinh.com.base.view.BaseView;
import lyhoangvinh.com.myutil.androidutils.CommonUtils;

/**
 * Created by vinh on 14/06/2017
 * Base Fragment with swipe to refresh and recycler view
 * All child fragments 's layout must have refresh layout with id <b>srl</b> and RecyclerView with id <b>rcv</b>
 * @param <A> Adapter with list and addable headers and footers
 * @param <V> View
 * @param <P> Presenter
 */

public abstract class BaseSwipeRecyclerViewFragment<
        A extends RecyclerView.Adapter,
        V extends BaseView,
        P extends BasePresenter<V>>
        extends BaseSwipeToRefreshFragment<V, P> implements SwipeRefreshLayout.OnRefreshListener {

    private static final int DEFAULT_SCROLL_TOP_POSITION = 10;

    @BindView(R.id.rcv)
    protected RecyclerView recyclerView;

    @Nullable
    protected View noDataView;

    @Nullable
    protected View scrollTopView;

    private A adapter;

    public A getAdapter() {
        return adapter;
    }

    private int scrollTopPosition = DEFAULT_SCROLL_TOP_POSITION;

    public void setScrollTopPosition(int scrollTopPosition) {
        this.scrollTopPosition = scrollTopPosition;
    }

    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected int getLayout() {
        return R.layout.swipe_recycler_view;
    }

    @Override
    protected void initialize(View view, Context ctx) {
        super.initialize(view, ctx);
        initRecyclerView(ctx);
        scrollTopView = view.findViewById(R.id.scrollTop);
        noDataView = view.findViewById(R.id.noDataView);
        if (scrollTopView != null) {
            scrollTopView.setVisibility(View.GONE);
            scrollTopView.setOnClickListener(view1 -> {
                recyclerView.scrollToPosition(0);
            });
        }
        if (noDataView != null) {
            noDataView.setVisibility(View.GONE);
        }
    }

    /**
     * Create recycler view adapter
     * @return adapter
     */
    @NonNull
    protected abstract A createAdapter();

    @CallSuper
    protected void initRecyclerView(Context ctx) {
        layoutManager = createLayoutManager();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(null);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(ctx, SimpleDividerItemDecoration.VERTICAL, R.drawable.list_divider_margin));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                if(layoutManager instanceof LinearLayoutManager) {
                    int pastVisibleItems = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
                    updateScrollTop(visibleItemCount, pastVisibleItems);
                }

                if (!isRefresh && (visibleItemCount + getPastVisibleItems()) >= totalItemCount) {
                    loadMore();
                }
            }
        });
        adapter = createAdapter();
//        recyclerView.getRecycledViewPool().clear();
        recyclerView.setAdapter(adapter);
    }

    @OnTouch(R.id.rcv)
    public boolean onClickRcv(){
        CommonUtils.hideSoftKeyboard(getActivity());
        return false;
    }


    protected RecyclerView.LayoutManager createLayoutManager() {
        LinearLayoutManager lm = new LinearLayoutManager(getContext());
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        return lm;
    }

    /**
     * Load more item after scrolling down to bottom of current list
     */
    public void loadMore() {
        if (presenter instanceof LoadMoreable && ((LoadMoreable) presenter).canLoadMore()) {
            ((LoadMoreable) presenter).loadMore();
            isRefresh = true;
//            adapter.addFooter(getFooterView());
        }
    }

    /**
     * @return true if our adapter has no data
     */
    protected boolean isDataEmpty() {
        return adapter != null && adapter instanceof ListData && ((ListData) adapter).isDataEmpty();
    }

    /**
     * refresh list by presenter
     */
    @Override
    public void refresh() {
        if (presenter != null) {
            presenter.refresh();
        }
    }

    /**
     * Called after refreshing success, for both case of success or fail
     * Should be called after {@link BaseFragment#hideProgress()}
     */
    @CallSuper
    public void doneRefresh() {
        if (adapter != null) {
//            adapter.removeFooter(getFooterView());
        }
        updateNoDataState();
        updateScrollTop();
        new android.os.Handler().postDelayed(super::doneRefresh, 300);
    }

    /**
     * Show no data view if current adapter data is empty
     * must be call inside or after {@link #doneRefresh()}
     */
    protected void updateNoDataState() {
        if (noDataView != null) {
            if (isDataEmpty()) {
                noDataView.setVisibility(View.VISIBLE);
            } else {
                noDataView.setVisibility(View.GONE);
            }
        }
    }

    private int getPastVisibleItems() {
        if (layoutManager instanceof LinearLayoutManager) {
            return ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
        }
        return 0;
    }

    /**
     * Show scroll top view (click on it to scroll recycler view to top)
     * if user scroll down more than {@link #DEFAULT_SCROLL_TOP_POSITION}
     */
    protected void updateScrollTop() {
        if (layoutManager != null) {
            int visibleItemCount = layoutManager.getChildCount();
            updateScrollTop(visibleItemCount, getPastVisibleItems());
        }
    }

    /**
     * Show scroll top view (click on it to scroll recycler view to top)
     * if user scroll down more than {@link #DEFAULT_SCROLL_TOP_POSITION}
     */
    private void updateScrollTop(int visibleItemCount, int pastVisibleItems) {
        if (scrollTopView != null) {
            if (recyclerView != null) {
                if (visibleItemCount + pastVisibleItems >= scrollTopPosition) {
                    scrollTopView.setVisibility(View.VISIBLE);
                } else {
                    scrollTopView.setVisibility(View.GONE);
                }
            } else {
                scrollTopView.setVisibility(View.GONE);
            }
        }
    }
}
