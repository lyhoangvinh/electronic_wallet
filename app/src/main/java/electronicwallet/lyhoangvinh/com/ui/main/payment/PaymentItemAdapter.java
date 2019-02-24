package electronicwallet.lyhoangvinh.com.ui.main.payment;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import electronicwallet.lyhoangvinh.com.base.adapter.BaseItemAdapter;
import electronicwallet.lyhoangvinh.com.base.adapter.BaseItemViewHolder;
import electronicwallet.lyhoangvinh.com.base.interfaces.Item;

public class PaymentItemAdapter extends BaseItemAdapter {

    @Override
    public int getItemViewType(int position) {
        Item item = getItemAt(position);

        throw new RuntimeException("Not support item " + item);
    }


    @NonNull
    @Override
    public BaseItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        switch (viewType) {

            default:
                throw new RuntimeException("Not support type=" + viewType);
        }
    }
}
