package electronicwallet.lyhoangvinh.com.ui.main.recharge.viewitemmodel;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import electronicwallet.lyhoangvinh.com.R;
import electronicwallet.lyhoangvinh.com.base.adapter.BaseAdapter;
import electronicwallet.lyhoangvinh.com.base.adapter.BaseViewHolder;
import electronicwallet.lyhoangvinh.com.local.model.Bank;
import electronicwallet.lyhoangvinh.com.utils.Utils;

public class BankAdapter extends BaseAdapter<Bank, BankAdapter.BankViewHolder> {

    public BankAdapter(@NonNull List<Bank> data) {
        super(data);
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
    protected void onBindViewHolder(BankViewHolder holder, @NonNull Bank dto, int position) {
        holder.tvName.setText(dto.getTitle());
        Utils.setImageFromUrl(dto.getUrl(), holder.imv);
    }

    class BankViewHolder extends BaseViewHolder {

        @BindView(R.id.tvName)
        TextView tvName;

        @BindView(R.id.imvAvatar)
        ImageView imv;

        BankViewHolder(View itemView) {
            super(itemView);
        }
    }
}
