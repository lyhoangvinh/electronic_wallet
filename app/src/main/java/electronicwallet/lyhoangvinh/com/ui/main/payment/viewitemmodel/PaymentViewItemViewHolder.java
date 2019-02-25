package electronicwallet.lyhoangvinh.com.ui.main.payment.viewitemmodel;

import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import electronicwallet.lyhoangvinh.com.R;
import electronicwallet.lyhoangvinh.com.base.adapter.BaseItemViewHolder;
import electronicwallet.lyhoangvinh.com.local.item.PaymentDetailItem;
import electronicwallet.lyhoangvinh.com.utils.Utils;

public class PaymentViewItemViewHolder extends BaseItemViewHolder<PaymentDetailItem> {

    @BindView(R.id.tvMoney)
    TextView tvMoney;

    @BindView(R.id.tvCount)
    TextView tvCount;

    @BindView(R.id.tvSum)
    TextView tvSum;

    public PaymentViewItemViewHolder(ViewGroup parent, int resId) {
        super(parent, resId);
    }

    @Override
    public void setItem(PaymentDetailItem item) {
        super.setItem(item);
        tvMoney.setText(Utils.formatVnCurrence(String.valueOf(item.getMoney())));
        tvCount.setText(String.valueOf(item.getCount()));
        int sum = item.getCount() * item.getMoney();
        tvSum.setText(Utils.formatVnCurrence(String.valueOf(sum)));
    }
}
