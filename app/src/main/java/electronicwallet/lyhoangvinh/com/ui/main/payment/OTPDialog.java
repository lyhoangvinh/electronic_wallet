package electronicwallet.lyhoangvinh.com.ui.main.payment;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mukesh.OtpView;

import butterknife.BindView;
import butterknife.OnClick;
import electronicwallet.lyhoangvinh.com.R;
import electronicwallet.lyhoangvinh.com.base.dialog.BaseDialogFragment;
import electronicwallet.lyhoangvinh.com.base.interfaces.PlainConsumer;

public class OTPDialog extends BaseDialogFragment {
    @BindView(R.id.tvTitle)
    TextView tvTitle;

    @BindView(R.id.imvBack)
    View imvBack;

    @BindView(R.id.otp_view)
    OtpView otpView;
    private String otp;
    private PlainConsumer<String> onOtpCompleted;

    public void setOnOtpCompleted(PlainConsumer<String> onOtpCompleted) {
        this.onOtpCompleted = onOtpCompleted;
    }

    @Override
    protected int getLayout() {
        return R.layout.dialog_otp;
    }

    @Override
    protected void onBindView(Context context) {
        setFullscreen();
        imvBack.setVisibility(View.GONE);
        tvTitle.setText(getString(R.string.nhap_ma_otp));
        otpView.setOtpCompletionListener(otp -> {
            this.otp = otp;
            Log.d("onOtpCompleted=>", otp);
        });
    }

    @OnClick(R.id.btnConfirm)
    public void confirmOTP() {
        if (onOtpCompleted != null && !TextUtils.isEmpty(otp)) {
            onOtpCompleted.accept(otp);
            dismiss();
        }else {
            Toast.makeText(getActivity(), "Vui lòng nhập đủ số OTP", Toast.LENGTH_SHORT).show();
        }
    }
}
