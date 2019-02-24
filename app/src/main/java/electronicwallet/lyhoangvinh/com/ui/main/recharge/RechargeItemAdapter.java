package electronicwallet.lyhoangvinh.com.ui.main.recharge;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import electronicwallet.lyhoangvinh.com.R;
import electronicwallet.lyhoangvinh.com.base.adapter.BaseItemAdapter;
import electronicwallet.lyhoangvinh.com.base.adapter.BaseItemViewHolder;
import electronicwallet.lyhoangvinh.com.base.interfaces.Item;
import electronicwallet.lyhoangvinh.com.local.model.BankItem;
import electronicwallet.lyhoangvinh.com.local.model.DividerItem;
import electronicwallet.lyhoangvinh.com.local.model.MoneyItem;
import electronicwallet.lyhoangvinh.com.local.model.TitleItem;
import electronicwallet.lyhoangvinh.com.local.model.TotalItem;
import electronicwallet.lyhoangvinh.com.ui.main.recharge.viewitemmodel.BankItemViewHolder;
import electronicwallet.lyhoangvinh.com.ui.main.recharge.viewitemmodel.DividerItemViewHolder;
import electronicwallet.lyhoangvinh.com.ui.main.recharge.viewitemmodel.MoneyViewItemViewHolder;
import electronicwallet.lyhoangvinh.com.ui.main.recharge.viewitemmodel.TitleViewItemViewHolder;
import electronicwallet.lyhoangvinh.com.ui.main.recharge.viewitemmodel.TotalViewItemViewHolder;

public class RechargeItemAdapter extends BaseItemAdapter {

    private static final int ITEM_TITLE = 0;
    private static final int ITEM_MONEY = 1;
    private static final int ITEM_TOTAL = 2;
    private static final int ITEM_DIVIDER = 3;
    private static final int ITEM_BANK = 4;

    @Override
    public int getItemViewType(int position) {
        Item item = getItemAt(position);

        if (item instanceof TitleItem)
            return ITEM_TITLE;

        if (item instanceof MoneyItem)
            return ITEM_MONEY;

        if (item instanceof TotalItem)
            return ITEM_TOTAL;

        if (item instanceof DividerItem)
            return ITEM_DIVIDER;

        if (item instanceof BankItem)
            return ITEM_BANK;

        throw new RuntimeException("Not support item " + item);
    }

    @NonNull
    @Override
    public BaseItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case ITEM_TITLE:
                return new TitleViewItemViewHolder(parent, R.layout.view_title);
            case ITEM_MONEY:
                return new MoneyViewItemViewHolder(parent, R.layout.view_recyclerview_default);
            case ITEM_TOTAL:
                return new TotalViewItemViewHolder(parent, R.layout.view_total);
            case ITEM_DIVIDER:
                return new DividerItemViewHolder(parent, R.layout.view_divider);
            case ITEM_BANK:
                return new BankItemViewHolder(parent, R.layout.view_bank);
            default:
                throw new RuntimeException("Not support type=" + viewType);
        }
    }
}
