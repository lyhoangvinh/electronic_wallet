package electronicwallet.lyhoangvinh.com.ui.main.contacts;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import electronicwallet.lyhoangvinh.com.R;
import electronicwallet.lyhoangvinh.com.base.adapter.BaseAdapter;
import electronicwallet.lyhoangvinh.com.base.adapter.BaseViewHolder;
import electronicwallet.lyhoangvinh.com.base.interfaces.PlainConsumer;
import electronicwallet.lyhoangvinh.com.local.model.MyContact;

public class ContactAdapter extends BaseAdapter<MyContact, ContactAdapter.ContactViewHolder> {
    private PlainConsumer<String> onClickItemListener;

    ContactAdapter(@NonNull List<MyContact> data,PlainConsumer<String> onClickItemListener) {
        super(data);
        this.onClickItemListener = onClickItemListener;
    }

    @Override
    public int getItemLayoutResource() {
        return R.layout.item_contact;
    }

    @Override
    public ContactViewHolder createViewHolder(View v) {
        return new ContactViewHolder(v);
    }


    @Override
    protected void onBindViewHolder(ContactViewHolder holder, @NonNull MyContact dto, int position) {
        holder.tvName.setText(dto.getName());
        holder.tvPhoneNumber.setText(dto.getNumber());
        holder.itemView.setOnClickListener(view -> {
            if (onClickItemListener != null) {
                onClickItemListener.accept(dto.getNumber());
            }
        });
    }

    class ContactViewHolder extends BaseViewHolder {
        @BindView(R.id.tvName)
        TextView tvName;

        @BindView(R.id.tvPhoneNumber)
        TextView tvPhoneNumber;

        ContactViewHolder(View itemView) {
            super(itemView);
        }
    }
}
