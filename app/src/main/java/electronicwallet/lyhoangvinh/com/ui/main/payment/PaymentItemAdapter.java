package electronicwallet.lyhoangvinh.com.ui.main.payment;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import electronicwallet.lyhoangvinh.com.R;
import electronicwallet.lyhoangvinh.com.base.adapter.BaseItemAdapter;
import electronicwallet.lyhoangvinh.com.base.adapter.BaseItemViewHolder;
import electronicwallet.lyhoangvinh.com.base.interfaces.Item;
import electronicwallet.lyhoangvinh.com.local.item.BankPaymentItem;
import electronicwallet.lyhoangvinh.com.local.item.DividerItem;
import electronicwallet.lyhoangvinh.com.local.item.PaymentDetailItem;
import electronicwallet.lyhoangvinh.com.local.item.TitleItem;
import electronicwallet.lyhoangvinh.com.ui.main.payment.viewitemmodel.BankPaymentViewItemViewHolder;
import electronicwallet.lyhoangvinh.com.ui.main.payment.viewitemmodel.PaymentViewItemViewHolder;
import electronicwallet.lyhoangvinh.com.ui.main.recharge.viewitemmodel.DividerItemViewHolder;
import electronicwallet.lyhoangvinh.com.ui.main.recharge.viewitemmodel.TitleViewItemViewHolder;

public class PaymentItemAdapter extends BaseItemAdapter {

    private static final int ITEM_TITLE = 0;
    private static final int ITEM_BANK_PAYMENT = 1;
    private static final int ITEM_DETAIL = 2;
    private static final int ITEM_DIVIDER = 3;

    @Override
    public int getItemViewType(int position) {
        Item item = getItemAt(position);
        if (item instanceof TitleItem)
            return ITEM_TITLE;

        if (item instanceof DividerItem)
            return ITEM_DIVIDER;

        if (item instanceof BankPaymentItem)
            return ITEM_BANK_PAYMENT;

        if (item instanceof PaymentDetailItem)
            return ITEM_DETAIL;

        throw new RuntimeException("Not support item " + item);
    }


    @NonNull
    @Override
    public BaseItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case ITEM_TITLE:
                return new TitleViewItemViewHolder(parent, R.layout.view_title);
            case ITEM_DIVIDER:
                return new DividerItemViewHolder(parent, R.layout.view_divider);
            case ITEM_BANK_PAYMENT:
                return new BankPaymentViewItemViewHolder(parent, R.layout.view_bank_payment);
            case ITEM_DETAIL:
                return new PaymentViewItemViewHolder(parent, R.layout.view_payment_detail);
            default:
                throw new RuntimeException("Not support type=" + viewType);
        }
    }
}
