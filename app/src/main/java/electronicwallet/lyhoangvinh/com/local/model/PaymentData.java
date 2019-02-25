package electronicwallet.lyhoangvinh.com.local.model;

import android.os.Parcel;
import android.os.Parcelable;

public class PaymentData implements Parcelable {

    private int count;

    private int money;

    private Bank bank;

    public PaymentData(int count, int money, Bank bank) {
        this.count = count;
        this.money = money;
        this.bank = bank;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Bank getBank() {
        return bank;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.count);
        dest.writeInt(this.money);
        dest.writeParcelable(this.bank, flags);
    }

    protected PaymentData(Parcel in) {
        this.count = in.readInt();
        this.money = in.readInt();
        this.bank = in.readParcelable(Bank.class.getClassLoader());
    }

    public static final Creator<PaymentData> CREATOR = new Creator<PaymentData>() {
        @Override
        public PaymentData createFromParcel(Parcel source) {
            return new PaymentData(source);
        }

        @Override
        public PaymentData[] newArray(int size) {
            return new PaymentData[size];
        }
    };
}
