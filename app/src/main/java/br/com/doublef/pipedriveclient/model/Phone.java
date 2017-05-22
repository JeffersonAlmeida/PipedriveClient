package br.com.doublef.pipedriveclient.model;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Phone extends RealmObject implements Parcelable{

    private String label;
    private String value;
    private boolean primary;

    public Phone() {
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.label);
        dest.writeString(this.value);
        dest.writeByte(this.primary ? (byte) 1 : (byte) 0);
    }

    protected Phone(Parcel in) {
        this.label = in.readString();
        this.value = in.readString();
        this.primary = in.readByte() != 0;
    }

    public static final Creator<Phone> CREATOR = new Creator<Phone>() {
        @Override
        public Phone createFromParcel(Parcel source) {
            return new Phone(source);
        }

        @Override
        public Phone[] newArray(int size) {
            return new Phone[size];
        }
    };
}
