package electronicwallet.lyhoangvinh.com.ui.main.recharge.viewitemmodel;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import electronicwallet.lyhoangvinh.com.R;
import electronicwallet.lyhoangvinh.com.base.adapter.BaseItemViewHolder;
import electronicwallet.lyhoangvinh.com.local.item.TitleItem;

public class TitleViewItemViewHolder extends BaseItemViewHolder<TitleItem> {

    @BindView(R.id.tvTitle)
    TextView tvTitle;

    @BindView(R.id.tvContent)
    TextView tvContent;

    public TitleViewItemViewHolder(ViewGroup parent, int resId) {
        super(parent, resId);
    }

    @Override
    public void setItem(TitleItem item) {
        super.setItem(item);
        tvContent.setVisibility(View.GONE);
        tvTitle.setText(item.getTitle());
    }
}
