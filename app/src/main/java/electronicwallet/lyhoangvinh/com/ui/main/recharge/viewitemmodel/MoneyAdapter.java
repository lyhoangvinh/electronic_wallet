package electronicwallet.lyhoangvinh.com.ui.main.recharge.viewitemmodel;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import electronicwallet.lyhoangvinh.com.R;
import electronicwallet.lyhoangvinh.com.base.adapter.BaseAdapter;
import electronicwallet.lyhoangvinh.com.base.adapter.BaseViewHolder;
import electronicwallet.lyhoangvinh.com.events.MoneyEvent;
import electronicwallet.lyhoangvinh.com.local.model.Money;
import electronicwallet.lyhoangvinh.com.utils.Utils;

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

    @Override
    protected void onBindViewHolder(MoneyViewHolder holder, @NonNull Money dto, int position) {
        holder.tvMoney.setText(String.format(holder.tvMoney.getContext().getString(R.string.money), dto.getContent()));

        if (dto.isTick()) {
            holder.tvMoney.setTextColor(holder.tvMoney.getContext().getResources().getColor(R.color.white));
            Utils.setBackground(holder.root.getContext(), holder.root, R.drawable.bg_blue_light);
        } else {
            holder.tvMoney.setTextColor(holder.tvMoney.getContext().getResources().getColor(R.color.dark_text));
            Utils.setBackground(holder.root.getContext(), holder.root, R.drawable.bg_white_light);
        }

        holder.itemView.setOnClickListener(view -> {
            changeTick(position);
            EventBus.getDefault().post(new MoneyEvent(dto, null));
        });
    }

    private void changeTick(int i) {
        for (Money dto : getData()) {
            dto.setTick(false);
        }
        getData().get(i).setTick(true);
        notifyDataSetChanged();
    }

    class MoneyViewHolder extends BaseViewHolder {

        @BindView(R.id.tvMoney)
        TextView tvMoney;

        @BindView(R.id.root)
        RelativeLayout root;

        MoneyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
