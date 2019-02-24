package electronicwallet.lyhoangvinh.com.local.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Total implements Parcelable {
    private int count;

    public Total(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.count);
    }

    protected Total(Parcel in) {
        this.count = in.readInt();
    }

    public static final Parcelable.Creator<Total> CREATOR = new Parcelable.Creator<Total>() {
        @Override
        public Total createFromParcel(Parcel source) {
            return new Total(source);
        }

        @Override
        public Total[] newArray(int size) {
            return new Total[size];
        }
    };
}
