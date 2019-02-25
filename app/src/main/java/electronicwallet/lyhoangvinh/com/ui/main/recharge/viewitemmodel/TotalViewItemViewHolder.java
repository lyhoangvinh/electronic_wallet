package electronicwallet.lyhoangvinh.com.ui.main.recharge.viewitemmodel;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;
import electronicwallet.lyhoangvinh.com.R;
import electronicwallet.lyhoangvinh.com.base.adapter.BaseItemViewHolder;
import electronicwallet.lyhoangvinh.com.events.MoneyEvent;
import electronicwallet.lyhoangvinh.com.local.model.Total;
import electronicwallet.lyhoangvinh.com.local.item.TotalItem;

public class TotalViewItemViewHolder extends BaseItemViewHolder<TotalItem> {

    @BindView(R.id.tvNumber)
    TextView tvNumber;

    private int max = 5;
    private int defaultNumber = 1;

    public TotalViewItemViewHolder(ViewGroup parent, int resId) {
        super(parent, resId);
    }

    @Override
    public void setItem(TotalItem item) {
        super.setItem(item);
        tvNumber.setText(String.valueOf(defaultNumber));
    }

    @OnClick({R.id.imvDis, R.id.imvSum})
    public void clickDisSum(View v) {
        switch (v.getId()) {
            case R.id.imvDis:
                if (defaultNumber == 1) {
                    return;
                }
                defaultNumber--;
                tvNumber.setText(String.valueOf(defaultNumber));
                break;
            case R.id.imvSum:
                if (defaultNumber == max) {
                    return;
                }
                defaultNumber++;
                tvNumber.setText(String.valueOf(defaultNumber));
                break;
        }
        EventBus.getDefault().post(new MoneyEvent(null, new Total(defaultNumber)));
    }
}
