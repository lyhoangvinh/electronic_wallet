package electronicwallet.lyhoangvinh.com.ui.main.recharge.viewitemmodel;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import electronicwallet.lyhoangvinh.com.R;
import electronicwallet.lyhoangvinh.com.base.adapter.BaseAdapter;
import electronicwallet.lyhoangvinh.com.base.adapter.BaseViewHolder;
import electronicwallet.lyhoangvinh.com.base.interfaces.PlainBiConsumer;
import electronicwallet.lyhoangvinh.com.events.MoneyEvent;
import electronicwallet.lyhoangvinh.com.local.model.Bank;
import electronicwallet.lyhoangvinh.com.utils.Utils;

public class BankAdapter extends BaseAdapter<Bank, BankAdapter.BankViewHolder> {
    private PlainBiConsumer<String, Integer> onClickItemListener;

    BankAdapter(@NonNull List<Bank> data, PlainBiConsumer<String, Integer> onClickItemListener) {
        super(data);
        this.onClickItemListener = onClickItemListener;
    }


    @Override
    public int getItemLayoutResource() {
        return R.layout.item_bank;
    }

    @Override
    public BankViewHolder createViewHolder(View v) {
        return new BankViewHolder(v);
    }

    @Override
    protected void onBindViewHolder(BankViewHolder holder, @SuppressLint("RecyclerView") @NonNull Bank dto, int position) {
        holder.tvName.setText(dto.getTitle());
        Utils.setImageFromUrl(dto.getUrl(), holder.imv);
        if (dto.isTick()) {
            holder.tvName.setTextColor(holder.tvName.getContext().getResources().getColor(R.color.white));
            Utils.setBackground(holder.root.getContext(), holder.root, R.drawable.bg_blue_light);
        } else {
            holder.tvName.setTextColor(holder.tvName.getContext().getResources().getColor(R.color.dark_text));
            Utils.setBackground(holder.root.getContext(), holder.root, R.drawable.button_blue);
        }
        holder.itemView.setOnClickListener(view -> {
            if (onClickItemListener != null) {
                onClickItemListener.accept(dto.getTitle(), position);
            }
            MoneyEvent moneyEvent = new MoneyEvent();
            moneyEvent.setBank(dto);
            EventBus.getDefault().post(moneyEvent);
            changeTick(position);
        });
    }

    private void changeTick(int i) {
        for (Bank dto : getData()) {
            dto.setTick(false);
        }
        getData().get(i).setTick(true);
        notifyDataSetChanged();
    }

    class BankViewHolder extends BaseViewHolder {

        @BindView(R.id.tvName)
        TextView tvName;

        @BindView(R.id.imvAvatar)
        ImageView imv;

        @BindView(R.id.root)
        RelativeLayout root;

        BankViewHolder(View itemView) {
            super(itemView);
        }
    }
}
