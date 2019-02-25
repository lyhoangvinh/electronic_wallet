package electronicwallet.lyhoangvinh.com.ui.main.payment.viewitemmodel;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import electronicwallet.lyhoangvinh.com.R;
import electronicwallet.lyhoangvinh.com.base.adapter.BaseItemViewHolder;
import electronicwallet.lyhoangvinh.com.local.item.BankPaymentItem;
import electronicwallet.lyhoangvinh.com.utils.Utils;

public class BankPaymentViewItemViewHolder extends BaseItemViewHolder<BankPaymentItem> {

    @BindView(R.id.tvName)
    TextView tvName;

    @BindView(R.id.imvAvatar)
    ImageView imv;

    public BankPaymentViewItemViewHolder(ViewGroup parent, int resId) {
        super(parent, resId);
    }

    @Override
    public void setItem(BankPaymentItem item) {
        super.setItem(item);
        tvName.setText(item.getBank().getTitle());
        Utils.setImageFromUrl(item.getBank().getUrl(), imv);
    }
}
