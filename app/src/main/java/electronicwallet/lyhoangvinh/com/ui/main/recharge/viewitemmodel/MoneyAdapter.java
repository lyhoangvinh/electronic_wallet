package electronicwallet.lyhoangvinh.com.ui.main.recharge.viewitemmodel;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import electronicwallet.lyhoangvinh.com.R;
import electronicwallet.lyhoangvinh.com.base.adapter.BaseAdapter;
import electronicwallet.lyhoangvinh.com.base.adapter.BaseViewHolder;
import electronicwallet.lyhoangvinh.com.events.MoneyEvent;
import electronicwallet.lyhoangvinh.com.local.model.Money;

public class MoneyAdapter extends BaseAdapter<Money, MoneyAdapter.MoneyViewHolder> {

    MoneyAdapter(@NonNull List<Money> data) {
        super(data);
    }

    @Override
    public int getItemLayoutResource() {
        return R.layout.item_money;
    }

    @Override
    public MoneyViewHolder createViewHolder(View v) {
        return new MoneyViewHolder(v);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onBindViewHolder(MoneyViewHolder holder, @NonNull Money dto, int position) {
        holder.btMoney.setText(String.format(holder.btMoney.getContext().getString(R.string.money), dto.getContent()));

        holder.btMoney.setOnClickListener(view -> {
            EventBus.getDefault().post(new MoneyEvent(dto, null));
        });
    }

    class MoneyViewHolder extends BaseViewHolder {

        @BindView(R.id.btMoney)
        Button btMoney;

        MoneyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
