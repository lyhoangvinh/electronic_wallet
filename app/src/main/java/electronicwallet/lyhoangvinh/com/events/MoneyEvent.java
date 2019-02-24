package electronicwallet.lyhoangvinh.com.events;

import android.os.Parcel;
import android.os.Parcelable;

import electronicwallet.lyhoangvinh.com.local.model.Money;
import electronicwallet.lyhoangvinh.com.local.model.Total;


public class MoneyEvent implements Parcelable {
    private Money money;
    private Total total;
    private String bankName;

    public MoneyEvent() {
    }

    public MoneyEvent(Money money, Total total) {
        this.money = money;
        this.total = total;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Money getMoney() {
        return money;
    }

    public void setMoney(Money money) {
        this.money = money;
    }

    public Total getTotal() {
        return total;
    }

    public void setTotal(Total total) {
        this.total = total;
    }

    public String getBankName() {
        return bankName;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.money, flags);
        dest.writeParcelable(this.total, flags);
        dest.writeString(this.bankName);
    }

    protected MoneyEvent(Parcel in) {
        this.money = in.readParcelable(Money.class.getClassLoader());
        this.total = in.readParcelable(Total.class.getClassLoader());
        this.bankName = in.readString();
    }

    public static final Parcelable.Creator<MoneyEvent> CREATOR = new Parcelable.Creator<MoneyEvent>() {
        @Override
        public MoneyEvent createFromParcel(Parcel source) {
            return new MoneyEvent(source);
        }

        @Override
        public MoneyEvent[] newArray(int size) {
            return new MoneyEvent[size];
        }
    };
}
