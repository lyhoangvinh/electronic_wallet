package electronicwallet.lyhoangvinh.com.ui.main.recharge.viewitemmodel;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import electronicwallet.lyhoangvinh.com.R;
import electronicwallet.lyhoangvinh.com.base.adapter.BaseItemViewHolder;
import electronicwallet.lyhoangvinh.com.local.model.BankItem;

public class BankItemViewHolder extends BaseItemViewHolder<BankItem> {

    @BindView(R.id.tvTitle)
    TextView tvTitle;

    @BindView(R.id.tvContent)
    TextView tvContent;

    public BankItemViewHolder(ViewGroup parent, int resId) {
        super(parent, resId);
    }

    @Override
    public void setItem(BankItem item) {
        super.setItem(item);
        tvContent.setVisibility(View.VISIBLE);
        tvContent.setText(item.getName());
        tvTitle.setText(item.getTitle());
    }
}
