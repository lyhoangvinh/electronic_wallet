package electronicwallet.lyhoangvinh.com.local.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Money implements Parcelable {
    private int price;
    private boolean tick = false;

    public Money(int price, boolean tick) {
        this.price = price;
        this.tick = tick;
    }

    public int getPrice() {
        return price;
    }

    public boolean isTick() {
        return tick;
    }

    public void setTick(boolean tick) {
        this.tick = tick;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.price);
        dest.writeByte(this.tick ? (byte) 1 : (byte) 0);
    }

    protected Money(Parcel in) {
        this.price = in.readInt();
        this.tick = in.readByte() != 0;
    }

    public static final Parcelable.Creator<Money> CREATOR = new Parcelable.Creator<Money>() {
        @Override
        public Money createFromParcel(Parcel source) {
            return new Money(source);
        }

        @Override
        public Money[] newArray(int size) {
            return new Money[size];
        }
    };
}
