package electronicwallet.lyhoangvinh.com.ui.main.recharge.viewitemmodel;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import butterknife.BindView;
import electronicwallet.lyhoangvinh.com.R;
import electronicwallet.lyhoangvinh.com.base.adapter.BaseItemViewHolder;
import electronicwallet.lyhoangvinh.com.local.item.MoneyItem;

public class MoneyViewItemViewHolder extends BaseItemViewHolder<MoneyItem> {

    @BindView(R.id.rcv)
    RecyclerView rcv;

    private Context context;

    public MoneyViewItemViewHolder(ViewGroup parent, int resId) {
        super(parent, resId);
        context = parent.getContext();
    }

    @Override
    public void setItem(MoneyItem item) {
        super.setItem(item);
        rcv.setHasFixedSize(true);
        rcv.setLayoutManager(new GridLayoutManager(context, 3));
        MoneyAdapter adapter = new MoneyAdapter(item.getMoneys());
        rcv.setAdapter(adapter);
    }
}
