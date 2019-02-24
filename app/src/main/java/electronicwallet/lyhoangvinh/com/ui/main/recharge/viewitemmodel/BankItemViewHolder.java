package electronicwallet.lyhoangvinh.com.ui.main.recharge.viewitemmodel;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import electronicwallet.lyhoangvinh.com.R;
import electronicwallet.lyhoangvinh.com.base.adapter.BaseItemViewHolder;
import electronicwallet.lyhoangvinh.com.base.rcv.GravitySnapHelper;
import electronicwallet.lyhoangvinh.com.base.rcv.MySnapHelper;
import electronicwallet.lyhoangvinh.com.local.model.BankItem;

public class BankItemViewHolder extends BaseItemViewHolder<BankItem> {

    @BindView(R.id.tvTitle)
    TextView tvTitle;

    @BindView(R.id.tvContent)
    TextView tvContent;

    @BindView(R.id.rcv)
    RecyclerView rcv;

    private Context context;

    public BankItemViewHolder(ViewGroup parent, int resId) {
        super(parent, resId);
        context = parent.getContext();
    }

    @Override
    public void setItem(BankItem item) {
        super.setItem(item);
        tvContent.setVisibility(View.VISIBLE);
        tvContent.setText(item.getName());
        tvTitle.setText(item.getTitle());
        rcv.setHasFixedSize(true);
        rcv.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        BankAdapter adapter = new BankAdapter(item.getBanks(), s -> {
            tvContent.setVisibility(View.GONE);
            tvTitle.setText(String.format(context.getString(R.string.phương_thuc_thanh_toan), s));
        });
        rcv.setAdapter(adapter);
        MySnapHelper snapRecycleReferences = new GravitySnapHelper(Gravity.START);
        snapRecycleReferences.attachToRecyclerView(rcv);
    }
}
