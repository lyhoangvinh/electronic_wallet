package electronicwallet.lyhoangvinh.com.local.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Bank implements Parcelable {
    private String url;
    private String title;
    private boolean isTick;

    public boolean isTick() {
        return isTick;
    }

    public void setTick(boolean tick) {
        isTick = tick;
    }

    public Bank(String url, String title) {
        this.url = url;
        this.title = title;
    }

    public Bank(String url, String title, boolean isTick) {
        this.url = url;
        this.title = title;
        this.isTick = isTick;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeString(this.title);
        dest.writeByte(this.isTick ? (byte) 1 : (byte) 0);
    }

    protected Bank(Parcel in) {
        this.url = in.readString();
        this.title = in.readString();
        this.isTick = in.readByte() != 0;
    }

    public static final Parcelable.Creator<Bank> CREATOR = new Parcelable.Creator<Bank>() {
        @Override
        public Bank createFromParcel(Parcel source) {
            return new Bank(source);
        }

        @Override
        public Bank[] newArray(int size) {
            return new Bank[size];
        }
    };
}
